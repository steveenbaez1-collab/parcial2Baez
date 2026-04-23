# parcialBaez2

API REST CRM simplificada para cumplir el parcial:
- Entidades: Cliente, Contacto, Oportunidad, Usuario
- DTOs de entrada y salida
- Servicios y repositorios con JPA
- Validaciones con Bean Validation
- Manejo global de excepciones
- Seguridad con Spring Security 6 + JWT + API stateless
- Roles: ADMIN, VENDEDOR, LECTOR

## Credenciales de prueba
- admin@crm.com / Admin123*
- vendedor@crm.com / Vendedor123*
- lector@crm.com / Lector123*

## Endpoints principales
- POST `/auth/register`
- POST `/auth/login`
- POST `/clientes`
- GET `/clientes`
- GET `/clientes/{id}`
- PUT `/clientes/{id}`
- DELETE `/clientes/{id}`
- POST `/clientes/{clienteId}/contactos`
- POST `/clientes/{clienteId}/oportunidades`

## Reglas de acceso
- GET: ADMIN, VENDEDOR, LECTOR
- POST/PUT: ADMIN, VENDEDOR
- DELETE clientes: solo ADMIN
