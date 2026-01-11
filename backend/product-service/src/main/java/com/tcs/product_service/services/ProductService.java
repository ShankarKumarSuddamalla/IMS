package com.tcs.product_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.product_service.client.InventoryClient;
import com.tcs.product_service.dtos.InventoryInitRequest;
import com.tcs.product_service.dtos.ProductRequest;
import com.tcs.product_service.entities.Category;
import com.tcs.product_service.entities.Product;
import com.tcs.product_service.repositories.CategoryRepository;
import com.tcs.product_service.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final InventoryClient inventoryClient;

    public ProductService(ProductRepository productRepo,
                          CategoryRepository categoryRepo,
                          InventoryClient inventoryClient) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.inventoryClient = inventoryClient;
    }

    public Product addProduct(ProductRequest req, String authHeader) {

        Category category = categoryRepo.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());
        product.setCategory(category);

        Product saved = productRepo.save(product);

        // 🔗 SYNC WITH INVENTORY SERVICE
        inventoryClient.initInventory(
                new InventoryInitRequest(saved.getId(), 0),
                authHeader
        );

        return saved;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product updateProduct(Long id, ProductRequest req) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(req.getName());
        product.setDescription(req.getDescription());
        product.setPrice(req.getPrice());

        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
