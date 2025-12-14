package mx.uv.listi.techfit.model;

import jakarta.persistence.*;

/**
 * Usuario: Esta entidad representa a una persona que puede usar el sistema. 
 */

@Entity 
@Table(name = "usuario") 
public class Usuario { 

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // Identificador único del usuario

    @Column(nullable = false) 
    private String nombre; // Nombre del usuario 

    @Column(nullable = false, unique = true) 
    private String correo; // Correo del usuario 

    @Column(nullable = false) 
    private String contrasena; // Contraseña del usuario

    @Column(nullable = false)
    private String tipoUsuario; // Tipo de usuario

    @Column(nullable = false) 
    private boolean esAdmin; // True = admin (puede crear/editar/borrar), False = usuario normal

    public Usuario() { 
    }

    // Getters y setters 

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public boolean isEsAdmin() { return esAdmin; } 
    public void setEsAdmin(boolean esAdmin) { this.esAdmin = esAdmin; }

}
