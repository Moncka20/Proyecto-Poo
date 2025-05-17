package org.songlibrary.modelos;

import java.util.ArrayList;

public class ListaDeReproduccion {
    private int id;
    private String nombre;
    private String descripcion;
    private ArrayList<Integer> idsCanciones;

    public ListaDeReproduccion() {
        this.idsCanciones = new ArrayList<>();
    }

    public ListaDeReproduccion(String nombre, String descripcion, ArrayList<Integer> idsCanciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idsCanciones = idsCanciones;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public ArrayList<Integer> getIdsCanciones() { return idsCanciones; }
    public void setIdsCanciones(ArrayList<Integer> idsCanciones) { this.idsCanciones = idsCanciones; }
}
