package mx.uv.listi.techfit.dto; 
/**
 * LoginRequest: recibir los datos del login desde el frontend.
 */

public class LoginRequest {

    private String correo; 
    private String contrasena; 

    public LoginRequest() { // Constructor vacío: Spring lo usa para poder crear el objeto automáticamente desde JSON
    } 

    public String getCorreo() { // Getter: permite leer el valor
        return correo; 
    } 

    public void setCorreo(String correo) { // Setter: permite asignar un valor
        this.correo = correo; 
    } 

    public String getContrasena() { 
        return contrasena; 
    } 

    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    } 
}
