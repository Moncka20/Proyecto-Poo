package org.songlibrary.modelos;

public class mensaje {
    private String mensaje;
    private String data;

    public mensaje(String mensaje, String data) {
        this.mensaje = mensaje;
        this.data = data;
    }

    public mensaje() {}

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
