package org.songlibrary.servicio;

import org.songlibrary.BD.ListaBloqueoBD;
import org.songlibrary.modelos.ListaBloqueo;

import java.util.ArrayList;
import java.util.List;

public class ListaBloqueoServicio {

    public void guardarListaBloqueo(ListaBloqueo bloqueo) {
        bloqueo.setId(ListaBloqueoBD.autoId++);
        ListaBloqueoBD.bloqueos.add(bloqueo);
    }

    public void eliminarListaBloqueo(int id) {
        ListaBloqueoBD.bloqueos.removeIf(b -> b.getId() == id);
    }

    public void actualizarListaBloqueo(int id, ListaBloqueo bloqueoActualizado) {
        for (int i = 0; i < ListaBloqueoBD.bloqueos.size(); i++) {
            if (ListaBloqueoBD.bloqueos.get(i).getId() == id) {
                bloqueoActualizado.setId(id);
                ListaBloqueoBD.bloqueos.set(i, bloqueoActualizado);
                return;
            }
        }
    }

    public ListaBloqueo obtenerListaBloqueo(int id) {
        for (ListaBloqueo bloqueo : ListaBloqueoBD.bloqueos) {
            if (bloqueo.getId() == id) {
                return bloqueo;
            }
        }
        return null;
    }

    public List<ListaBloqueo> obtenerListasBloqueo() {
        return new ArrayList<>(ListaBloqueoBD.bloqueos);
    }
}