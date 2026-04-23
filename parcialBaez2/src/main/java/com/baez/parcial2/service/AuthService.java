package com.baez.parcial2.service;

import com.baez.parcial2.dto.request.LoginRequest;
import com.baez.parcial2.dto.request.RegisterRequest;
import com.baez.parcial2.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
