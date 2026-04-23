package com.baez.parcial2.config;

import com.baez.parcial2.entity.Role;
import com.baez.parcial2.entity.Usuario;
import com.baez.parcial2.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        crearSiNoExiste("Administrador", "admin@crm.com", "Admin123*", Role.ROLE_ADMIN);
        crearSiNoExiste("Vendedor Demo", "vendedor@crm.com", "Vendedor123*", Role.ROLE_VENDEDOR);
        crearSiNoExiste("Lector Demo", "lector@crm.com", "Lector123*", Role.ROLE_LECTOR);
    }

    private void crearSiNoExiste(String nombre, String email, String rawPassword, Role role) {
        if (!usuarioRepository.existsByEmail(email)) {
            usuarioRepository.save(Usuario.builder()
                    .nombre(nombre)
                    .email(email)
                    .password(passwordEncoder.encode(rawPassword))
                    .role(role)
                    .build());
        }
    }
}
