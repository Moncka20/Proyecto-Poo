package org.songlibrary.servicio;

import org.songlibrary.BD.SelloDiscograficoBD;
import org.songlibrary.modelos.SelloDiscografico;

import java.util.ArrayList;
import java.util.List;

public class SelloDiscograficoServicio {

    public void guardarSello(SelloDiscografico sello) {
        sello.setId(SelloDiscograficoBD.autoId++);
        SelloDiscograficoBD.sellos.add(sello);
    }

    public void eliminarSello(int id) {
        SelloDiscograficoBD.sellos.removeIf(s -> s.getId() == id);
    }

    public void actualizarSello(int id, SelloDiscografico selloActualizado) {
        for (int i = 0; i < SelloDiscograficoBD.sellos.size(); i++) {
            if (SelloDiscograficoBD.sellos.get(i).getId() == id) {
                selloActualizado.setId(id);
                SelloDiscograficoBD.sellos.set(i, selloActualizado);
                return;
            }
        }
    }

    public SelloDiscografico obtenerSello(int id) {
        for (SelloDiscografico sello : SelloDiscograficoBD.sellos) {
            if (sello.getId() == id) {
                return sello;
            }
        }
        return null;
    }

    public List<SelloDiscografico> obtenerSellos() {
        return new ArrayList<>(SelloDiscograficoBD.sellos);
    }
}