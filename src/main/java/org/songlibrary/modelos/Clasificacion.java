package org.songlibrary.modelos;

public class Clasificacion {
    private int id;
    private int usuarioId;
    private int elementoId; // Puede ser ID de canción, artista o playlist
    private String tipoElemento; // Ejemplo: "cancion", "artista", "playlist"
    private String etiqueta; // Ejemplo: "Feliz", "Rock", "Workout", etc.

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getElementoId() { return elementoId; }
    public void setElementoId(int elementoId) { this.elementoId = elementoId; }

    public String getTipoElemento() { return tipoElemento; }
    public void setTipoElemento(String tipoElemento) { this.tipoElemento = tipoElemento; }

    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }
}