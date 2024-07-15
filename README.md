# ForoHub

ForoHub es una aplicación web de foro construida con Spring Boot, utilizando JWT para la autenticación y seguridad. La aplicación permite a los usuarios crear, leer, actualizar y eliminar tópicos (temas de discusión). También incluye funcionalidades para registrar y autenticar usuarios.

## Características

- **Autenticación y Autorización**: Utiliza JWT para la autenticación y seguridad de las API.
- **CRUD de Tópicos**: Permite crear, leer, actualizar y eliminar tópicos.
- **Validación de datos**: Implementa validaciones para los datos de los tópicos y usuarios.
- **Seguridad Stateless**: Implementa una política de sesión stateless.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Lombok**

## Requisitos Previos

- **Java 17**
- **Maven**
- **MySQL**

## Configuración del Proyecto

1. **Clonar el repositorio**

    ```bash
    git clone https://github.com/tu_usuario/forohub.git
    cd forohub
    ```

2. **Configurar la base de datos**

    Crea una base de datos en MySQL:

    ```sql
    CREATE DATABASE forohub2;
    ```

    Asegúrate de crear las propiedades de la base de datos en `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:5500/forohub2
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña

    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    server.error.include-stacktrace=never

    hibernate.hbm2ddl.auto = update
    api.security.secret = ${JWT_SECRET:123456}
    ```

## Endpoints de la API

### Autenticación

- **`POST /login`**: Autenticar un usuario y recibir un token JWT.

### Usuarios

- **`POST /usuarios`**: Registrar un nuevo usuario.

### Tópicos

- **`GET /topicos`**: Obtener todos los tópicos.
- **`GET /topicos/{id}`**: Obtener un tópico por ID.
- **`POST /topicos`**: Crear un nuevo tópico.
- **`PUT /topicos/{id}`**: Actualizar un tópico existente.
- **`DELETE /topicos/{id}`**: Eliminar un tópico.
