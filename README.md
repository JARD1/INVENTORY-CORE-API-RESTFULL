# InventoriCore API RESTful 📦

Una API RESTful robusta y escalable desarrollada en Java con Spring Boot para la gestión integral de un inventario. Incluye control de **Productos**, **Proveedores** y un sistema completo de **Autenticación y Autorización utilizando tokens JWT**.

## 🚀 Tecnologías Utilizadas

* **Java 21**
* **Spring Boot 3.2.4**
    * Spring Web (Creación de API REST)
    * Spring Data JPA / Hibernate (Manejo de Base de Datos)
    * Spring Security (Protección de rutas)
* **MySQL** (Base de datos relacional)
* **JSON Web Tokens (JWT)** (Autenticación sin estado)
* **Lombok** (Reducción de código repetitivo)
* **Maven** (Gestor de dependencias)

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura híbrida (Capas + Módulos) recomendada para escalabilidad, aplicando principios SOLID:

com.jorgedev.inventoriCoreApiRestFull

 ┣ 📂 auth           # Módulo de autenticación (Login/Registro, DTOs de Auth)
 
 ┣ 📂 config         # Configuraciones globales (Beans, SecurityConfig)
 
 ┣ 📂 controllers    # Endpoints de la API (Productos, Proveedores)
 
 ┣ 📂 dtos           # Objetos de Transferencia de Datos
 
 ┣ 📂 entities       # Modelos de la base de datos mapeados con JPA
 
 ┣ 📂 repositories   # Interfaces para operaciones CRUD en MySQL
 
 ┣ 📂 security       # Filtros JWT, Servicios de Token y Entidad de Usuario
 
 ┗ 📂 services       # Lógica de negocio (Interfaces y sus implementaciones 'impl')

## ⚙️ Requisitos Previos

Antes de ejecutar este proyecto localmente, asegúrate de tener instalado:
* Java Development Kit (JDK) 21
* Maven
* Servidor MySQL ejecutándose localmente

## 🛠️ Instalación y Configuración

1. Clonar el repositorio:
   git clone https://github.com/tu-usuario/inventoriCoreApiRestFull.git

2. Configurar la Base de Datos:
   Crea una base de datos en tu servidor MySQL.

3. Configurar Variables de Entorno:
   Actualiza las credenciales en el archivo src/main/resources/application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña_aqui
   spring.jpa.hibernate.ddl-auto=update

4. Ejecutar la aplicación:
   mvn spring-boot:run
   
   La API estará disponible en http://localhost:8081

---

## 🔐 Documentación de Endpoints

Toda la API (excepto las rutas de registro y login) está protegida por Spring Security. Para consumir los endpoints protegidos, debes incluir el Token JWT en los Headers de la petición HTTP:
Authorization: Bearer <tu_token_jwt>

### 👤 Rutas Públicas (Autenticación)

1. Registrar un nuevo usuario
* Endpoint: POST /auth/register
* Body:
  {
    "username": "jorge",
    "password": "123"
  }
* Respuesta (200 OK): Devuelve el token generado.

2. Iniciar Sesión
* Endpoint: POST /auth/login
* Body:
  {
    "username": "jorge",
    "password": "123"
  }
* Respuesta (200 OK): Devuelve el token generado.

---

### 🏢 Rutas Protegidas: Proveedores

1. Crear un Proveedor
* Endpoint: POST /api/v1/proveedores
* Body:
  {
    "nombre": "Distribuidora Tech C.A.",
    "contacto": "Juan Pérez",
    "telefono": "+584141234567",
    "email": "ventas@distribuidoratech.com"
  }
* Respuesta (201 Created): Retorna el proveedor creado con su ID generado.

2. Listar todos los Proveedores
* Endpoint: GET /api/v1/proveedores
* Body: No requiere.
* Respuesta (200 OK): Retorna un arreglo con todos los proveedores.

3. Obtener Proveedor por ID
* Endpoint: GET /api/v1/proveedores/{id}
* Body: No requiere.
* Respuesta (200 OK): Retorna el objeto del proveedor solicitado.

4. Actualizar un Proveedor
* Endpoint: PUT /api/v1/proveedores/{id}
* Body:
  {
    "nombre": "Distribuidora Tech C.A. Actualizada",
    "contacto": "Pedro Pérez",
    "telefono": "+584141234567",
    "email": "nuevo@distribuidoratech.com"
  }
* Respuesta (200 OK): Retorna el proveedor actualizado.

5. Eliminar un Proveedor
* Endpoint: DELETE /api/v1/proveedores/{id}
* Body: No requiere.
* Respuesta (204 No Content): Operación exitosa, sin contenido de retorno.

---

### 📦 Rutas Protegidas: Productos

1. Crear un Producto
* Endpoint: POST /api/v1/productos
* Body:
  {
    "nombre": "Laptop Gamer XYZ",
    "descripcion": "Laptop de alto rendimiento",
    "precio": 1500.50,
    "cantidad": 10
  }
* Respuesta (201 Created): Retorna el producto creado con su ID generado.

2. Listar todos los Productos
* Endpoint: GET /api/v1/productos
* Body: No requiere.
* Respuesta (200 OK): Retorna un arreglo con todos los productos.

3. Obtener Producto por ID
* Endpoint: GET /api/v1/productos/{id}
* Body: No requiere.
* Respuesta (200 OK): Retorna el objeto del producto solicitado.

4. Actualizar un Producto
* Endpoint: PUT /api/v1/productos/{id}
* Body:
  {
    "nombre": "Laptop Gamer XYZ V2",
    "descripcion": "Modelo actualizado",
    "precio": 1450.00,
    "cantidad": 15
  }
* Respuesta (200 OK): Retorna el producto actualizado.

5. Eliminar un Producto
* Endpoint: DELETE /api/v1/productos/{id}
* Body: No requiere.
* Respuesta (204 No Content): Operación exitosa, sin contenido de retorno.

---

## 👨‍💻 Autor
Desarrollado por Jorge Diaz.
