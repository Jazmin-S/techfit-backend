package mx.uv.listi.techfit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Entidad Usuario (tabla: usuario).
 * Agregamos esAdmin para controlar permisos (solo admins pueden crear ejercicios).
 */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;

    /**
     * Seguridad mínima:
     * - Se guarda en BD (para tu login simple)
     * - Pero NO se devuelve en JSON al frontend
     */
    @JsonIgnore
    private String contrasena;

    /**
     * general | rehabilitacion | adulto_mayor
     * (Hibernate lo mapea a tipo_usuario)
     */
    private String tipoUsuario;

    /**
     * Nuevo: Admin o no.
     * (Hibernate lo mapea a es_admin)
     */
    private boolean esAdmin;

    public Usuario() {}

    // ===== GETTERS / SETTERS =====
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
