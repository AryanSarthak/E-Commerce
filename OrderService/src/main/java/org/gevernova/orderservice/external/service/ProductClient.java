package org.gevernova.orderservice.external.service;

import org.gevernova.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    // Fetch product details by product ID
    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

    // Reduce product quantity by given amount
    @PutMapping("/products/reduce/{id}")
    void reduce(@PathVariable("id") Long id,
                @RequestParam("quantity") int quantity);
}
