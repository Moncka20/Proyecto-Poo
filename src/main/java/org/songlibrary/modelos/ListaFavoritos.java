package org.songlibrary.modelos;

import java.util.List;

public class ListaFavoritos {
    private int id;
    private int usuarioId;
    private String nombre;
    private List<Integer> cancionesIds;

    public ListaFavoritos(int id, int usuarioId, String nombre, List<Integer> cancionesIds) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.cancionesIds = cancionesIds;
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Integer> getCancionesIds() {
        return cancionesIds;
    }
    public void setCancionesIds(List<Integer> cancionesIds) {
        this.cancionesIds = cancionesIds;
    }
}
