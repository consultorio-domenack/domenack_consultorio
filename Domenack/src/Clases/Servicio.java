package Clases;

import java.io.InputStream;

public class Servicio {
    private String id;
    private String nombre;
    private String descripcion;
    private Float costo;
    private InputStream guardarImagen;
    private byte[] recuperarImagen;
    private int disponibilidad;
    private int cantidad;   
    private int ganancia;

    public Servicio(String id, String nombre, String descripcion, Float costo, InputStream guardarImagen, byte[] recuperarImagen, int disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.guardarImagen = guardarImagen;
        this.recuperarImagen = recuperarImagen;
        this.disponibilidad = disponibilidad;
    }

    public Servicio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public InputStream getGuardarImagen() {
        return guardarImagen;
    }

    public void setGuardarImagen(InputStream guardarImagen) {
        this.guardarImagen = guardarImagen;
    }

    public byte[] getRecuperarImagen() {
        return recuperarImagen;
    }

    public void setRecuperarImagen(byte[] recuperarImagen) {
        this.recuperarImagen = recuperarImagen;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getGanancia() {
        return ganancia;
    }

    public void setGanancia(int ganancia) {
        this.ganancia = ganancia;
    }
}
