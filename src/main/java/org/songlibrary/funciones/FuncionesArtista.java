package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ArtistaBD;
import org.songlibrary.modelos.Artista;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesArtista {

    public static void FuncionesCRUD(Javalin app,ObjectMapper mapper) {

        // Crear un artista
        app.post("/artistas", ctx -> {
            ctx.contentType("application/json");
            Artista artista = mapper.readValue(ctx.body(), Artista.class);
            artista.setId(ArtistaBD.autoId++);
            ArtistaBD.artistas.add(artista);
            ctx.json(new mensaje("Artista agregado", ctx.body()));
        });

        // Obtener todos los artistas
        app.get("/artistas", ctx -> {
            ctx.contentType("application/json");
            ctx.json(ArtistaBD.artistas);
        });

        // Obtener artista por ID
        app.get("/artistas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
            }

            Artista artista = null;
            for (Artista a : ArtistaBD.artistas) {
                if (a.getId() == Integer.parseInt(id)) {
                    artista = a;
                    break;
                }
            }

            if (artista != null) {
                ctx.json(artista);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Artista no encontrado", ""));
            }
        });

        // Actualizar artista
        app.put("/artistas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Artista actualizado = mapper.readValue(ctx.body(), Artista.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
                if (ArtistaBD.artistas.get(i).getId() == Integer.parseInt(id)) {
                    ArtistaBD.artistas.set(i, actualizado);
                    ctx.json(new mensaje("Artista actualizado", ""));
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Artista no encontrado", ""));
        });

        // Eliminar artista
        app.delete("/artistas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
                if (ArtistaBD.artistas.get(i).getId() == Integer.parseInt(id)) {
                    ArtistaBD.artistas.remove(i);
                    ctx.json(new mensaje("Artista eliminado", ""));
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Artista no encontrado", ""));
        });
    }
}

