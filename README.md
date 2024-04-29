# Instrucciones de uso

1. Clona este repositorio en tu máquina local: https://github.com/victorfelipealvarezrojas/SENTRA-backend-developer.git
2. Navega hasta el directorio del proyecto:
3. Compila el proyecto con Maven:
4. Ejecuta la aplicación Spring Boot:
   La aplicación debería estar disponible en `http://localhost:8080/pi/users`.

   ## Endpoints

### POST /api/users/register
Este endpoint se utiliza para registrar un nuevo usuario. Recibe un objeto `UserDto` en el cuerpo de la solicitud con los siguientes campos:

- `name` (string): Nombre del usuario
- `email` (string): Correo electrónico del usuario
- `password` (string): Contraseña del usuario
- `phones` (array de objetos): Lista de números de teléfono del usuario

Si el registro es exitoso, responderá con un código de estado HTTP 201 (Created) y un objeto `UserDtoResponse` que contiene la información del usuario recién creado, incluyendo un token de acceso.

### PUT /api/users/{id}
Este endpoint se utiliza para actualizar la información de un usuario existente. Recibe un objeto `UserDto` en el cuerpo de la solicitud con los campos que se desean actualizar, y el `id` del usuario en la URL. Si la actualización es exitosa, responderá con un código de estado HTTP 200 (OK) y un objeto `UserDtoResponse` con la información actualizada del usuario.

### GET /api/users
Este endpoint devuelve una lista de todos los usuarios registrados. Si la solicitud es exitosa, responderá con un código de estado HTTP 200 (OK) y una lista de objetos `UserDtoResponse`.

### GET /api/users/{id}
Este endpoint devuelve la información de un usuario específico. Recibe el `id` del usuario en la URL. Si el usuario existe, responderá con un código de estado HTTP 200 (OK) y un objeto `UserDtoResponse` con la información del usuario.

### DELETE /api/users/{id}
Este endpoint elimina un usuario existente. Recibe el `id` del usuario en la URL. Si la eliminación es exitosa, responderá con un código de estado HTTP 200 (OK) y un mensaje de confirmación.

# Dependencias
Este proyecto utiliza las siguientes dependencias principales:

- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Web
- Spring Boot Starter Validation
- JSON Web Token (JJWT)
- H2 Database
- Lombok
- Spring Boot Starter Test
- Spring Security Test

Puedes encontrar más detalles en el archivo `pom.xml`.

