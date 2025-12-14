# TechFit Backend 🏋️‍♂️

Backend del sistema **TechFit**, desarrollado con **Spring Boot**, expuesto como **API REST**, conectado a una base de datos **PostgreSQL (Neon)** y desplegado en **Render** mediante **Docker**.


## 📌 Tecnologías utilizadas

* Java 17
* Spring Boot 4
* Spring Data JPA
* Hibernate
* PostgreSQL (Neon)
* Maven
* Docker
* Render


## 📁 Repositorio del Backend (GitHub)

El código fuente del backend se encuentra en el siguiente repositorio:


[https://github.com/Jazmin-S/techfit-backend.git](https://github.com/Jazmin-S/techfit-backend.git)


El repositorio contiene:

* Código fuente del backend
* Archivo `Dockerfile`
* Archivo `application.properties`
* Archivo de configuración `pom.xml`


## 🚀 Despliegue del Backend en Render

El backend está desplegado como **Web Service en Render**, utilizando Docker.

### 🔧 Pasos para desplegar

1. Ingresar a [https://render.com](https://render.com)
2. Crear un **New Web Service**
3. Conectar el repositorio de GitHub:

   ```
   Jazmin-S / techfit-backend
   ```
4. Seleccionar:

   * Runtime: **Docker**
   * Branch: **main**
5. Render detecta automáticamente el `Dockerfile`

---

### 🌐 Variables de entorno en Render

En **Settings → Environment**, agregar las siguientes variables:

| KEY                        | VALUE                                                     |
| -------------------------- | --------------------------------------------------------- |
| SPRING_DATASOURCE_URL      | jdbc:postgresql://<host>.neon.tech/neondb?sslmode=require |
| SPRING_DATASOURCE_USERNAME | neondb_owner                                              |
| SPRING_DATASOURCE_PASSWORD | contraseña proporcionada por Neon                         |

⚠️ La URL no debe incluir usuario ni contraseña.

---

### 🌍 URL del backend desplegado

```
https://techfit-backend.onrender.com
```

Ejemplo:

```
GET https://techfit-backend.onrender.com/usuarios
```

---

## 💻 Compilación y ejecución en local

### 📋 Requisitos

* Java 17 o superior
* Maven
* PostgreSQL (local o remoto)

---

### ▶️ Ejecutar con Maven

1. Clonar el repositorio:

```bash
git clone https://github.com/Jazmin-S/techfit-backend.git
cd techfit-backend
```

2. Configurar `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/techfit
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

3. Ejecutar el backend:

```bash
mvn spring-boot:run
```

La aplicación quedará disponible en:

```
http://localhost:8080/api
```

---

### ▶️ Ejecutar con Docker en local

1. Construir la imagen:

```bash
docker build -t techfit-backend .
```

2. Ejecutar el contenedor:

```bash
docker run -p 8080:8080 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://<host>.neon.tech/neondb?sslmode=require \
-e SPRING_DATASOURCE_USERNAME=neondb_owner \
-e SPRING_DATASOURCE_PASSWORD=tu_password \
techfit-backend
```

---

## 🧠 Documentación mínima del código

### 📦 Estructura del proyecto

```
src/main/java/mx/uv/listi/techfit
│
├── controller
│   ├── EjercicioController.java
│   └── UsuarioController.java
│
├── model
│   ├── Ejercicio.java
│   └── Usuario.java
│
├── repository
│   ├── EjercicioRepository.java
│   └── UsuarioRepository.java
│
└── TechfitBackendApplication.java
```

---

### 📌 Controladores

* **EjercicioController**: expone endpoints REST para consultar y gestionar ejercicios.
* **UsuarioController**: gestiona el registro y consulta de usuarios.

---

### 📌 Modelos

* **Ejercicio**: representa un ejercicio físico dentro del sistema.
* **Usuario**: representa a un usuario registrado.

Ambos modelos están mapeados a tablas mediante JPA.

---

### 📌 Repositorios

Los repositorios extienden `JpaRepository` y permiten realizar operaciones CRUD sin necesidad de escribir consultas SQL manuales.

---

## ✅ Cumplimiento de la rúbrica

* Backend publicado en GitHub
* Documentación de despliegue
* Documentación de compilación local
* Documentación mínima del código
* Uso de base de datos externa (Neon)

---

Proyecto realizado para la materia **Desarrollo Web**.
