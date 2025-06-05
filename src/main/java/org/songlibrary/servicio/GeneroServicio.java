package org.songlibrary.servicio;

import org.songlibrary.BD.GeneroBD;
import org.songlibrary.modelos.Genero;

import java.util.ArrayList;
import java.util.List;

public class GeneroServicio {

    public void guardarGenero(Genero genero) {
        genero.setId(GeneroBD.autoId++);
        GeneroBD.generos.add(genero);
    }

    public void eliminarGenero(int id) {
        GeneroBD.generos.removeIf(g -> g.getId() == id);
    }

    public void actualizarGenero(int id, Genero generoActualizado) {
        for (int i = 0; i < GeneroBD.generos.size(); i++) {
            if (GeneroBD.generos.get(i).getId() == id) {
                generoActualizado.setId(id);
                GeneroBD.generos.set(i, generoActualizado);
                return;
            }
        }
    }

    public Genero obtenerGenero(int id) {
        for (Genero genero : GeneroBD.generos) {
            if (genero.getId() == id) {
                return genero;
            }
        }
        return null;
    }

    public List<Genero> obtenerGeneros() {
        return new ArrayList<>(GeneroBD.generos);
    }
}