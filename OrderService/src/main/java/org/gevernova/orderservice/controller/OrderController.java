package org.gevernova.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.gevernova.orderservice.entity.Order;
import org.gevernova.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // Create a new order for the given product and quantity
    @PostMapping
    public Order place(@RequestParam Long productId,
                       @RequestParam int quantity) {
        return service.place(productId, quantity);
    }

    // Retrieve order details by order ID
    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return service.getById(id);
    }
}
