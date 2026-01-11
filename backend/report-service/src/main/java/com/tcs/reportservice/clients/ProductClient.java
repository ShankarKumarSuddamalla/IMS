package com.tcs.reportservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tcs.reportservice.dtos.ProductDTO;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductDTO> getProducts();
}
