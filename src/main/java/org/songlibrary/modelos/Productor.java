package org.songlibrary.modelos;

public class Productor {
    private int id;
    private String nombre;
    private String nacionalidad;
    private int anioInicioCarrera;

    public Productor() {}

    public Productor(String nombre, String nacionalidad, int anioInicioCarrera) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.anioInicioCarrera = anioInicioCarrera;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public int getAnioInicioCarrera() { return anioInicioCarrera; }
    public void setAnioInicioCarrera(int anioInicioCarrera) { this.anioInicioCarrera = anioInicioCarrera; }
}