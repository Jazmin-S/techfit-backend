package mx.uv.listi.techfit.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.uv.listi.techfit.model.Ejercicio;

/**
 * Repositorio de Ejercicio.
 * Permite CRUD y búsquedas por tipoUsuario.
 */
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {
    List<Ejercicio> findByTipoUsuario(String tipoUsuario);
}
