package mx.uv.listi.techfit.repository; 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 

import mx.uv.listi.techfit.model.Usuario; // Importa la entidad Usuario

/**
 * UsuarioRepository: Este repositorio se encarga de consultar y guardar usuarios en la base de datos. 
 */

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 

    // Consulta de usuario por correo  y contraseña.

    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena); 

}
