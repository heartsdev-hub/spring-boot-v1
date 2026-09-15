package com.daiquiriclub.app.springbootv1.controller;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.category.response.CategoryResponse;
import com.daiquiriclub.app.springbootv1.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<ApiResult<List<CategoryResponse>>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<CategoryResponse>> getByIdCategory(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getByIdCategory(id));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResult<CategoryResponse>> createCategory(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryCreateRequest));
    }
}
