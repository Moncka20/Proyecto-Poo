package org.songlibrary.modelos;

public class Compositor {
    private int id;
    private String nombre;
    private String pais;
    private String estilo;

    public Compositor() {}

    public Compositor(String nombre, String pais, String estilo) {
        this.nombre = nombre;
        this.pais = pais;
        this.estilo = estilo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getEstilo() { return estilo; }
    public void setEstilo(String estilo) { this.estilo = estilo; }
}
