package org.songlibrary.servicio;

import org.songlibrary.BD.ClasificacionBD;
import org.songlibrary.modelos.Clasificacion;

import java.util.ArrayList;
import java.util.List;

public class ClasificacionServicio {

    public void guardarClasificacion(Clasificacion clasificacion) {
        clasificacion.setId(ClasificacionBD.autoId++);
        ClasificacionBD.clasificaciones.add(clasificacion);
    }

    public void eliminarClasificacion(int id) {
        ClasificacionBD.clasificaciones.removeIf(c -> c.getId() == id);
    }

    public void actualizarClasificacion(int id, Clasificacion clasificacionActualizada) {
        for (int i = 0; i < ClasificacionBD.clasificaciones.size(); i++) {
            if (ClasificacionBD.clasificaciones.get(i).getId() == id) {
                clasificacionActualizada.setId(id);
                ClasificacionBD.clasificaciones.set(i, clasificacionActualizada);
                return;
            }
        }
    }

    public Clasificacion obtenerClasificacion(int id) {
        for (Clasificacion clasificacion : ClasificacionBD.clasificaciones) {
            if (clasificacion.getId() == id) {
                return clasificacion;
            }
        }
        return null;
    }

    public List<Clasificacion> obtenerClasificaciones() {
        return new ArrayList<>(ClasificacionBD.clasificaciones);
    }
}