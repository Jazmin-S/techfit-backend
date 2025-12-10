package com.techfit.techfit.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techfit.techfit.model.Usuario;
import com.techfit.techfit.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // POST http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public Usuario login(@RequestBody Map<String, String> datos) {
        String correo = datos.get("correo");
        String contrasena = datos.get("contrasena");

        System.out.println("Intento de login: " + correo);

        Optional<Usuario> usuarioBD = usuarioRepository.findByCorreo(correo);

        if (usuarioBD.isPresent()) {
            Usuario u = usuarioBD.get();
            if (u.getContrasena().equals(contrasena)) {
                System.out.println("Login correcto");
                return u; // se manda el usuario al frontend
            } else {
                System.out.println("Contraseña incorrecta");
                return null;
            }
        } else {
            System.out.println("Usuario no encontrado");
            return null;
        }
    }
    
}
