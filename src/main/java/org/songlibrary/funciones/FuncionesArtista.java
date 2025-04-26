package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ArtistaBD;
import org.songlibrary.modelos.Artista;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesArtista {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        // Crear un artista
        post("/artistas", (request, response) -> {
            response.type("application/json");
            Artista artista = mapper.readValue(request.body(), Artista.class);
            artista.setId(ArtistaBD.autoId++);
            ArtistaBD.artistas.add(artista);
            return mapper.writeValueAsString(new mensaje("Artista agregado", request.body()));
        });

        // Obtener todos los artistas
        get("/artistas", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(ArtistaBD.artistas);
        });

        // Obtener artista por ID
        get("/artistas/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (id == null) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID no proporcionado", ""));
            }

            Artista artista = null;
            for (Artista a : ArtistaBD.artistas) {
                if (a.getId() == Integer.parseInt(id)) {
                    artista = a;
                    break;
                }
            }

            if (artista != null) {
                return mapper.writeValueAsString(artista);
            } else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Artista no encontrado", ""));
            }
        });

        // Actualizar artista
        put("/artistas/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            Artista actualizado = mapper.readValue(request.body(), Artista.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
                if (ArtistaBD.artistas.get(i).getId() == Integer.parseInt(id)) {
                    ArtistaBD.artistas.set(i, actualizado);
                    return mapper.writeValueAsString(new mensaje("Artista actualizado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Artista no encontrado", ""));
        });

        // Eliminar artista
        delete("/artistas/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
                if (ArtistaBD.artistas.get(i).getId() == Integer.parseInt(id)) {
                    ArtistaBD.artistas.remove(i);
                    return mapper.writeValueAsString(new mensaje("Artista eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Artista no encontrado", ""));
        });
    }
}

