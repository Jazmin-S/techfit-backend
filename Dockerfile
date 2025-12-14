# ============================
# ETAPA 1: Compilar con Maven
# ============================
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero el pom para aprovechar cache de dependencias
COPY pom.xml .

# Copiamos el código fuente
COPY src ./src

# Compilamos el proyecto y generamos el .jar (sin correr tests)
RUN mvn -DskipTests package


# ============================
# ETAPA 2: Ejecutar con Java
# ============================
FROM eclipse-temurin:17-jre

# Carpeta de trabajo
WORKDIR /app

# Copiamos el .jar ya compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Render usa el puerto 8080
EXPOSE 8080

# Comando para arrancar el backend
CMD ["java", "-jar", "app.jar"]
