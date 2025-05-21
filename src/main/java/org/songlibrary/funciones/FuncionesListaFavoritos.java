package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ListaFavoritosBD;
import org.songlibrary.modelos.ListaFavoritos;
import org.songlibrary.modelos.mensaje;


import io.javalin.Javalin;

public class FuncionesListaFavoritos {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/favoritos", ctx -> {
            ctx.contentType("application/json");
            ListaFavoritos lista = mapper.readValue(ctx.body(), ListaFavoritos.class);
            lista.setId(ListaFavoritosBD.autoId++);
            ListaFavoritosBD.favoritos.add(lista);
            ctx.json(new mensaje("Lista de favoritos agregada", ctx.body()));
        });

        app.get("/favoritos", ctx -> {
            ctx.contentType("application/json");
            ctx.json(ListaFavoritosBD.favoritos);
        });

        app.get("/favoritos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            ListaFavoritos encontrada = null;
            for (ListaFavoritos l : ListaFavoritosBD.favoritos) {
                if (l.getId() == Integer.parseInt(id)) {
                    encontrada = l;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Lista de favoritos no encontrada", ""));
            }
        });

        app.put("/favoritos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            ListaFavoritos actualizada = mapper.readValue(ctx.body(), ListaFavoritos.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < ListaFavoritosBD.favoritos.size(); i++) {
                if (ListaFavoritosBD.favoritos.get(i).getId() == Integer.parseInt(id)) {
                    ListaFavoritosBD.favoritos.set(i, actualizada);
                    ctx.json(new mensaje("Lista de favoritos actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Lista de favoritos no encontrada", ""));
        });

        app.delete("/favoritos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < ListaFavoritosBD.favoritos.size(); i++) {
                if (ListaFavoritosBD.favoritos.get(i).getId() == Integer.parseInt(id)) {
                    ListaFavoritosBD.favoritos.remove(i);
                    ctx.json(new mensaje("Lista de favoritos eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Lista de favoritos no encontrada", ""));
        });
    }
}