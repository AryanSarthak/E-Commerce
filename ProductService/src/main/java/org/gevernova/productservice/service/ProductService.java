package org.gevernova.productservice.service;

import org.gevernova.productservice.entity.Product;

import java.util.List;

public interface ProductService {
     Product addProduct(Product product);
    List<Product> getAll();
    Product getById(Long id);
    void reduceQuantity(Long id, int quantity);
    void delete(Long id);
}
