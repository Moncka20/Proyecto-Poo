package org.songlibrary.modelos;

public class Podcast {
    private int id;
    private String titulo;
    private String anfitrion;
    private String descripcion;
    private int anioInicio;

    public Podcast() {}

    public Podcast(String titulo, String anfitrion, String descripcion, int anioInicio) {
        this.titulo = titulo;
        this.anfitrion = anfitrion;
        this.descripcion = descripcion;
        this.anioInicio = anioInicio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAnfitrion() { return anfitrion; }
    public void setAnfitrion(String anfitrion) { this.anfitrion = anfitrion; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getAnioInicio() { return anioInicio; }
    public void setAnioInicio(int anioInicio) { this.anioInicio = anioInicio; }
}
