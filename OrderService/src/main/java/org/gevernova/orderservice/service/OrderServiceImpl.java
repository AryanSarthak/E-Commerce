package org.gevernova.orderservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.gevernova.orderservice.dto.ProductResponse;
import org.gevernova.orderservice.entity.Order;
import org.gevernova.orderservice.external.service.ProductClient;
import org.gevernova.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient;

    // Place order by fetching product details and reducing stock
    @Override
    @CircuitBreaker(name = "productService", fallbackMethod = "fallback")
    public Order place(Long productId, int quantity) {

        ProductResponse product = productClient.getProduct(productId);

        productClient.reduce(productId, quantity);

        double total = product.getPrice() * quantity;

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalAmount(total);

        return repository.save(order);
    }

    // Fallback method executed when ProductService is unavailable
    public Order fallback(Long productId, int quantity, Exception ex) {
        return new Order(null, productId, quantity, 0);
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

}

