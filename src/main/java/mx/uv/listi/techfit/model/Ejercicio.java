package mx.uv.listi.techfit.model;

import jakarta.persistence.*;

/**
 * Entidad Ejercicio.
 * Sirve para guardar el catálogo en BD y que el frontend lo consuma.
 */
@Entity
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String duracion;
    private String nivel;

    @Column(length = 1000)
    private String objetivo;

    @Column(length = 2000)
    private String recomendaciones;

    private String videoUrl;


    /**
     * Para qué tipo de usuario es el ejercicio:
     * general | rehabilitacion | adulto_mayor
     */
    private String tipoUsuario;

    public Ejercicio() {}

    // ===== GETTERS / SETTERS =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
