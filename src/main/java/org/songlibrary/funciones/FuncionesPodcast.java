package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.modelos.Podcast;
import org.songlibrary.modelos.PodcastBD;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesPodcast {

    public static void configurarFunciones(ObjectMapper mapper) {

        // Crear un podcast
        post("/podcasts", (request, response) -> {
            response.type("application/json");
            Podcast podcast = mapper.readValue(request.body(), Podcast.class);
            podcast.setId(PodcastBD.autoId++);
            PodcastBD.podcasts.add(podcast);
            return mapper.writeValueAsString(new mensaje("Podcast agregado", request.body()));
        });

        // Obtener todos los podcasts
        get("/podcasts", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(PodcastBD.podcasts);
        });

        // Obtener podcast por ID
        get("/podcasts/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (id == null) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID no proporcionado", ""));
            }

            Podcast encontrado = null;
            for (Podcast p : PodcastBD.podcasts) {
                if (p.getId() == Integer.parseInt(id)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                return mapper.writeValueAsString(encontrado);
            } else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Podcast no encontrado", ""));
            }
        });

        // Actualizar podcast
        put("/podcasts/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            Podcast actualizado = mapper.readValue(request.body(), Podcast.class);
            actualizado.setId(id);

            for (int i = 0; i < PodcastBD.podcasts.size(); i++) {
                if (PodcastBD.podcasts.get(i).getId() == id) {
                    PodcastBD.podcasts.set(i, actualizado);
                    return mapper.writeValueAsString(new mensaje("Podcast actualizado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Podcast no encontrado", ""));
        });

        // Eliminar podcast
        delete("/podcasts/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (int i = 0; i < PodcastBD.podcasts.size(); i++) {
                if (PodcastBD.podcasts.get(i).getId() == id) {
                    PodcastBD.podcasts.remove(i);
                    return mapper.writeValueAsString(new mensaje("Podcast eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Podcast no encontrado", ""));
        });
    }
}
