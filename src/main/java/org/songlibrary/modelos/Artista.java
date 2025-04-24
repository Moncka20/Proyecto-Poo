package org.songlibrary.modelos;

public class Artista {
    private int id;
    private String nombre;
    private String pais;
    private String generoPrincipal;

    public Artista() {}

    public Artista(String nombre, String pais, String generoPrincipal) {
        this.nombre = nombre;
        this.pais = pais;
        this.generoPrincipal = generoPrincipal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getGeneroPrincipal() { return generoPrincipal; }
    public void setGeneroPrincipal(String generoPrincipal) { this.generoPrincipal = generoPrincipal; }
}

