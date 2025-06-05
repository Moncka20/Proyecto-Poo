package org.songlibrary.modelos;

public class mensaje<T> {
    private String mensaje;
    private T data;

    public mensaje(String mensaje, T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
