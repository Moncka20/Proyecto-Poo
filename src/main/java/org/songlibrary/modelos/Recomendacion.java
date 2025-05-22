package org.songlibrary.modelos;

public class Recomendacion {
    private int id;
    private int usuarioId;
    private int cancionId;
    private int albumId;
    private String tipo;

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

    public int getCancionId() {
        return cancionId;
    }
    public void setCancionId(int cancionId) {
        this.cancionId = cancionId;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}