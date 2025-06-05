package org.songlibrary.servicio;

import org.songlibrary.BD.RecomendacionBD;
import org.songlibrary.modelos.Recomendacion;

import java.util.ArrayList;
import java.util.List;

public class RecomendacionServicio {

    public void guardarRecomendacion(Recomendacion recomendacion) {
        recomendacion.setId(RecomendacionBD.autoId++);
        RecomendacionBD.recomendaciones.add(recomendacion);
    }

    public void eliminarRecomendacion(int id) {
        RecomendacionBD.recomendaciones.removeIf(r -> r.getId() == id);
    }

    public void actualizarRecomendacion(int id, Recomendacion recomendacionActualizada) {
        for (int i = 0; i < RecomendacionBD.recomendaciones.size(); i++) {
            if (RecomendacionBD.recomendaciones.get(i).getId() == id) {
                recomendacionActualizada.setId(id);
                RecomendacionBD.recomendaciones.set(i, recomendacionActualizada);
                return;
            }
        }
    }

    public Recomendacion obtenerRecomendacion(int id) {
        for (Recomendacion recomendacion : RecomendacionBD.recomendaciones) {
            if (recomendacion.getId() == id) {
                return recomendacion;
            }
        }
        return null;
    }

    public List<Recomendacion> obtenerRecomendaciones() {
        return new ArrayList<>(RecomendacionBD.recomendaciones);
    }
}