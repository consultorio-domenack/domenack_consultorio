package Clases;

public class Usuario {
    private String id;
    private String dni;
    private String clave;
    private String correo;
    private String nivel;

    public Usuario(String id, String dni, String clave, String correo, String nivel) {
        this.id = id;
        this.dni = dni;
        this.clave = clave;
        this.correo = correo;
        this.nivel = nivel;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    
}
