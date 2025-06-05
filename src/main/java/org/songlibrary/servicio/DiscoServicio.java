package org.songlibrary.servicio;

import org.songlibrary.BD.DiscoBD;
import org.songlibrary.modelos.Disco;

import java.util.ArrayList;
import java.util.List;

public class DiscoServicio {

    public void guardarDisco(Disco disco) {
        disco.setId(DiscoBD.autoId++);
        DiscoBD.discos.add(disco);
    }

    public void eliminarDisco(int id) {
        DiscoBD.discos.removeIf(d -> d.getId() == id);
    }

    public void actualizarDisco(int id, Disco discoActualizado) {
        for (int i = 0; i < DiscoBD.discos.size(); i++) {
            if (DiscoBD.discos.get(i).getId() == id) {
                discoActualizado.setId(id);
                DiscoBD.discos.set(i, discoActualizado);
                return;
            }
        }
    }

    public Disco obtenerDisco(int id) {
        for (Disco disco : DiscoBD.discos) {
            if (disco.getId() == id) {
                return disco;
            }
        }
        return null;
    }

    public List<Disco> obtenerDiscos() {
        return new ArrayList<>(DiscoBD.discos);
    }
}