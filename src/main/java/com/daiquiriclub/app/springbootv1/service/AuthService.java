package com.daiquiriclub.app.springbootv1.service;

import com.daiquiriclub.app.springbootv1.dto.ApiResult;
import com.daiquiriclub.app.springbootv1.dto.auth.AuthRequest;
import com.daiquiriclub.app.springbootv1.dto.auth.AuthResponse;
import com.daiquiriclub.app.springbootv1.exception.BadRequestException;
import com.daiquiriclub.app.springbootv1.exception.UnauthorizedException;
import com.daiquiriclub.app.springbootv1.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;

    public AuthService(AuthenticationProvider authenticationProvider, JwtService jwtService) {
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
    }
    public ApiResult<AuthResponse> login(AuthRequest authRequest){
        try{
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequest.correo(),authRequest.password()));
            String token = jwtService.generateToken(authRequest.correo());
            return new ApiResult<>(
                    true,
                    "Login exitoso",
                    new AuthResponse(token)
            );

        }catch (BadCredentialsException e){
            throw new UnauthorizedException("Correo o contraseña incorrectos");
        }
    }
}
