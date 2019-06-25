package Clases;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ProgramacionCita {
    private int id;
    private Date fecha;
    private Time hora_ini_valor;
    private Time hora_fin_valor;
    private List<Time> hora_ini;
    private List<Time> hora_fin;
    private String estado;
    private String dia;
    private int cantidad;

    public ProgramacionCita(int id, Date fecha, Time hora_ini_valor, Time hora_fin_valor, List<Time> hora_ini, List<Time> hora_fin, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora_ini_valor = hora_ini_valor;
        this.hora_fin_valor = hora_fin_valor;
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
        this.estado = estado;
    }

    public ProgramacionCita() {
    }

    public List<Time> getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(List<Time> hora_ini) {
        this.hora_ini = hora_ini;
    }

    public List<Time> getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(List<Time> hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_ini_valor() {
        return hora_ini_valor;
    }

    public void setHora_ini_valor(Time hora_ini_valor) {
        this.hora_ini_valor = hora_ini_valor;
    }

    public Time getHora_fin_valor() {
        return hora_fin_valor;
    }

    public void setHora_fin_valor(Time hora_fin_valor) {
        this.hora_fin_valor = hora_fin_valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
