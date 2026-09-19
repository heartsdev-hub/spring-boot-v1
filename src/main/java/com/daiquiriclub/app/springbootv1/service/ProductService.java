package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.product.response.ProductResponse;
import com.daiquiriclub.app.springbootv1.mapper.ProductMapper;
import com.daiquiriclub.app.springbootv1.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    public ApiResult<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> products =
                productRepository.findAll().stream().map(
                        productMapper::toProductResponse
                ).toList();
        return new ApiResult<>(true, "All Products", products);
    }
}
