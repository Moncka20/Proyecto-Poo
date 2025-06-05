package org.songlibrary.servicio;

import org.songlibrary.BD.HistorialReproduccionBD;
import org.songlibrary.modelos.HistorialReproduccion;

import java.util.ArrayList;
import java.util.List;

public class HistorialReproduccionServicio {

    public void guardarHistorial(HistorialReproduccion historial) {
        historial.setId(HistorialReproduccionBD.autoId++);
        HistorialReproduccionBD.historiales.add(historial);
    }

    public void eliminarHistorial(int id) {
        HistorialReproduccionBD.historiales.removeIf(h -> h.getId() == id);
    }

    public void actualizarHistorial(int id, HistorialReproduccion historialActualizado) {
        for (int i = 0; i < HistorialReproduccionBD.historiales.size(); i++) {
            if (HistorialReproduccionBD.historiales.get(i).getId() == id) {
                historialActualizado.setId(id);
                HistorialReproduccionBD.historiales.set(i, historialActualizado);
                return;
            }
        }
    }

    public HistorialReproduccion obtenerHistorial(int id) {
        for (HistorialReproduccion historial : HistorialReproduccionBD.historiales) {
            if (historial.getId() == id) {
                return historial;
            }
        }
        return null;
    }

    public List<HistorialReproduccion> obtenerHistoriales() {
        return new ArrayList<>(HistorialReproduccionBD.historiales);
    }
}