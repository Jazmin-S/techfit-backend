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
 * Controlador de Ejercicios (maneja rutas relacionadas a ejercicios).
 */

@RestController // Esta clase expone endpoints REST y responde normalmente en JSON

@CrossOrigin(   // Permite que el frontend pueda hacer peticiones sin bloqueo CORS
    origins = { 
        "http://localhost:5173",          
        "https://jazmin-s.github.io"    
    }
)

public class EjercicioController {

    private final EjercicioRepository ejercicioRepo; // Dependencia para operar CRUD de Ejercicio en la BD
    private final UsuarioRepository usuarioRepo;     // Dependencia para consultar usuarios y validar si son admin

    public EjercicioController(EjercicioRepository ejercicioRepo, UsuarioRepository usuarioRepo) {
        this.ejercicioRepo = ejercicioRepo; 
        this.usuarioRepo = usuarioRepo;     
    }

    
    @GetMapping("/ejercicios") // Define que este método responde a GET /ejercicios
    public List<Ejercicio> listar(@RequestParam String tipoUsuario) { 
        return ejercicioRepo.findByTipoUsuario(tipoUsuario); 
    }

    //(SOLO ADMIN) // Endpoint para crear ejercicios.
    @PostMapping("/ejercicios") 
    public ResponseEntity<?> crear( 
            @RequestHeader(value = "X-USER-ID", required = false) Long userId, // Toma el id del usuario desde header (si viene)
            @RequestBody Ejercicio ejercicio 
    ) {
        if (!esAdmin(userId)) { // Si el usuario NO es admin, lo bloqueamos
            return ResponseEntity.status(HttpStatus.FORBIDDEN) 
                    .body(Map.of("message", "No autorizado: solo administradores")); 
        }

        ejercicio.setId(null); // Asegura que sea el ejrcicio sea “nuevo” (para que JPA lo inserte y no intente actualizar)
        return ResponseEntity.status(HttpStatus.CREATED).body(ejercicioRepo.save(ejercicio)); 
    }

    // (SOLO ADMIN) Endpoint para actualizar un ejercicio por id.
    @PutMapping("/ejercicios/{id}") 
    public ResponseEntity<?> actualizar(
            @RequestHeader(value = "X-USER-ID", required = false) Long userId, 
            @PathVariable Long id, 
            @RequestBody Ejercicio body
    ) {
        if (!esAdmin(userId)) { // Si no es admin, no lo dejamos editar
            return ResponseEntity.status(HttpStatus.FORBIDDEN) 
                    .body(Map.of("message", "No autorizado: solo administradores")); 
        }

        return ejercicioRepo.findById(id) // Busca el ejercicio en BD por id
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

    // Endpoint para borrar por id, solo admins
    @DeleteMapping("/ejercicios/{id}") 
    public ResponseEntity<?> eliminar( 
            @RequestHeader(value = "X-USER-ID", required = false) Long userId, 
            @PathVariable Long id 
    ) {
        if (!esAdmin(userId)) { // Si el usuario no es admin, lo bloqueamos
            return ResponseEntity.status(HttpStatus.FORBIDDEN) 
                    .body(Map.of("message", "No autorizado: solo administradores")); 
        }

        if (!ejercicioRepo.existsById(id)) { // Verifica si el ejercicio existe antes de borrar.
            return ResponseEntity.status(HttpStatus.NOT_FOUND) 
                    .body(Map.of("message", "Ejercicio no encontrado")); 
        }

        ejercicioRepo.deleteById(id); // Borra el ejercicio de la BD por id
        return ResponseEntity.noContent().build(); 
    }

    private boolean esAdmin(Long userId) { // Método privado: solo lo usa este controller para validar permisos
        if (userId == null) return false;
        return usuarioRepo.findById(userId) 
                .map(u -> u.isEsAdmin())    
                .orElse(false);          
    }
}
