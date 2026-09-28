package com.daiquiriclub.app.springbootv1.controller;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.request.UsuarioUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.usuario.response.UsuarioResponse;
import com.daiquiriclub.app.springbootv1.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public ResponseEntity<ApiResult<List<UsuarioResponse>>> getAllUsers(){
        return ResponseEntity.ok(usuarioService.getAllUsers());
    }
    @GetMapping("/active")
    public ResponseEntity<ApiResult<List<UsuarioResponse>>> getAllUsersActive(){
        return ResponseEntity.ok(usuarioService.getAllUsersActive());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<UsuarioResponse>>getByIdUsuario(@PathVariable String id){
        return ResponseEntity.ok(usuarioService.getByIdUser(id));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResult<UsuarioResponse>>createUsuario(@RequestBody @Valid UsuarioCreateRequest usuarioCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuer(usuarioCreateRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<UsuarioResponse>>updateUser(@PathVariable String id, @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest){
        return ResponseEntity.ok(usuarioService.updatedUser(id,usuarioUpdateRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<Void>>deleteUser(@PathVariable String id){
        return ResponseEntity.ok(usuarioService.deleteUer(id));
    }
}
