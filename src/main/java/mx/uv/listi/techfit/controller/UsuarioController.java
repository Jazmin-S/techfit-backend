package mx.uv.listi.techfit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import mx.uv.listi.techfit.dto.LoginRequest;
import mx.uv.listi.techfit.model.Usuario;
import mx.uv.listi.techfit.repository.UsuarioRepository;

/**
 * Controlador de Usuarios.
 * NO usamos @RequestMapping("/api") aquí,
 * porque YA existe server.servlet.context-path=/api
 * Rutas finales:
 *   GET  /api/usuarios
 *   POST /api/usuarios
 *   PUT  /api/usuarios/{id}
 *   DELETE /api/usuarios/{id}
 *   POST /api/auth/login
 */
@RestController
@CrossOrigin(
    origins = {
        "http://localhost:5173",
        "https://TU_USUARIO.github.io"
    }
)
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Optional<Usuario> encontrado = usuarioRepository.findById(id);
        return encontrado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datos) {
        Optional<Usuario> encontrado = usuarioRepository.findById(id);
        if (encontrado.isEmpty()) return ResponseEntity.notFound().build();

        Usuario existente = encontrado.get();
        existente.setNombre(datos.getNombre());
        existente.setCorreo(datos.getCorreo());
        existente.setTipoUsuario(datos.getTipoUsuario());

        if (datos.getContrasena() != null && !datos.getContrasena().isBlank()) {
            existente.setContrasena(datos.getContrasena());
        }

        return ResponseEntity.ok(usuarioRepository.save(existente));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> encontrado = usuarioRepository.findByCorreoAndContrasena(
                loginRequest.getCorreo(),
                loginRequest.getContrasena()
        );

        if (encontrado.isPresent()) {
            return ResponseEntity.ok(encontrado.get());
        }

        Map<String, String> error = new HashMap<>();
        error.put("message", "Credenciales incorrectas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
