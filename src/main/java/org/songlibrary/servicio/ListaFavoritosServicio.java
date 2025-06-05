package org.songlibrary.servicio;

import org.songlibrary.BD.ListaFavoritosBD;
import org.songlibrary.modelos.ListaFavoritos;

import java.util.ArrayList;
import java.util.List;

public class ListaFavoritosServicio {

    public void guardarFavorito(ListaFavoritos favorito) {
        favorito.setId(ListaFavoritosBD.autoId++);
        ListaFavoritosBD.favoritos.add(favorito);
    }

    public void eliminarFavorito(int id) {
        ListaFavoritosBD.favoritos.removeIf(f -> f.getId() == id);
    }

    public void actualizarFavorito(int id, ListaFavoritos favoritoActualizado) {
        for (int i = 0; i < ListaFavoritosBD.favoritos.size(); i++) {
            if (ListaFavoritosBD.favoritos.get(i).getId() == id) {
                favoritoActualizado.setId(id);
                ListaFavoritosBD.favoritos.set(i, favoritoActualizado);
                return;
            }
        }
    }

    public ListaFavoritos obtenerFavorito(int id) {
        for (ListaFavoritos favorito : ListaFavoritosBD.favoritos) {
            if (favorito.getId() == id) {
                return favorito;
            }
        }
        return null;
    }

    public List<ListaFavoritos> obtenerFavoritos() {
        return new ArrayList<>(ListaFavoritosBD.favoritos);
    }
}