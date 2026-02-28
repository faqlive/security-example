# Estructura del proyecto (feature-first)

Este proyecto está organizado por **funcionalidades** para que sea más fácil aprender seguridad por módulos.

## Carpetas raíz

- `docs/` → documentación funcional y de seguridad.
- `scripts/` → scripts de apoyo (arranque local, utilidades, pruebas rápidas).
- `src/main/java/com/faqlive/securityexample/` → código principal.
- `src/main/resources/` → configuración y migraciones.
- `src/test/java/com/faqlive/securityexample/` → tests organizados por funcionalidad.

## Paquetes principales

- `common/` → utilidades transversales y componentes reutilizables.
- `config/` → configuración de Spring Security, beans, filtros globales.
- `shared/` → contratos compartidos (DTOs base, errores comunes, etc.).
- `features/authlocal/` → login/registro tradicional (email + password).
- `features/authoauth2/` → autenticación OAuth2 (por ejemplo Google/GitHub).
- `features/authsaml/` → autenticación SAML.
- `features/users/` → perfil/gestión de usuario.
- `features/notes/` → dominio principal de notas seguras.
- `features/admin/` → funciones administrativas.

## Convención interna por feature

Dentro de cada feature se recomienda mantener subpaquetes como:

- `controller/`
- `service/`
- `domain/` (entidades y reglas de negocio)
- `repository/`
- `dto/`
- `mapper/`
- `security/` (si aplica)

Esto permite aislar responsabilidades y aprender seguridad en contexto real.
