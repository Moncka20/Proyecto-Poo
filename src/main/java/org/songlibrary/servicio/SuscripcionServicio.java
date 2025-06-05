package org.songlibrary.servicio;

import org.songlibrary.BD.SuscripcionBD;
import org.songlibrary.modelos.Suscripcion;

import java.util.ArrayList;
import java.util.List;

public class SuscripcionServicio {

    public void guardarSuscripcion(Suscripcion suscripcion) {
        suscripcion.setId(SuscripcionBD.autoId++);
        SuscripcionBD.suscripciones.add(suscripcion);
    }

    public void eliminarSuscripcion(int id) {
        SuscripcionBD.suscripciones.removeIf(s -> s.getId() == id);
    }

    public void actualizarSuscripcion(int id, Suscripcion suscripcionActualizada) {
        for (int i = 0; i < SuscripcionBD.suscripciones.size(); i++) {
            if (SuscripcionBD.suscripciones.get(i).getId() == id) {
                suscripcionActualizada.setId(id);
                SuscripcionBD.suscripciones.set(i, suscripcionActualizada);
                return;
            }
        }
    }

    public Suscripcion obtenerSuscripcion(int id) {
        for (Suscripcion suscripcion : SuscripcionBD.suscripciones) {
            if (suscripcion.getId() == id) {
                return suscripcion;
            }
        }
        return null;
    }

    public List<Suscripcion> obtenerSuscripciones() {
        return new ArrayList<>(SuscripcionBD.suscripciones);
    }
}