package Clases;

public class Cita {
    private int id;
    private ProgramacionCita programacionCita = new ProgramacionCita();
    private Perfil perfilUsuario = new Perfil();
    private Servicio servicio = new Servicio();
    private String asistencia;

    public Cita(int id, String asistencia, ProgramacionCita programacionCita, Perfil usuario, Servicio servicio) {
        this.id = id;
        this.asistencia = asistencia;
        this.programacionCita = programacionCita;
        this.perfilUsuario = usuario;
        this.servicio = servicio;
    }

    public Cita() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public ProgramacionCita getProgramacionCita() {
        return programacionCita;
    }

    public void setProgramacionCita(ProgramacionCita programacionCita) {
        this.programacionCita = programacionCita;
    }

    public Perfil getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(Perfil perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    
}
