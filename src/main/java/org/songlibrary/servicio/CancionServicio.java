package org.songlibrary.servicio;

import org.songlibrary.BD.CancionBD;
import org.songlibrary.modelos.Cancion;

import java.util.ArrayList;
import java.util.List;

public class CancionServicio {

    public void guardarCancion(Cancion cancion) {
        cancion.setId(CancionBD.autoId++);
        CancionBD.canciones.add(cancion);
    }

    public void eliminarCancion(int id) {
        CancionBD.canciones.removeIf(c -> c.getId() == id);
    }

    public void actualizarCancion(int id, Cancion cancionActualizada) {
        for (int i = 0; i < CancionBD.canciones.size(); i++) {
            if (CancionBD.canciones.get(i).getId() == id) {
                cancionActualizada.setId(id);
                CancionBD.canciones.set(i, cancionActualizada);
                return;
            }
        }
    }

    public Cancion obtenerCancion(int id) {
        for (Cancion cancion : CancionBD.canciones) {
            if (cancion.getId() == id) {
                return cancion;
            }
        }
        return null;
    }

    public List<Cancion> obtenerCanciones() {
        return new ArrayList<>(CancionBD.canciones);
    }
}