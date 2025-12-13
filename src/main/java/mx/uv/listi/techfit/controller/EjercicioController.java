package mx.uv.listi.techfit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.uv.listi.techfit.model.Ejercicio;
import mx.uv.listi.techfit.repository.EjercicioRepository;
import mx.uv.listi.techfit.repository.UsuarioRepository;

/**
 * Controlador de Ejercicios.
 * NOTA IMPORTANTE:
 * - NO usamos @RequestMapping("/api") aquí
 * - Porque YA tenemos: server.servlet.context-path=/api
 * Entonces las rutas finales quedan como:
 *   GET    /api/ejercicios
 *   POST   /api/ejercicios
 *   PUT    /api/ejercicios/{id}
 *   DELETE /api/ejercicios/{id}
 */
@RestController
@CrossOrigin(
    origins = {
        "http://localhost:5173",
        "https://TU_USUARIO.github.io" // cámbialo cuando tengas Pages
    }
)
public class EjercicioController {

    private final EjercicioRepository ejercicioRepo;
    private final UsuarioRepository usuarioRepo;

    public EjercicioController(EjercicioRepository ejercicioRepo, UsuarioRepository usuarioRepo) {
        this.ejercicioRepo = ejercicioRepo;
        this.usuarioRepo = usuarioRepo;
    }

    // GET /api/ejercicios?tipoUsuario=general
    @GetMapping("/ejercicios")
    public List<Ejercicio> listar(@RequestParam String tipoUsuario) {
        return ejercicioRepo.findByTipoUsuario(tipoUsuario);
    }

    // POST /api/ejercicios (SOLO ADMIN)
    @PostMapping("/ejercicios")
    public ResponseEntity<?> crear(
            @RequestHeader(value = "X-USER-ID", required = false) Long userId,
            @RequestBody Ejercicio ejercicio
    ) {
        if (!esAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "No autorizado: solo administradores"));
        }

        ejercicio.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(ejercicioRepo.save(ejercicio));
    }

    // PUT /api/ejercicios/{id} (SOLO ADMIN)
    @PutMapping("/ejercicios/{id}")
    public ResponseEntity<?> actualizar(
            @RequestHeader(value = "X-USER-ID", required = false) Long userId,
            @PathVariable Long id,
            @RequestBody Ejercicio body
    ) {
        if (!esAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "No autorizado: solo administradores"));
        }

        return ejercicioRepo.findById(id)
                .<ResponseEntity<?>>map(e -> {
                    e.setNombre(body.getNombre());
                    e.setDuracion(body.getDuracion());
                    e.setNivel(body.getNivel());
                    e.setObjetivo(body.getObjetivo());
                    e.setRecomendaciones(body.getRecomendaciones());
                    e.setTipoUsuario(body.getTipoUsuario());
                    return ResponseEntity.ok(ejercicioRepo.save(e));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Ejercicio no encontrado")));
    }

    // DELETE /api/ejercicios/{id} (SOLO ADMIN)
    @DeleteMapping("/ejercicios/{id}")
    public ResponseEntity<?> eliminar(
            @RequestHeader(value = "X-USER-ID", required = false) Long userId,
            @PathVariable Long id
    ) {
        if (!esAdmin(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "No autorizado: solo administradores"));
        }

        if (!ejercicioRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Ejercicio no encontrado"));
        }

        ejercicioRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean esAdmin(Long userId) {
        if (userId == null) return false;
        return usuarioRepo.findById(userId).map(u -> u.isEsAdmin()).orElse(false);
    }
}
