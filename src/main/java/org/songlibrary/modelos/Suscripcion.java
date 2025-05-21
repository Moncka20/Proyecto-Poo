package org.songlibrary.modelos;

public class Suscripcion {
    private int id;
    private int usuarioId;
    private String tipo; // Ejemplo: "Free", "Premium"
    private String fechaInicio;
    private String fechaFin;
    private boolean activa;

    public Suscripcion(int id, int usuarioId, String tipo, String fechaInicio, String fechaFin, boolean activa) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = activa;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    public boolean isActiva() {
        return activa;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}