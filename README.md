# Entrega Parcial - Sustento Técnico

## B1. Modelo de datos y entidades JPA
Se diseñó el modelo con las entidades Cliente, Contacto, Oportunidad y Usuario. Cada una usa `@Entity` y `@Table` para representar tablas de la base de datos. Los atributos principales se definieron con `@Column`, incluyendo restricciones como `nullable = false` y `unique = true` cuando aplica.

Las relaciones entre entidades se manejan con `@OneToMany` y `@ManyToOne`. Por ejemplo, un Cliente puede tener muchos Contactos y muchas Oportunidades, mientras que cada Contacto y cada Oportunidad pertenecen a un solo Cliente.

El uso de `mappedBy` se aplica en el lado inverso de una relación bidireccional, cuando la llave foránea ya está controlada por la otra entidad. Esto evita duplicar la relación y permite que JPA entienda cuál lado es el dueño de la asociación. En este proyecto se usa para indicar que Cliente expone colecciones relacionadas, pero la llave foránea realmente está en Contacto u Oportunidad.

## B2. Repositorios y servicios
Se definieron repositorios con `JpaRepository` para Cliente, Contacto, Oportunidad y Usuario, lo que permite operaciones CRUD sin escribir implementaciones manuales.

En la capa de servicio se centralizó la lógica de negocio. Por ejemplo, crear cliente valida duplicados, listar clientes consulta el repositorio, asociar contacto verifica que el cliente exista y eliminar cliente se restringe por permisos.

El principio SRP se aplica separando responsabilidades: el controlador solo recibe y responde peticiones HTTP, el servicio contiene la lógica de negocio y validaciones, y el repositorio se encarga del acceso a datos. De esta manera cada clase tiene una única responsabilidad clara.

## B3. API REST y DTOs
Los endpoints se diseñaron usando DTOs de entrada y salida para evitar exponer directamente las entidades.

Endpoints principales:

- POST `/clientes` -> crea un cliente. Recibe `ClienteRequestDTO`, responde `ClienteResponseDTO`, código esperado `201 Created`.
- GET `/clientes/{id}` -> consulta cliente por ID. Responde `ClienteResponseDTO`, código esperado `200 OK`.
- GET `/clientes` -> lista clientes. Responde lista de `ClienteResponseDTO`, código esperado `200 OK`.
- PUT `/clientes/{id}` -> actualiza cliente. Recibe `ClienteRequestDTO`, responde `ClienteResponseDTO`, código esperado `200 OK`.
- DELETE `/clientes/{id}` -> elimina cliente. Código esperado `204 No Content` o `200 OK`, según implementación.
- POST `/clientes/{id}/oportunidades` -> crea oportunidad para un cliente. Recibe `OportunidadRequestDTO`, responde `OportunidadResponseDTO`, código esperado `201 Created`.

## B4. Validación y manejo de excepciones
Se aplicaron validaciones con `@NotBlank`, `@NotNull`, `@Email` y `@Size` en los DTOs para garantizar que los datos recibidos sean correctos antes de llegar a la lógica de negocio.

El manejo de errores se centralizó con `@RestControllerAdvice`. Cuando un cliente no existe, se retorna un error controlado de tipo `404 Not Found`. Cuando el correo tiene formato inválido o faltan campos obligatorios, se responde con `400 Bad Request`. Cuando un usuario intenta una acción sin permisos suficientes, se retorna `403 Forbidden`.

## B5. Seguridad con Spring Security 6 y JWT
Se implementó autenticación con JWT y una configuración stateless, lo que significa que el servidor no mantiene sesiones. Cada petición protegida debe enviar un token válido.

Se definieron roles `ADMIN`, `VENDEDOR` y `LECTOR`. La restricción principal del enunciado se cumple permitiendo que solo `ADMIN` pueda eliminar clientes. Los demás accesos se controlan según la configuración de seguridad establecida.

## B6. Uso responsable de IA
Se utilizó IA como apoyo para estructurar la solución base, organizar la arquitectura, proponer validaciones, sugerir configuración de seguridad y mejorar la documentación.

Sin embargo, el código fue revisado, adaptado y probado antes de usarse. Copiar una solución sin validarla podría generar errores de compilación, fallos de seguridad, malas prácticas de diseño o incumplimiento de los requisitos del parcial.
