package com.baez.parcial2.service.impl;

import com.baez.parcial2.dto.request.LoginRequest;
import com.baez.parcial2.dto.request.RegisterRequest;
import com.baez.parcial2.dto.response.AuthResponse;
import com.baez.parcial2.entity.Usuario;
import com.baez.parcial2.exception.custom.DuplicateResourceException;
import com.baez.parcial2.mapper.UsuarioMapper;
import com.baez.parcial2.repository.UsuarioRepository;
import com.baez.parcial2.security.JwtService;
import com.baez.parcial2.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
                           UsuarioMapper usuarioMapper, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.usuarioMapper = usuarioMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Ya existe un usuario con el email " + request.getEmail());
        }

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        Usuario saved = usuarioRepository.save(usuario);
        String token = jwtService.generateToken(saved);

        return AuthResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpiration())
                .role(saved.getRole())
                .user(usuarioMapper.toResponse(saved))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generateToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpiration())
                .role(usuario.getRole())
                .user(usuarioMapper.toResponse(usuario))
                .build();
    }
}
