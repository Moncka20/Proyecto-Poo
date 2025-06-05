package org.songlibrary.servicio;

import org.songlibrary.BD.ListaDeReproduccionBD;
import org.songlibrary.modelos.ListaDeReproduccion;

import java.util.ArrayList;
import java.util.List;

public class ListaDeReproduccionServicio {

    public void guardarLista(ListaDeReproduccion lista) {
        lista.setId(ListaDeReproduccionBD.autoId++);
        ListaDeReproduccionBD.listas.add(lista);
    }

    public void eliminarLista(int id) {
        ListaDeReproduccionBD.listas.removeIf(l -> l.getId() == id);
    }

    public void actualizarLista(int id, ListaDeReproduccion listaActualizada) {
        for (int i = 0; i < ListaDeReproduccionBD.listas.size(); i++) {
            if (ListaDeReproduccionBD.listas.get(i).getId() == id) {
                listaActualizada.setId(id);
                ListaDeReproduccionBD.listas.set(i, listaActualizada);
                return;
            }
        }
    }

    public ListaDeReproduccion obtenerLista(int id) {
        for (ListaDeReproduccion lista : ListaDeReproduccionBD.listas) {
            if (lista.getId() == id) {
                return lista;
            }
        }
        return null;
    }

    public List<ListaDeReproduccion> obtenerListas() {
        return new ArrayList<>(ListaDeReproduccionBD.listas);
    }
}