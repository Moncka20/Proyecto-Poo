package org.songlibrary.modelos;

public class EstadisticaCancion {
    private int id;
    private int cancionId;
    private int reproducciones;
    private int likes;
    private int skips;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCancionId() {
        return cancionId;
    }
    public void setCancionId(int cancionId) {
        this.cancionId = cancionId;
    }

    public int getReproducciones() {
        return reproducciones;
    }
    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getSkips() {
        return skips;
    }
    public void setSkips(int skips) {
        this.skips = skips;
    }
}