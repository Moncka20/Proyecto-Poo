package org.songlibrary.modelos;

import java.util.List;

public class RankingCancion {
    private int id;
    private String nombre;
    private String descripcion;
    private String fechaCreacion;
    private String fechaActualizacion;
    private List<Integer> cancionesIds; // IDs de canciones en el ranking
    private String criterio;
    private String periodo;

    public RankingCancion(int id, String nombre, String descripcion, String fechaCreacion, String fechaActualizacion, List<Integer> cancionesIds, String criterio, String periodo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.cancionesIds = cancionesIds;
        this.criterio = criterio;
        this.periodo = periodo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getFechaActualizacion() {
        return fechaActualizacion;
    }
    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    public List<Integer> getCancionesIds() {
        return cancionesIds;
    }
    public void setCancionesIds(List<Integer> cancionesIds) {
        this.cancionesIds = cancionesIds;
    }
    public String getCriterio() {
        return criterio;
    }
    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
