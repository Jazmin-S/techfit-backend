package mx.uv.listi.techfit; 

import org.springframework.boot.SpringApplication; // Importa la clase que se encarga de levantar Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; 

/**
 * TechfitBackendApplication: Esta es la clase principal del backend. 
 */

@SpringBootApplication // Activa configuración automática + escaneo de componentes + configuración base de Spring
public class TechfitBackendApplication { 

    public static void main(String[] args) { 
        SpringApplication.run(TechfitBackendApplication.class, args); // Arranca la app
    } 
} 
