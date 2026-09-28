package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.response.UsuarioResponse;
import com.daiquiriclub.app.springbootv1.entity.Usuario;
import com.daiquiriclub.app.springbootv1.exception.ConflictException;
import com.daiquiriclub.app.springbootv1.mapper.UsuarioMapper;
import com.daiquiriclub.app.springbootv1.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }
    public ApiResult<List<UsuarioResponse>> getAllUsers(){
        List<UsuarioResponse> usuarios = usuarioRepository.findAll().stream().map(usuarioMapper::toUsuarioResponse).toList();
        return new ApiResult<>(true,"all Uusarios",usuarios);
    }
    public ApiResult<List<UsuarioResponse>> getAllUsersActive(){
        List<UsuarioResponse> usuarios = usuarioRepository.allUsuariosActive().stream().map(usuarioMapper::toUsuarioResponse).toList();
        return new ApiResult<>(true,"all Usuarios Active",usuarios);
    }
    public ApiResult<UsuarioResponse> createUsuer(UsuarioCreateRequest usuarioCreateRequest){
        if(usuarioRepository.existsByCorreo(usuarioCreateRequest.correo()))
            throw new ConflictException("El correo ya existe");

    }
}
