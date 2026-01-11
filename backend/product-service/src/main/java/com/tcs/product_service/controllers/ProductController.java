package com.tcs.product_service.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.product_service.dtos.ProductRequest;
import com.tcs.product_service.dtos.ProductResponse;
import com.tcs.product_service.entities.Product;
import com.tcs.product_service.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponse add(
            @RequestBody ProductRequest req,
            @RequestHeader("Authorization") String authHeader) {

        Product p = service.addProduct(req, authHeader);
        return map(p);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAllProducts()
                .stream()
                .map(this::map)
                .toList();
    }

    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @RequestBody ProductRequest req) {

        return map(service.updateProduct(id, req));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    private ProductResponse map(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getCategory().getName()
        );
    }
}
