package mx.uv.listi.techfit.controller; 

import java.util.HashMap;  
import java.util.List;      
import java.util.Map;      
import java.util.Optional;  // Para manejar búsquedas que pueden o no encontrar algo.

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.*;                            
import org.springframework.web.bind.annotation.*;             // Para(endpoints)

import mx.uv.listi.techfit.dto.LoginRequest;            
import mx.uv.listi.techfit.model.Usuario;               
import mx.uv.listi.techfit.repository.UsuarioRepository; 


@RestController
@CrossOrigin(   
    origins = { // Lista de sitios permitidos para hacer peticiones
        "http://localhost:5173",       
        "https://TU_USUARIO.github.io" 
    }     
)               
public class UsuarioController {

    @Autowired 
    private UsuarioRepository usuarioRepository; // Objeto para hacer consultas/altas/bajas/cambios en la tabla usuarios

    @GetMapping("/usuarios") 
    public List<Usuario> obtenerUsuarios() { // Método que regresa la lista de usuarios
        return usuarioRepository.findAll(); 
    } 

    @PostMapping("/usuarios") // Endpoint: crear usuario
    public Usuario crearUsuario(@RequestBody Usuario usuario) { 
        return usuarioRepository.save(usuario); 
    } 

    @GetMapping("/usuarios/{id}") // Endpoint: buscar usuario por id
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) { 
        Optional<Usuario> encontrado = usuarioRepository.findById(id); 
        return encontrado.map(ResponseEntity::ok) 
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    } 

    @PutMapping("/usuarios/{id}") // Endpoint: actualizar usuario
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario datos) { 
        Optional<Usuario> encontrado = usuarioRepository.findById(id); 
        if (encontrado.isEmpty()) return ResponseEntity.notFound().build(); 

        Usuario existente = encontrado.get(); 
        existente.setNombre(datos.getNombre()); 
        existente.setTipoUsuario(datos.getTipoUsuario()); 

        if (datos.getContrasena() != null && !datos.getContrasena().isBlank()) { 
            existente.setContrasena(datos.getContrasena()); 
        } 

        return ResponseEntity.ok(usuarioRepository.save(existente));
    }

    @DeleteMapping("/usuarios/{id}") // Endpoint: eliminar usuario
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) { 
        if (!usuarioRepository.existsById(id)) return ResponseEntity.notFound().build(); 
        usuarioRepository.deleteById(id); 
        return ResponseEntity.noContent().build();
    } 

    @PostMapping("/auth/login") // Endpoint: iniciar sesión
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) { 
        Optional<Usuario> encontrado = usuarioRepository.findByCorreoAndContrasena( // Busca usuario por correo y contraseña
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
