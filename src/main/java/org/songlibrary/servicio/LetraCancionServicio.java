package org.songlibrary.servicio;

import org.songlibrary.BD.LetraCancionBD;
import org.songlibrary.modelos.LetraCancion;

import java.util.ArrayList;
import java.util.List;

public class LetraCancionServicio {

    public void guardarLetraCancion(LetraCancion letra) {
        letra.setId(LetraCancionBD.autoId++);
        LetraCancionBD.letras.add(letra);
    }

    public void eliminarLetraCancion(int id) {
        LetraCancionBD.letras.removeIf(l -> l.getId() == id);
    }

    public void actualizarLetraCancion(int id, LetraCancion letraActualizada) {
        for (int i = 0; i < LetraCancionBD.letras.size(); i++) {
            if (LetraCancionBD.letras.get(i).getId() == id) {
                letraActualizada.setId(id);
                LetraCancionBD.letras.set(i, letraActualizada);
                return;
            }
        }
    }

    public LetraCancion obtenerLetraCancion(int id) {
        for (LetraCancion letra : LetraCancionBD.letras) {
            if (letra.getId() == id) {
                return letra;
            }
        }
        return null;
    }

    public List<LetraCancion> obtenerLetrasCancion() {
        return new ArrayList<>(LetraCancionBD.letras);
    }
}