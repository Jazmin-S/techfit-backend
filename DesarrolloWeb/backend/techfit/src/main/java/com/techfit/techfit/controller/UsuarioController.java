package com.techfit.techfit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.techfit.techfit.model.Usuario;
import com.techfit.techfit.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // GET /api/usuarios
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // GET /api/usuarios/{id}
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            // si no existe, regresamos null (el frontend debe checar esto)
            return null;
        }
    }

    // POST /api/usuarios
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        // aquí podrías imprimir para debug
        System.out.println("Creando usuario: " + usuario.getCorreo());
        return usuarioRepository.save(usuario);
    }

    // PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Integer id, @RequestBody Usuario usuarioNuevo) {
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);

        if (usuarioBD.isPresent()) {
            Usuario u = usuarioBD.get();

            u.setNombre(usuarioNuevo.getNombre());
            u.setCorreo(usuarioNuevo.getCorreo());
            u.setTipoUsuario(usuarioNuevo.getTipoUsuario());

            // Solo cambiamos la contraseña si viene algo en el JSON
            if (usuarioNuevo.getContrasena() != null && !usuarioNuevo.getContrasena().isEmpty()) {
                u.setContrasena(usuarioNuevo.getContrasena());
            }

            return usuarioRepository.save(u);
        } else {
            // si no existe ese id, regresamos null
            return null;
        }
    }


    // DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario eliminado";
        } else {
            return "Usuario no encontrado";
        }
    }
}
