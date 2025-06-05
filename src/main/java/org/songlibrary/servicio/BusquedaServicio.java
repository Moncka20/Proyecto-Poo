package org.songlibrary.servicio;

import org.songlibrary.BD.BusquedaBD;
import org.songlibrary.modelos.Busqueda;

import java.util.ArrayList;
import java.util.List;

public class BusquedaServicio {

    public void guardarBusqueda(Busqueda busqueda) {
        busqueda.setId(BusquedaBD.autoId++);
        BusquedaBD.busquedas.add(busqueda);
    }

    public void eliminarBusqueda(int id) {
        BusquedaBD.busquedas.removeIf(b -> b.getId() == id);
    }

    public void actualizarBusqueda(int id, Busqueda busquedaActualizada) {
        for (int i = 0; i < BusquedaBD.busquedas.size(); i++) {
            if (BusquedaBD.busquedas.get(i).getId() == id) {
                busquedaActualizada.setId(id);
                BusquedaBD.busquedas.set(i, busquedaActualizada);
                return;
            }
        }
    }

    public Busqueda obtenerBusqueda(int id) {
        for (Busqueda busqueda : BusquedaBD.busquedas) {
            if (busqueda.getId() == id) {
                return busqueda;
            }
        }
        return null;
    }

    public List<Busqueda> obtenerBusquedas() {
        return new ArrayList<>(BusquedaBD.busquedas);
    }
}