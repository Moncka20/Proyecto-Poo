package org.songlibrary.servicio;

import org.songlibrary.BD.ArtistaBD;
import org.songlibrary.modelos.Artista;

import java.util.ArrayList;
import java.util.List;

public class ArtistaServicio {

    public void guardarArtista(Artista objeto) {
        objeto.setId(ArtistaBD.autoId++);
        ArtistaBD.artistas.add(objeto);
    }

    public void eliminarArtista(int id) {
        ArtistaBD.artistas.removeIf(o -> o.getId() == id);
    }

    public void actualizarArtista(int id, Artista ArtistaActualizado) {
        for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
            if (ArtistaBD.artistas.get(i).getId() == id) {
                ArtistaActualizado.setId(id);
                ArtistaBD.artistas.set(i, ArtistaActualizado);
                return;
            }
        }
    }

    public Artista obtenerArtista(int id) {
        for (Artista artistas : ArtistaBD.artistas) {
            if (artistas.getId() == id) {
                return artistas;
            }
        }
        return null;
    }

    public List<Artista> obtenerArtista() {
        return new ArrayList<>(ArtistaBD.artistas);
    }
}
