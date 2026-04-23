package com.baez.parcial2.repository;

import com.baez.parcial2.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByNit(String nit);
    boolean existsByEmail(String email);
    boolean existsByNitAndIdNot(String nit, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
