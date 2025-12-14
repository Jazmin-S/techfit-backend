package mx.uv.listi.techfit.repository; 

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository; // Interfaz base que ya trae CRUD 
import org.springframework.stereotype.Repository;

import mx.uv.listi.techfit.model.Ejercicio; 

/**
 * EjercicioRepository: Este repositorio es la “puerta” para consultar/guardar ejercicios en la base de datos. 
 */

@Repository 
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> { 


    // Busca todos los ejercicios donde tipoUsuario sea igual al valor recibido.

    List<Ejercicio> findByTipoUsuario(String tipoUsuario);

}
