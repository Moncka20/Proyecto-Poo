package org.songlibrary.errores;

public class ErrorApi extends RuntimeException {
    private int estado;

    public ErrorApi(String mensaje, int estado) {
        super(mensaje);
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }
}