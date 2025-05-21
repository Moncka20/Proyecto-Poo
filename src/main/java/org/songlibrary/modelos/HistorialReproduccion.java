package org.songlibrary.modelos;

public class HistorialReproduccion {
    private int id;
    private int usuarioId;
    private int cancionId;
    private String fechaReproduccion;

    public HistorialReproduccion(int id, int usuarioId, int cancionId, String fechaReproduccion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.cancionId = cancionId;
        this.fechaReproduccion = fechaReproduccion;
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
    public int getCancionId() {
        return cancionId;
    }
    public void setCancionId(int cancionId) {
        this.cancionId = cancionId;
    }
    public String getFechaReproduccion() {
        return fechaReproduccion;
    }
    public void setFechaReproduccion(String fechaReproduccion) {
        this.fechaReproduccion = fechaReproduccion;
    }
}
