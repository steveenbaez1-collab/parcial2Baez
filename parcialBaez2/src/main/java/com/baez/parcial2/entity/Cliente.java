package com.baez.parcial2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, unique = true, length = 30)
    private String nit;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 180)
    private String direccion;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Contacto> contactos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Oportunidad> oportunidades = new ArrayList<>();
}
