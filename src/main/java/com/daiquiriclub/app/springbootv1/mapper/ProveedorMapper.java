package com.daiquiriclub.app.springbootv1.mapper;

import com.daiquiriclub.app.springbootv1.dto.Proveedor.Request.ProveedorCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Request.ProveedorUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Response.ProveedorResponse;
import com.daiquiriclub.app.springbootv1.entity.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "active",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    Proveedor toProveedor(ProveedorCreateRequest proveedorCreateRequest);

    ProveedorResponse toProveedorResponse(Proveedor proveedor);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "active",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    void updateProveedor(ProveedorUpdateRequest proveedorUpdateRequest, @MappingTarget Proveedor proveedor);
}
