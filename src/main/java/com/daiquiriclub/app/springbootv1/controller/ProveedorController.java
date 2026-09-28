package com.daiquiriclub.app.springbootv1.controller;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Request.ProveedorCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Request.ProveedorUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Response.ProveedorResponse;
import com.daiquiriclub.app.springbootv1.dto.product.response.ProductResponse;
import com.daiquiriclub.app.springbootv1.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }
    @GetMapping
    public ResponseEntity<ApiResult<List<ProveedorResponse>>>getAllProveedor(){
        return ResponseEntity.ok(proveedorService.getAllProveedor());
    }
    @GetMapping("/active")
    public ResponseEntity<ApiResult<List<ProveedorResponse>>>getAllProveedoActive(){
        return ResponseEntity.ok(proveedorService.getAllProveedorActive());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<ProveedorResponse>>getByIdProveedor(@PathVariable String id){
        return ResponseEntity.ok(proveedorService.getByIdProveedor(id));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResult<ProveedorResponse>>createProveedor(@RequestBody @Valid ProveedorCreateRequest proveedorCreateRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.createProveedor(proveedorCreateRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResult<ProveedorResponse>>updateProveedor(@PathVariable String id, @RequestBody @Valid ProveedorUpdateRequest proveedorUpdateRequest){
        return ResponseEntity.ok(proveedorService.updateProveedor(id,proveedorUpdateRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<Void>>deleteProveedor(@PathVariable String id){
        return ResponseEntity.ok(proveedorService.deleteProveedor(id));
    }
}
