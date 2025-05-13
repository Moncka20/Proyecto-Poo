package org.songlibrary.modelos;

public class LetraCancion {
    private int id;
    private int idCancion;  // Relación con Cancion
    private String contenido;

    public LetraCancion() {}

    public LetraCancion(int idCancion, String contenido) {
        this.idCancion = idCancion;
        this.contenido = contenido;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCancion() { return idCancion; }
    public void setIdCancion(int idCancion) { this.idCancion = idCancion; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}
