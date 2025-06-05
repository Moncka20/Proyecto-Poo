package org.songlibrary.servicio;

import org.songlibrary.BD.EstadisticaCancionBD;
import org.songlibrary.modelos.EstadisticaCancion;

import java.util.ArrayList;
import java.util.List;

public class EstadisticaCancionServicio {

    public void guardarEstadistica(EstadisticaCancion estadistica) {
        estadistica.setId(EstadisticaCancionBD.autoId++);
        EstadisticaCancionBD.estadisticas.add(estadistica);
    }

    public void eliminarEstadistica(int id) {
        EstadisticaCancionBD.estadisticas.removeIf(e -> e.getId() == id);
    }

    public void actualizarEstadistica(int id, EstadisticaCancion estadisticaActualizada) {
        for (int i = 0; i < EstadisticaCancionBD.estadisticas.size(); i++) {
            if (EstadisticaCancionBD.estadisticas.get(i).getId() == id) {
                estadisticaActualizada.setId(id);
                EstadisticaCancionBD.estadisticas.set(i, estadisticaActualizada);
                return;
            }
        }
    }

    public EstadisticaCancion obtenerEstadistica(int id) {
        for (EstadisticaCancion estadistica : EstadisticaCancionBD.estadisticas) {
            if (estadistica.getId() == id) {
                return estadistica;
            }
        }
        return null;
    }

    public List<EstadisticaCancion> obtenerEstadisticas() {
        return new ArrayList<>(EstadisticaCancionBD.estadisticas);
    }
}