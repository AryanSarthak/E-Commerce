package org.gevernova.productservice.service;

import lombok.RequiredArgsConstructor;
import org.gevernova.productservice.entity.Product;
import org.gevernova.productservice.exception.InsufficientStockException;
import org.gevernova.productservice.exception.ProductNotFoundException;
import org.gevernova.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public void reduceQuantity(Long id, int quantity) {
        Product product = getById(id);

        if (product.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
