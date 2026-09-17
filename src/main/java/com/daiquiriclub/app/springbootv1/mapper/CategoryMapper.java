package com.daiquiriclub.app.springbootv1.mapper;

import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.category.response.CategoryResponse;
import com.daiquiriclub.app.springbootv1.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryCreateRequest categoryCreateRequest);
    void updateCategory(CategoryUpdateRequest categoryUpdateRequest, @MappingTarget Category category);
}
