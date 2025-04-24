package org.songlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.songlibrary.modelos.Cancion;
import org.songlibrary.modelos.CancionBD;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.modelos.Artista;
import org.songlibrary.modelos.ArtistaBD;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        port(4567);

        // Ruta de prueba
        get("/", (request, response) -> {
            response.type("application/json");
            return "{\"mensaje\": \"funcionando\"}";
        });

        //Objeto Cancion

        // Crear una canción
        post("/canciones", (request, response) -> {
            response.type("application/json");
            Cancion cancion = mapper.readValue(request.body(), Cancion.class);
            cancion.setId(CancionBD.autoId++);
            CancionBD.canciones.add(cancion);
            return mapper.writeValueAsString(new mensaje("Canción agregada", request.body()));
        });

        // Obtener todas las canciones
        get("/canciones", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(CancionBD.canciones);
        });

        // Obtener canción por ID
        get("/canciones/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (id == null) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID no proporcionado", ""));
            }

            Cancion cancion = null;
            for (Cancion c : CancionBD.canciones) {
                if (c.getId() == Integer.parseInt(id)) {
                    cancion = c;
                    break;
                }
            }

            if (cancion != null) {
                return mapper.writeValueAsString(cancion);
            }
            else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Canción no encontrada", ""));
            }
        });

        // Actualizar canción
        put("/canciones/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            Cancion actualizada = mapper.readValue(request.body(), Cancion.class);
            actualizada.setId(id);

            for (int i = 0; i < CancionBD.canciones.size(); i++) {
                if (CancionBD.canciones.get(i).getId() == id) {
                    CancionBD.canciones.set(i, actualizada);
                    return mapper.writeValueAsString(new mensaje("Canción actualizada", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Canción no encontrada", ""));
        });

        // Eliminar canción
        delete("/canciones/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (int i = 0; i < CancionBD.canciones.size(); i++) {
                if (CancionBD.canciones.get(i).getId() == id) {
                    CancionBD.canciones.remove(i);
                    return mapper.writeValueAsString(new mensaje("Canción eliminada", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Canción no encontrada", ""));
        });

        //Objeto Artista

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
            }
            else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Artista no encontrado", ""));
            }
        });

        // Actualizar artista
        put("/artistas/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            Artista actualizado = mapper.readValue(request.body(), Artista.class);
            actualizado.setId(id);

            for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
                if (ArtistaBD.artistas.get(i).getId() == id) {
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
            int id = Integer.parseInt(request.params(":id"));

            for (int i = 0; i < ArtistaBD.artistas.size(); i++) {
                if (ArtistaBD.artistas.get(i).getId() == id) {
                    ArtistaBD.artistas.remove(i);
                    return mapper.writeValueAsString(new mensaje("Artista eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Artista no encontrado", ""));
        });

    }
}
