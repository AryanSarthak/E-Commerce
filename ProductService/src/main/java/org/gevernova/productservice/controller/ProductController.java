package org.gevernova.productservice.controller;


import lombok.RequiredArgsConstructor;
import org.gevernova.productservice.entity.Product;
import org.gevernova.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product add(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/reduce/{id}")
    public void reduce(@PathVariable Long id,
                       @RequestParam int quantity) {
        service.reduceQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
