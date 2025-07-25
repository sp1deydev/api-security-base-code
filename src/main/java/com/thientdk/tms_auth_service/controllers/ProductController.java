package com.thientdk.tms_auth_service.controllers;

import com.thientdk.tms_auth_service.models.requests.ProductRequest;
import com.thientdk.tms_auth_service.models.responses.TextResponse;
import com.thientdk.tms_auth_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public TextResponse createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

}
