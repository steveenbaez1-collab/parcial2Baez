# Entrega parcialBaez2

## B1. Modelo de datos y entidades JPA
- `Cliente`: `@Entity`, `@Table(name="clientes")`, PK `id`, atributos `nombre`, `nit`, `email`, `telefono`, `direccion`, `fechaCreacion`.
- `Contacto`: `@Entity`, `@Table(name="contactos")`, PK `id`, relación `@ManyToOne` con `Cliente`.
- `Oportunidad`: `@Entity`, `@Table(name="oportunidades")`, PK `id`, relación `@ManyToOne` con `Cliente` y `Usuario`.
- `Usuario`: `@Entity`, `@Table(name="usuarios")`, PK `id`, atributos `nombre`, `email`, `password`, `role`.
- Cardinalidades:
  - Un cliente tiene muchos contactos: `Cliente 1:N Contacto`.
  - Un cliente tiene muchas oportunidades: `Cliente 1:N Oportunidad`.
  - Un usuario puede estar asociado a muchos clientes y oportunidades.
- `mappedBy` se usa en `Cliente` para indicar que la FK vive en `Contacto` y `Oportunidad`, evitando tablas intermedias innecesarias.

## B2. Repositorios y servicios
- Repositorios con `JpaRepository`: `ClienteRepository`, `ContactoRepository`, `OportunidadRepository`, `UsuarioRepository`.
- Servicio principal: `ClienteService`.
- Casos cubiertos:
  - crear cliente
  - listar clientes
  - consultar cliente por id
  - actualizar cliente
  - eliminar cliente
  - asociar contacto a cliente
  - crear oportunidad para cliente
- SRP aplicado:
  - Controlador expone HTTP.
  - Servicio contiene reglas de negocio.
  - Repositorio persiste.
  - Mapper convierte DTO <-> entidad.

## B3. API REST y DTOs
- `POST /clientes` -> `ClienteRequest` -> `201`
- `GET /clientes/{id}` -> `200`
- `GET /clientes` -> `200`
- `PUT /clientes/{id}` -> `ClienteRequest` -> `200`
- `DELETE /clientes/{id}` -> `200`
- `POST /clientes/{clienteId}/oportunidades` -> `OportunidadRequest` -> `201`
- Adicional: `POST /clientes/{clienteId}/contactos` -> `ContactoRequest` -> `201`

## B4. Validación y excepciones
- Se usan `@NotBlank`, `@NotNull`, `@Email`, `@Size`, `@DecimalMin`, `@FutureOrPresent`.
- `@RestControllerAdvice` centraliza errores.
- Casos manejados:
  - cliente no existe -> `404`
  - correo inválido o datos inválidos -> `400`
  - duplicados -> `409`
  - usuario sin permiso -> `403`
  - sin autenticación -> `401`

## B5. Seguridad Spring Security 6 y JWT
- Autenticación con JWT stateless.
- Roles implementados: `ADMIN`, `VENDEDOR`, `LECTOR`.
- Restricciones:
  - GET `/clientes/**`: ADMIN, VENDEDOR, LECTOR
  - POST/PUT `/clientes/**`: ADMIN, VENDEDOR
  - DELETE `/clientes/**`: solo ADMIN
- Endpoints públicos: `/auth/register`, `/auth/login`.

## B6. Uso responsable de IA
- Se usó IA para reorganizar el proyecto, limpiar el alcance y generar una base técnica coherente con el parcial.
- Se validó la arquitectura por capas, seguridad, DTOs y entidades antes de entregarlo.
- Riesgo de copiar sin validar: errores de compilación, relaciones JPA mal diseñadas, endpoints inseguros o inconsistencias con la rúbrica.
- Decisiones técnicas propias: cambiar el dominio a CRM, dejar solo módulos exigidos, definir roles, diseñar entidades y mantener separación controlador-servicio-repositorio-mapper.
