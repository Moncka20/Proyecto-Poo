package org.songlibrary.modelos;

public class Disco {
    private int id;
    private String titulo;
    private int anio;
    private int idArtista; // El ID del artista que creó el disco

    // Constructor vacío
    public Disco() {
    }

    // Constructor con parámetros
    public Disco(int id, String titulo, int anio, int idArtista) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.idArtista = idArtista;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }
}
