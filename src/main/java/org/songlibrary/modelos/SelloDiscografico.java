package org.songlibrary.modelos;

public class SelloDiscografico {
    private int id;
    private String nombre;
    private String pais;
    private int anioFundacion;

    public SelloDiscografico() {}

    public SelloDiscografico(String nombre, String pais, int anioFundacion) {
        this.nombre = nombre;
        this.pais = pais;
        this.anioFundacion = anioFundacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public int getAnioFundacion() { return anioFundacion; }
    public void setAnioFundacion(int anioFundacion) { this.anioFundacion = anioFundacion; }
}
