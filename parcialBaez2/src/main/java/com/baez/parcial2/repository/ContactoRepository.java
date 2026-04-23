package com.baez.parcial2.repository;

import com.baez.parcial2.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
