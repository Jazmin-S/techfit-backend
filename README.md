# TechFit Backend

En este repositorio pusimos todo el **backend del sistema TechFit**, que desarrollamos con **Spring Boot** y expusimos como una **API REST**.  
El backend es el encargado de manejar toda la lógica del sistema, la persistencia de datos y la comunicación con el frontend.

El sistema administra información de **usuarios** y **ejercicios físicos**, utiliza una base de datos **PostgreSQL alojada en Neon** y se encuentra **desplegado en Render utilizando Docker**, lo que permite que la aplicación esté disponible en línea.

Este proyecto lo desarrollamos como proyecto para la materia de **Desarrollo Web**.

---

## Tecnologías utilizadas

Para el desarrollo del backend se utilizaron las siguientes tecnologías y herramientas para lograr el funcionamiento del sistema:

- **Java 17**  
  Es el lenguaje de programación principal del proyecto.

- **Spring Boot**  
  Es el framework que usamos que nos facilita la creación de aplicaciones backend y APIs REST.

- **Spring Data JPA**  
  Permite el acceso a datos mediante entidades y repositorios.

- **Hibernate**  
  ORM encargado de mapear los objetos Java a tablas de la base de datos.

- **PostgreSQL (Neon)**  
  Base de datos relacional utilizada, alojada en la nube.

- **Maven**  
  Herramienta para la gestión de dependencias y compilación del proyecto.

- **Docker**  
  Se utiliza para contenerizar la aplicación y facilitar su despliegue.

- **Render**  
  Plataforma en la nube donde se encuentra desplegado el backend.

---

## Repositorio del Backend

Todo el código fuente del backend del proyecto está disponible en GitHub en el siguiente repositorio:

https://github.com/Jazmin-S/techfit-backend.git

Dentro del repositorio se incluyen los siguientes archivos y carpetas:

- Código fuente completo del backend  
- Archivo `Dockerfile` para la creación de la imagen Docker  
- Archivo `application.properties` para la configuración del sistema  
- Archivo `pom.xml` con las dependencias del proyecto  

---

## Backend desplegado

El backend está desplegado como un **Web Service en Render**, lo que permite que la API esté disponible públicamente.

**URL del backend en producción:**  
https://techfit-backend.onrender.com

Ejemplo de consumo de la API:

GET https://techfit-backend.onrender.com/usuarios

---

## Despliegue del backend en Render

Para publicar el backend en Render realizamos los siguientes pasos:

1. Ingresamos a https://render.com  
2. Creamos un **New Web Service**  
3. Conectamos el repositorio de GitHub:
   - Jazmin-S / techfit-backend  
4. Configuramos el servicio con:
   - Runtime: Docker  
   - Branch: main  
5. Render detecta automáticamente el archivo `Dockerfile` y realiza el despliegue  

---

## Variables de entorno en Render

Para la conexión con la base de datos se configuraron variables de entorno en  
**Settings → Environment**:

- **SPRING_DATASOURCE_URL**  
  URL de conexión a la base de datos PostgreSQL en Neon.  
  Ejemplo:
  jdbc:postgresql://<host>.neon.tech/neondb?sslmode=require

- **SPRING_DATASOURCE_USERNAME**  
  Usuario de la base de datos proporcionado por Neon.

- **SPRING_DATASOURCE_PASSWORD**  
  Contraseña correspondiente al usuario de la base de datos.

**Nota importante:**  
Por seguridad, el usuario y la contraseña **no se incluyen directamente en la URL**, sino que se manejan únicamente como variables de entorno.

---

## Compilación y ejecución en local

### Requisitos

Para ejecutar el proyecto de manera local se necesita:

- Java 17 o superior  
- Maven  
- PostgreSQL (local o remoto)  

---

### Ejecución del backend con Maven

1. Clonamos el repositorio:

git clone https://github.com/Jazmin-S/techfit-backend.git  
cd techfit-backend  

2. Configuramos el archivo `application.properties` con los datos de la base de datos local:

spring.datasource.url=jdbc:postgresql://localhost:5432/techfit  
spring.datasource.username=postgres  
spring.datasource.password=tu_password  

spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect  

3. Ejecutamos el proyecto:

mvn spring-boot:run  

La aplicación quedará disponible en:

http://localhost:8080/api

---

### Ejecución del backend con Docker en local

1. Construimos la imagen Docker:

docker build -t techfit-backend .

2. Ejecutamos el contenedor Docker con este comando:

docker run -p 8080:8080 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://<host>.neon.tech/neondb?sslmode=require \
-e SPRING_DATASOURCE_USERNAME=neondb_owner \
-e SPRING_DATASOURCE_PASSWORD=tu_password \
techfit-backend

---

## Documentación del código

### Estructura del proyecto

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

---

### Controladores

- **EjercicioController**  
  Contiene los endpoints REST relacionados con la consulta y administración de ejercicios físicos.

- **UsuarioController**  
  Maneja el registro, la consulta y la administración de usuarios del sistema.

---

### Modelos

- **Ejercicio**  
  Representa un ejercicio físico dentro del sistema TechFit.

- **Usuario**  
  Representa a un usuario registrado en la aplicación.

Ambos modelos están mapeados a tablas de la base de datos mediante **JPA**.

---

### Repositorios

Los repositorios extienden de `JpaRepository`, lo que permite realizar operaciones  
**CRUD (crear, leer, actualizar y eliminar)** sin necesidad de escribir consultas SQL manuales.

---

## Cumplimiento de la rúbrica

El proyecto cumple con los siguientes puntos solicitados en la rúbrica del proyecto final:

- Backend publicado en GitHub  
- Documentación clara del despliegue  
- Documentación de compilación y ejecución en local  
- Documentación mínima del código  
- Uso de base de datos externa (Neon)  
- Proyecto realizado para la materia **Desarrollo Web**
