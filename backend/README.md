# Backend Prueba Practica

Este proyecto es el backend en spring para una app ejemplo de manejo de productos y categorías.
Ha sido desarrollado utilizando **Spring Boot 3.3.7**, **Java 17**, y se despliega en un servidor **Apache Tomcat 10**. 
El sistema implementa funcionalidades RESTful, autenticación básica, y manejo de CORS para integrarse con un frontend desarrollado en Angular.

## Características Principales

- **Autenticación:**
  - Basada en autenticación básica.
  - Roles configurados: `ADMIN` y `USER`.
  - Protección de endpoints según el rol del usuario.

- **API RESTful:**
  - Operaciones CRUD para productos y categorías.
  - Validaciones integradas para los datos enviados al backend.

- **Base de Datos en Memoria:**
  - Utiliza **H2 Database** para almacenamiento temporal de datos durante el desarrollo.
  - Los esquemas de las tablas se generan automáticamente al iniciar la aplicación.

- **CORS Habilitado:**
  - Desde `http://localhost:4200` (frontend Angular) caso muy comun que pasa trabajando con estos jsframeworks, en productivo si vivien en el mismo dominio se corrije

---

## Requisitos Previos

1. **Java:**  17.
2. **Apache Tomcat:** 10.
3. **Maven:**  3.9 .
4. **IDE Recomendado:** IntelliJ IDEA.

---

## Configuración

### Archivo `application.properties`

La configuración principal se encuentra en el archivo `src/main/resources/application.properties`:

```properties
# Configuración de la base de datos H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Consola H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Puerto de la aplicación
server.port=8080
```

---



## Endpoints 

### **Login**
- **POST** `/api/login`
  - **Cuerpo:**
    ```json
    {
      "username": "admin",
      "password": "password"
    }
    ```
  - **Respuesta:** Devuelve un token de sesión en una cookie.

### **Productos**
- **GET** `/api/productos`
  - Devuelve la lista de productos.

- **POST** `/api/productos`
  - **Cuerpo:**
    ```json
    {
      "nombre": "Producto 1",
      "precio": 100.0,
      "categoria": { "id": 1 }
    }
    ```

- **PUT** `/api/productos/{id}`
  - Actualiza un producto existente.

- **DELETE** `/api/productos/{id}`
  - Elimina un producto.

### **Categorías**
- **GET** `/api/categorias`
  - Devuelve la lista de categorías.

- **POST** `/api/categorias`
  - **Cuerpo:**
    ```json
    {
      "nombre": "Categoría 1"
    }
    ```

- **PUT** `/api/categorias/{id}`
  - Actualiza una categoría existente.

- **DELETE** `/api/categorias/{id}`
  - Elimina una categoría.

---

## Seguridad

La seguridad está configurada en la clase `SecurityConfig.java`.

- **Roles Disponibles:**
  - `ADMIN`: Acceso a todos los endpoints.
  - `USER`: Acceso limitado a endpoints de consulta.

- **Autenticación:**
  - Basada en credenciales enviadas en la cabecera `Authorization` (Basic Auth).

- **Configuración CORS:**
  - Permite solicitudes desde `http://localhost:4200` para integrarse con el frontend.

---

## Ejecución Local

1. **Clonar el Repositorio:**
   ```bash
   git clone <url-del-repositorio>
   cd pruebapractica
   ```

2. **Construir el Proyecto:**
   ```bash
   mvn clean install
   ```

3. **Generar el WAR:**
   ```bash
   mvn package
   ```

4. **Desplegar en Tomcat:**
   - Copiar el archivo `pruebapractica.war` al directorio `webapps` de Tomcat.

5. **Acceder a la Aplicación:**
   - URL: `http://localhost:8080/pruebapractica`

---

## Pruebas

### Pruebas con Postman
Se incluye una colección de Postman (`postman_collection.json`) para probar los endpoints.


---

## Autor
Desarrollado por **jmf**.

---

## Licencia
Sin Licenciamiento
