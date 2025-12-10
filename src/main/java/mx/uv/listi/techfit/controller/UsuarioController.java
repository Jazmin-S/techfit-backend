package mx.uv.listi.techfit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.uv.listi.techfit.dto.LoginRequest;
import mx.uv.listi.techfit.model.Usuario;
import mx.uv.listi.techfit.repository.UsuarioRepository;

/**
 * Controlador REST para manejar usuarios de TECHFIT.
 * Todas las rutas comienzan con /api porque configuramos server.servlet.context-path=/api
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173") // permitir peticiones desde tu frontend local
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // -------------------------
    // LISTAR TODOS LOS USUARIOS (GET /api/usuarios)
    // -------------------------
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    // -------------------------
    // CREAR USUARIO (POST /api/usuarios)
    // -------------------------
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // -------------------------
    // OBTENER USUARIO POR ID (GET /api/usuarios/{id})
    // -------------------------
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Optional<Usuario> encontrado = usuarioRepository.findById(id);
        return encontrado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // -------------------------
    // ACTUALIZAR USUARIO (PUT /api/usuarios/{id})
    // -------------------------
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario datos) {

        Optional<Usuario> encontrado = usuarioRepository.findById(id);
        if (encontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario existente = encontrado.get();
        existente.setNombre(datos.getNombre());
        existente.setCorreo(datos.getCorreo());
        existente.setTipoUsuario(datos.getTipoUsuario());

        // Opcional: permitir cambiar contraseña si viene
        if (datos.getContrasena() != null && !datos.getContrasena().isBlank()) {
            existente.setContrasena(datos.getContrasena());
        }

        Usuario actualizado = usuarioRepository.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    // -------------------------
    // ELIMINAR USUARIO (DELETE /api/usuarios/{id})
    // -------------------------
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    // -------------------------
    // LOGIN (POST /api/auth/login)
    // -------------------------
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> encontrado = usuarioRepository.findByCorreoAndContrasena(
                loginRequest.getCorreo(),
                loginRequest.getContrasena()
        );

        if (encontrado.isPresent()) {
            Usuario usuario = encontrado.get();
            // Por seguridad, no regresar la contraseña
            usuario.setContrasena(null);
            return ResponseEntity.ok(usuario);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Credenciales incorrectas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
