package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.request.ProductCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.request.ProductUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.response.ProductResponse;
import com.daiquiriclub.app.springbootv1.entity.Category;
import com.daiquiriclub.app.springbootv1.entity.Product;
import com.daiquiriclub.app.springbootv1.exception.BadRequestException;
import com.daiquiriclub.app.springbootv1.exception.ResourceNotFoundException;
import com.daiquiriclub.app.springbootv1.mapper.ProductMapper;
import com.daiquiriclub.app.springbootv1.repository.CategoryRepository;
import com.daiquiriclub.app.springbootv1.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }
    public ApiResult<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> products =
                productRepository.findAll().stream().map(
                        productMapper::toProductResponse
                ).toList();
        return new ApiResult<>(true, "All Products", products);
    }
    public ApiResult<List<ProductResponse>> getAllProductsActive(){
        List<ProductResponse> products = productRepository.findByActiveTrue().stream().map(productMapper::toProductResponse).toList();
        return new ApiResult<>(true, "All Products active",products);
    }
    public ApiResult<ProductResponse> getByIdProduct(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Product product = productRepository.findById(uuid).orElseThrow(
                ()-> new ResourceNotFoundException("Not Found Product")
        );
        return new ApiResult<>(true, "Product found", productMapper.toProductResponse(product));
    }
    public ApiResult<ProductResponse> createProduct (ProductCreateRequest productCreateRequest){
        UUID uuid;
        try{
            uuid = UUID.fromString(productCreateRequest.categoryId());
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Category category = categoryRepository.findById(uuid).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found")
        );
        Product product = productMapper.toProduct(productCreateRequest);
        product.setCategory(category);
        return new ApiResult<>(
                true,"Category created", productMapper.toProductResponse(product)
        );
    }
    public  ApiResult<ProductResponse> updateProduct(String idProduct, ProductUpdateRequest productUpdateRequest){
        UUID uuidProduct, uuidCategory;
        try{
            uuidProduct = UUID.fromString(idProduct);
            uuidCategory = UUID.fromString(productUpdateRequest.categoryId());
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Product product = productRepository.findById(uuidProduct).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found")
        );
        Category category = categoryRepository.findById(uuidCategory).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found")
        );
        productMapper.updateProduct(productUpdateRequest,product);
        product.setCategory(category);
        ProductResponse productResponse = productMapper.toProductResponse(
                productRepository.save(product)
        );
        return new ApiResult<>(true,"product updated", productResponse);
    }
    public  ApiResult<Void> deleteProduct(String id){
        UUID uuidProduct;
        try{
            uuidProduct = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Product product = productRepository.findById(uuidProduct).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found")
        );
        product.setActive(false);
        productRepository.save(product);
        return new ApiResult<>(true,"The product is disabled", null);
    }
}
