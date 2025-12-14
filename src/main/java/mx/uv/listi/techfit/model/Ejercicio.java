package mx.uv.listi.techfit.model; 

import jakarta.persistence.*; // Importa anotaciones JPA para mapear la clase a una tabla

/**
 * Ejercicio: Esta clase representa un ejercicio dentro del sistema. 
 */

@Entity // Esta clase se guarda como tabla en la base de datos
@Table(name = "ejercicio") // Nombre de la tabla 

public class Ejercicio { 

    @Id // (Identificador único)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @Column(nullable = false) // Indica que este campo no debería ir vacío 
    private String nombre; 

    private String duracion; // Duración del ejercicio 

    private String nivel; // Nivel recomendado 

    @Column(length = 500) // Da espacio extra por si el texto es largo
    private String objetivo; 

    @Column(length = 1000) 
    private String recomendaciones; // Consejos o recomendaciones 

    @Column(nullable = false) // Filtrar
    private String tipoUsuario; 


    private String videoUrl; // Link del video 

    public Ejercicio() {
    } 

    // =========================
    // GETTERS Y SETTERS
    // =========================

    public Long getId() { 
        return id; 
    } 

    public void setId(Long id) { 
        this.id = id; 
    } 

    public String getNombre() { 
        return nombre; 
    } 

    public void setNombre(String nombre) { 
        this.nombre = nombre;
    } 

    public String getDuracion() {
        return duracion; 
    } 

    public void setDuracion(String duracion) { 
        this.duracion = duracion; 
    } 

    public String getNivel() { 
        return nivel;
    } 

    public void setNivel(String nivel) { 
        this.nivel = nivel; 
    } 

    public String getObjetivo() {
        return objetivo; 
    } 

    public void setObjetivo(String objetivo) { 
        this.objetivo = objetivo; 
    }

    public String getRecomendaciones() { 
        return recomendaciones; 
    } 

    public void setRecomendaciones(String recomendaciones) { 
        this.recomendaciones = recomendaciones; 
    } 

    public String getTipoUsuario() { 
        return tipoUsuario; 
    } 

    public void setTipoUsuario(String tipoUsuario) { 
        this.tipoUsuario = tipoUsuario; 
    } 

    public String getVideoUrl() {
        return videoUrl;
    } 

    public void setVideoUrl(String videoUrl) { 
        this.videoUrl = videoUrl; 
    } 

}
