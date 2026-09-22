package com.daiquiriclub.app.springbootv1.mapper;

import com.daiquiriclub.app.springbootv1.dto.product.request.ProductCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.request.ProductUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.product.response.ProductResponse;
import com.daiquiriclub.app.springbootv1.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "created_at",ignore = true)
    @Mapping(target = "updated_at",ignore = true)
    Product toProduct (ProductCreateRequest productCreateRequest);
    @Mapping(source = "category.name",target = "category")
    ProductResponse toProductResponse(Product product);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "category",ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    void updateProduct(ProductUpdateRequest updateRequest, @MappingTarget Product product);
}
