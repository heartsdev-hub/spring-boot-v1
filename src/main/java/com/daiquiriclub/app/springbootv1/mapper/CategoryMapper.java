package com.daiquiriclub.app.springbootv1.mapper;

import com.daiquiriclub.app.springbootv1.dto.category.request.CategoryCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.category.response.CategoryResponse;
import com.daiquiriclub.app.springbootv1.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryCreateRequest categoryCreateRequest);
}
