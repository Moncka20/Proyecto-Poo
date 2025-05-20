package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.modelos.Podcast;
import org.songlibrary.BD.PodcastBD;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesPodcast {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear un podcast
        app.post("/podcasts", ctx -> {
            ctx.contentType("application/json");
            Podcast podcast = mapper.readValue(ctx.body(), Podcast.class);
            podcast.setId(PodcastBD.autoId++);
            PodcastBD.podcasts.add(podcast);
            ctx.json(new mensaje("Podcast agregado", ctx.body()));
        });

        // Obtener todos los podcasts
        app.get("/podcasts", ctx -> {
            ctx.contentType("application/json");
            ctx.json(PodcastBD.podcasts);
        });

        // Obtener podcast por ID
        app.get("/podcasts/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }

            Podcast encontrado = null;
            for (Podcast p : PodcastBD.podcasts) {
                if (p.getId() == Integer.parseInt(id)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                ctx.json(encontrado);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Podcast no encontrado", ""));
            }
        });

        // Actualizar podcast
        app.put("/podcasts/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Podcast actualizado = mapper.readValue(ctx.body(), Podcast.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < PodcastBD.podcasts.size(); i++) {
                if (PodcastBD.podcasts.get(i).getId() == Integer.parseInt(id)) {
                    PodcastBD.podcasts.set(i, actualizado);
                    ctx.json(new mensaje("Podcast actualizado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Podcast no encontrado", ""));
        });

        // Eliminar podcast
        app.delete("/podcasts/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            for (int i = 0; i < PodcastBD.podcasts.size(); i++) {
                if (PodcastBD.podcasts.get(i).getId() == Integer.parseInt(id)) {
                    PodcastBD.podcasts.remove(i);
                    ctx.json(new mensaje("Podcast eliminado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Podcast no encontrado", ""));
        });
    }
}
