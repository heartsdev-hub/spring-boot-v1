package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Request.ProveedorCreateRequest;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Request.ProveedorUpdateRequest;
import com.daiquiriclub.app.springbootv1.dto.Proveedor.Response.ProveedorResponse;
import com.daiquiriclub.app.springbootv1.entity.Proveedor;
import com.daiquiriclub.app.springbootv1.exception.BadRequestException;
import com.daiquiriclub.app.springbootv1.exception.ConflictException;
import com.daiquiriclub.app.springbootv1.exception.ResourceNotFoundException;
import com.daiquiriclub.app.springbootv1.mapper.ProveedorMapper;
import com.daiquiriclub.app.springbootv1.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;
    private final ProveedorMapper proveedorMapper;

    public ProveedorService(ProveedorRepository proveedorRepository, ProveedorMapper proveedorMapper) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorMapper = proveedorMapper;
    }
    public ApiResult<List<ProveedorResponse>> getAllProveedor(){
        List<ProveedorResponse> proveedores =
                proveedorRepository.findAll().stream().map(proveedorMapper::toProveedorResponse).toList();
        return new ApiResult<>(true,"All proveedores", proveedores);
    }
    public  ApiResult<List<ProveedorResponse>> getAllProveedorActive(){
        List<ProveedorResponse> proveedores = proveedorRepository.findByActiveTrue().stream().map(proveedorMapper::toProveedorResponse).toList();
        return new ApiResult<>(true, "All proveedores active", proveedores);
    }
    public ApiResult<ProveedorResponse> getByIdProveedor(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Proveedor proveedor = proveedorRepository.findById(uuid).orElseThrow(
                ()-> new ResourceNotFoundException("Proveedor not found")
        );
        return new ApiResult<>(true,"Proveedor",proveedorMapper.toProveedorResponse(proveedor));
    }
    public ApiResult<ProveedorResponse> createProveedor(ProveedorCreateRequest proveedorCreateRequest){
        if(proveedorRepository.existsByCorreo(proveedorCreateRequest.correo())){
            throw new ConflictException("Ya existe el correo");
        }
        if(proveedorRepository.existsByRuc(proveedorCreateRequest.ruc())){
            throw new ConflictException("Ya existe el ruc");
        }
        Proveedor proveedor = proveedorMapper.toProveedor(proveedorCreateRequest);
        ProveedorResponse proveedorResponse = proveedorMapper.toProveedorResponse(proveedorRepository.save(proveedor));
        return new ApiResult<>(true,"Category created", proveedorResponse);
    }
    public ApiResult<ProveedorResponse> updateProveedor(String id, ProveedorUpdateRequest proveedorUpdateReques){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new BadRequestException("UUID invalid");
        }
        Proveedor proveedor = proveedorRepository.findById(uuid).orElseThrow(
                ()-> new ResourceNotFoundException("Proveedor not found")
        );
        proveedorMapper.updateProveedor(proveedorUpdateReques,proveedor);
        proveedorRepository.save(proveedor);
        return new ApiResult<>(true,"proveedor updated", proveedorMapper.toProveedorResponse(proveedor));
    }
    public ApiResult<Void> deleteProveedor(String id){
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("UUID invalid");
        }
        Proveedor proveedor = proveedorRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException("Proveedor not found"));
        proveedorRepository.delete(proveedor);
        return new ApiResult<>(true,"Proveedor deleted",null);
    }
}
