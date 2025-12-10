package mx.uv.listi.techfit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.uv.listi.techfit.model.Usuario;

/**
 * Repositorio JPA para la entidad Usuario.
 * Nos da CRUD listo: findAll, save, findById, deleteById, etc.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por correo (para evitar duplicados o para login)
    Optional<Usuario> findByCorreo(String correo);

    // Buscar por correo y contraseña (login simple)
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
}
