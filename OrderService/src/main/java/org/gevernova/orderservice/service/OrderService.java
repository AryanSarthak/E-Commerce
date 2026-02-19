package org.gevernova.orderservice.service;

import org.gevernova.orderservice.entity.Order;

public interface OrderService {

    // Place a new order for a product with given quantity
    Order place(Long productId, int quantity);

    // Retrieve order details by order ID
    Order getById(Long id);
}

