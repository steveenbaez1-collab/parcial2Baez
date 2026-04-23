package com.baez.parcial2.security;

import com.baez.parcial2.dto.response.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status("error")
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Autenticación requerida")
                .errors(List.of(authException.getMessage()))
                .timestamp(OffsetDateTime.now())
                .path(request.getRequestURI())
                .build();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
