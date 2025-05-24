package org.songlibrary.modelos;

public class ListaBloqueo {
    private int id;
    private int usuarioId;
    private int bloqueadoId; // Puede ser usuario, artista o canción
    private String tipo; // Ejemplo: "usuario", "artista", "cancion"
    private String motivo; // Opcional, motivo del bloqueo

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getBloqueadoId() { return bloqueadoId; }
    public void setBloqueadoId(int bloqueadoId) { this.bloqueadoId = bloqueadoId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}