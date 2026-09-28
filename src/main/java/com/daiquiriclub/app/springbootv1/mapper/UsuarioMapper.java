package com.daiquiriclub.app.springbootv1.mapper;

import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.response.UsuarioResponse;
import com.daiquiriclub.app.springbootv1.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "active",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    Usuario toUsuario(UsuarioCreateRequest usuarioCreateRequest);

    UsuarioResponse toUsuarioResponse(Usuario usuario);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    void updatedUsuario(UsuarioUpdateRequest updateRequest, @MappingTarget Usuario usuario);
}
