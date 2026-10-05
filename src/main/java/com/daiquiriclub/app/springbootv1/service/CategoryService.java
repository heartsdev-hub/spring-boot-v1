package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.category.response.CategoryResponse;
import com.daiquiriclub.app.springbootv1.entity.Category;
import com.daiquiriclub.app.springbootv1.exception.BadRequestException;
import com.daiquiriclub.app.springbootv1.exception.ConflictException;
import com.daiquiriclub.app.springbootv1.exception.ResourceNotFoundException;
import com.daiquiriclub.app.springbootv1.mapper.CategoryMapper;
import com.daiquiriclub.app.springbootv1.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    @PreAuthorize("hasAuthority('CATEGORY_READ')")
    public ApiResult<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> categories = categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
        return new ApiResult<>(
                true,"All Categories",
                categories
        );
    }
    @PreAuthorize("hasAuthority('CATEGORY_READ_ACTIVE')")
    public ApiResult<List<CategoryResponse>> getActiveCategories(){
        List<CategoryResponse> categories = categoryRepository.findByActiveTrue().stream().map(categoryMapper::toCategoryResponse).toList();
        return new ApiResult<>(true,"All active categories", categories);
    }
    @PreAuthorize("hasAuthority('CATEGORY_READ')")
    public ApiResult<CategoryResponse> getByIdCategory(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("Invalid UUID");
        }
        Category category = categoryRepository.findById(uuid).orElseThrow(
                ()-> new ResourceNotFoundException("Category Not Found")
        );
        return new ApiResult<>(true,"Category Found", categoryMapper.toCategoryResponse(category));
    }
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public  ApiResult<CategoryResponse> createCategory(CategoryCreateRequest categoryCreateRequest){
        String name = categoryCreateRequest.name().trim().toUpperCase();
        if(categoryRepository.existsByName(name)){
            throw new ConflictException("The category already exists.");
        }
        Category category = categoryMapper.toCategory(categoryCreateRequest);
        category.setName(name);
        CategoryResponse categoryResponse = categoryMapper.toCategoryResponse(categoryRepository.save(category));
        return new ApiResult<>(
                true,"Category created successfully",categoryResponse
        );
    }
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ApiResult<CategoryResponse> updateCategory (String id, CategoryUpdateRequest categoryUpdateRequest){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException exception){
            throw new BadRequestException("UUID invalid");
        }
        Category category = categoryRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("Category Not Found")
        );
        categoryMapper.updateCategory(categoryUpdateRequest,category);
        return new ApiResult<>(true,"Category updated",categoryMapper.toCategoryResponse(categoryRepository.save(category)));
    }
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ApiResult<Void> deleteCategory(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("Invalid UUID");
        }
        Category category = categoryRepository.findById(uuid).orElseThrow(
                ()-> new ResourceNotFoundException("Category Not Found")
        );
        category.setActive(false);
        categoryRepository.save(category);
        return new ApiResult<>(true, "The category is disabled.",null);
    }
}
