package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.TipoDocumento;
import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.response.UsuarioResponse;
import com.daiquiriclub.app.springbootv1.entity.Usuario;
import com.daiquiriclub.app.springbootv1.exception.BadRequestException;
import com.daiquiriclub.app.springbootv1.exception.ConflictException;
import com.daiquiriclub.app.springbootv1.exception.ResourceNotFoundException;
import com.daiquiriclub.app.springbootv1.mapper.UsuarioMapper;
import com.daiquiriclub.app.springbootv1.repository.UsuarioRepository;
import com.daiquiriclub.app.springbootv1.security.enums.Rol;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public ApiResult<List<UsuarioResponse>> getAllUsers(){
        List<UsuarioResponse> usuarios = usuarioRepository.findAll().stream().map(usuarioMapper::toUsuarioResponse).toList();
        return new ApiResult<>(true,"all Uusarios",usuarios);
    }
    public ApiResult<List<UsuarioResponse>> getAllUsersActive(){
        List<UsuarioResponse> usuarios = usuarioRepository.allUsuariosActive().stream().map(usuarioMapper::toUsuarioResponse).toList();
        return new ApiResult<>(true,"all Usuarios Active",usuarios);
    }
    public ApiResult<UsuarioResponse> getByIdUser(String id){
        UUID uuid;
        try{
        uuid = UUID.fromString(id);
        }catch (IllegalArgumentException exception){
            throw new BadRequestException("UUID invalid");
        }
        Usuario usuario = usuarioRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
        return new ApiResult<>(true,"usuario encontrado con id", usuarioMapper.toUsuarioResponse(usuario));
    }
    public ApiResult<UsuarioResponse> createUsuer(UsuarioCreateRequest usuarioCreateRequest){
        if(usuarioRepository.existsByCorreo(usuarioCreateRequest.correo()))
            throw new ConflictException("El correo ya existe");
       boolean existe = Arrays.stream(TipoDocumento.values()).anyMatch(tipo -> tipo.name().equalsIgnoreCase(usuarioCreateRequest.tipoDocumento().name()));
       if(!existe)
           throw new BadRequestException("Tipo de documento invalido");
       for(var role : usuarioCreateRequest.roles()){
           boolean existeRol = Arrays.stream(Rol.values()).anyMatch(
                   rol -> rol.name().equalsIgnoreCase(role.name())
           );
           if(!existeRol)
               throw new BadRequestException("Rol invalido: " + role.name());
       }
        Usuario usuario = usuarioMapper.toUsuario(usuarioCreateRequest);
        usuario.setPassword(passwordEncoder.encode(usuarioCreateRequest.password()));
        UsuarioResponse usuarioResponse = usuarioMapper.toUsuarioResponse(usuarioRepository.save(usuario));
        return new ApiResult<>(true, "usuario agregado", usuarioResponse);
    }
    public ApiResult<UsuarioResponse> updatedUser(String id, UsuarioUpdateRequest usuarioUpdateRequest){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException exception){
            throw new BadRequestException("UUID invalid");
        }
        Usuario usuario = usuarioRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
        List<Usuario> usuarios = usuarioRepository.findAll();
        boolean existeNumDocument = usuarios.stream().anyMatch(
                u -> u.getNumDocumento().equals(usuarioUpdateRequest.numDocumento())
                        && !u.getId().equals(uuid)
        );
        if(existeNumDocument) {
            throw new ConflictException("El numero de documento ya existe");
        }
        for(var role : usuarioUpdateRequest.roles()){
            boolean existeRol = Arrays.stream(Rol.values()).anyMatch(
                    rol -> rol.name().equalsIgnoreCase(role.name())
            );
            if(!existeRol)
                throw new BadRequestException("Rol invalido: " + role.name());
        }
        boolean existsCorreo = usuarios.stream().anyMatch(
                c -> c.getCorreo().equals(usuarioUpdateRequest.correo())
                && !c.getId().equals(uuid)
        );
        if(existsCorreo)
            throw new ConflictException("El correo ya existe");
        usuarioMapper.updatedUsuario(usuarioUpdateRequest,usuario);
        usuario.setPassword(passwordEncoder.encode(usuarioUpdateRequest.password()));
        usuarioRepository.save(usuario);
        return new ApiResult<>(true,"Usuario actualizado", usuarioMapper.toUsuarioResponse(usuario));
    }
    public ApiResult<Void> deleteUer(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Usuario usuario = usuarioRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
        usuario.setActive(false);
        usuarioRepository.save(usuario);
        return new ApiResult<>(true,"usuario eliminado",null);
    }
}
