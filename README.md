# Microservicios Tienda de Electrodomésticos

Proyecto de aprendizaje desarrollado con **Spring Boot** que implementa una arquitectura de microservicios. El sistema simula una plataforma de venta de electrodomésticos con tres servicios independientes que se comunican entre sí.

> ⚠️ Este proyecto tiene fines educativos

---

## Arquitectura

```
Cliente
   ↓
API Gateway (9007)
   ↓
┌──────────────────────────────────────────────┐
│  Service Producto (9770)                     │
│  Service Carrito  (9768)   ← Feign entre     │
│  Service Ventas   (9769)      servicios      │
└──────────────────────────────────────────────┘
   ↓
Eureka Server (8762) — Service Registry
   ↓
┌──────────────────────────────────────────────┐
│  MySQL Producto (3326)                       │
│  MySQL Carrito  (3327)                       │
│  MySQL Ventas   (3338)                       │
└──────────────────────────────────────────────┘
```

---

## Microservicios

### Service Producto
Gestiona el catálogo de electrodomésticos disponibles.

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/productos` | Lista todos los productos |
| GET | `/api/v1/productos/{id}` | Obtiene un producto por ID |
| POST | `/api/v1/productos` | Crea un nuevo producto |
| PUT | `/api/v1/productos/{id}` | Actualiza un producto |
| DELETE | `/api/v1/productos/{id}` | Elimina un producto |

### Service Carrito
Gestiona el carrito de compras de los usuarios.

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/carrito` | Lista todos los carritos |
| GET | `/api/v1/carrito/{id}` | Obtiene un carrito por ID |
| POST | `/api/v1/carrito` | Crea un nuevo carrito |
| DELETE | `/api/v1/carrito/{id}` | Elimina un carrito |
| PUT | `/api/v1/carrito/{id}/agregar/{productoId}` | Añade un producto al carrito |
| PUT | `/api/v1/carrito/{id}/quitar/{productoId}` | Elimina un producto del carrito |
| PUT | `/api/v1/carrito/cambiar_estado/{id}?estado=true/false` | Activa o desactiva un carrito |

### Service Ventas
Registra y gestiona las ventas realizadas.

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/venta` | Lista todas las ventas |
| GET | `/api/v1/venta/{id}` | Obtiene una venta por ID |
| POST | `/api/v1/venta/{id}` | Crea una venta a partir del ID de un carrito |
| DELETE | `/api/v1/venta/{id}` | Elimina una venta y reactiva el carrito |

---

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Cloud Netflix Eureka** — Service Registry
- **Spring Cloud Gateway** — API Gateway
- **Spring Cloud OpenFeign** — Comunicación entre microservicios
- **Resilience4j** — Circuit Breaker
- **Spring Data JPA + Hibernate** — Acceso a datos
- **MySQL 8.0** — Base de datos
- **Docker + Docker Compose** — Contenedorización
- **Lombok** — Reducción de boilerplate
- **Maven** — Gestión de dependencias

---

## Requisitos previos

- [Docker](https://www.docker.com/) y Docker Compose instalados
- Puertos 9007, 9770, 9768, 9769, 8762, 3326, 3327 y 3338 disponibles

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/josepllopis/springboot-microservices-demo.git
cd springboot-microservices-demo
```

### 2. Crear el fichero `.env`

Crea un fichero `.env` en la raíz del proyecto basándote en este `.env.example`:


Editalo con tus propios valores:

```
MYSQL_ROOT_PASSWORD=tu_password
MYSQL_DATABASE_PRODUCTO=nombreBBDDProducto
MYSQL_DATABASE_CARRITO=nombreBBDDCarrito
MYSQL_DATABASE_VENTAS=nombreBBDDVentas
DATASOURCE_URL_PRODUCTO=jdbc:mysql://mysql-producto:3306/nombreBBDDProducto
DATASOURCE_URL_CARRITO=jdbc:mysql://mysql-carrito:3306/nombreBBDDCarrito
DATASOURCE_URL_VENTAS=jdbc:mysql://mysql-ventas:3306/nombreBBDDVentas
DATASOURCE_USERNAME=root
DATASOURCE_PASSWORD=tu_password
EUREKA_URL=http://eureka-server:8762/eureka
```

> ⚠️ El nombre que pongas en `MYSQL_DATABASE_PRODUCTO` debe coincidir con el nombre al final de `DATASOURCE_URL_PRODUCTO`. Lo mismo aplica para carrito y ventas.

> ⚠️ `MYSQL_ROOT_PASSWORD` y `DATASOURCE_PASSWORD` deben tener el mismo valor.

> ⚠️ No uses espacios después del `=` en ninguna variable, Docker los interpretaría como parte del valor.

> ⚠️ No tocar la ruta del EUREKA_URL.

### 3. Arrancar los contenedores

```bash
docker-compose up --build
```

### 4. Verificar que todo está funcionando

Accede al panel de Eureka para ver los servicios registrados:

```
http://localhost:8762
```

Deberías ver los tres microservicios y el Gateway registrados.

---

## Uso con Postman

Todas las peticiones se realizan a través del API Gateway en el puerto `9007`:

```
http://localhost:9007/service-producto/api/v1/productos
http://localhost:9007/service-carrito/api/v1/carrito
http://localhost:9007/service-venta/api/v1/venta
```

### Ejemplo de flujo completo

```bash
# 1. Crear un carrito
POST http://localhost:9007/service-carrito/api/v1/carrito

# 2. Añadir productos al carrito
POST http://localhost:9007/service-carrito/api/v1/carrito/1/productos/3

# 3. Realizar la venta (el carrito se desactiva automáticamente)
POST http://localhost:9007/service-venta/api/v1/venta/1

# 4. Si se elimina la venta, el carrito se reactiva automáticamente
DELETE http://localhost:9007/service-venta/api/v1/venta/1
```

---


## Conceptos aplicados

- Arquitectura de microservicios con bases de datos independientes por servicio
- Comunicación síncrona entre servicios mediante Feign Client
- Patrón Circuit Breaker con Resilience4j para gestión de fallos
- Service Discovery con Eureka
- API Gateway como punto de entrada único
- Contenedorización con Docker y Docker Compose
- Separación de configuración por entornos mediante Spring Profiles
- Manejo global de excepciones con `@ControllerAdvice`
- Patrón DTO con mappers para desacoplar entidades JPA de las respuestas de la API
---

## Autor

**Josep Llopis** — Desarrollador Java Backend

[LinkedIn](https://www.linkedin.com/in/josep-llopis-sanchis-10ba67191)
