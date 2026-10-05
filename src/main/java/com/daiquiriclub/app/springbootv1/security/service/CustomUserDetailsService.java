package com.daiquiriclub.app.springbootv1.security.service;

import com.daiquiriclub.app.springbootv1.entity.Usuario;
import com.daiquiriclub.app.springbootv1.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreo(correo).orElseThrow(
                ()-> new UsernameNotFoundException("El usuario no existe")
        );
    }
}
