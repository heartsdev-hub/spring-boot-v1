package com.daiquiriclub.app.springbootv1.controller;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.product.request.ProductCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.request.ProductUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.response.ProductResponse;
import com.daiquiriclub.app.springbootv1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<ApiResult<List<ProductResponse>>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/active")
    public ResponseEntity<ApiResult<List<ProductResponse>>> getActiveProducts(){
        return ResponseEntity.ok(productService.getAllProductsActive());
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResult<ProductResponse>> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productCreateRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<ProductResponse>> updatedProduct(@PathVariable String id, @RequestBody @Valid ProductUpdateRequest productUpdateRequest){
        return ResponseEntity.ok(productService.updateProduct(id,productUpdateRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<Void>> deleteProduct(@PathVariable String id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
