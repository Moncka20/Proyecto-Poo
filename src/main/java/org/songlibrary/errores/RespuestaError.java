package org.songlibrary.errores;

public class RespuestaError {
    private String mensaje;

    public RespuestaError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}