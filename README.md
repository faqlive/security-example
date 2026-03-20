# security-example

Proyecto de aprendizaje de seguridad en Java.

## Objetivo

Construir una API segura con tres enfoques de autenticación:

- Login tradicional (usuario/contraseña)
- OAuth2
- SAML

## Estructura

La organización del código es **por funcionalidades** (feature-first).
Consulta [`STRUCTURE.md`](./STRUCTURE.md) para el detalle completo.

## Estado actual (MVP inicial)

Autenticación local implementada de forma básica:

- `POST /api/auth/local/register`
- `POST /api/auth/local/login`

Ambos endpoints devuelven un token JWT (modo aprendizaje).
OAuth2 y SAML quedaron preparados como módulos para la siguiente fase.
