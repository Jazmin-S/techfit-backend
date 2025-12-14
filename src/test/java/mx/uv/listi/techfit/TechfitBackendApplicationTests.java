package mx.uv.listi.techfit; 

import org.junit.jupiter.api.Test; 
import org.springframework.boot.test.context.SpringBootTest; // Permite levantar el contexto de Spring para probar que arranca bien

/**
 * TechfitBackendApplicationTests: Este archivo es una prueba básica para verificar que la aplicación carga sin fallar. 
 */

@SpringBootTest 
class TechfitBackendApplicationTests { // Clase de pruebas

    @Test 
    void contextLoads() { 
        // Esta prueba no hace nada a mano porque su chiste es que si el contexto falla, la prueba truena sola.
        // Si llega hasta aquí sin errores, significa que el proyecto arranca correctamente.
    } 

} 
