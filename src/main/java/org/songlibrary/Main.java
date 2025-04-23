package org.songlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.songlibrary.modelos.Cancion;
import org.songlibrary.modelos.CancionBD;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        port(4567);

        // Ruta de prueba
        get("/", (request, response) -> {
            response.type("application/json");
            return "{\"mensaje\": \"API de Canciones funcionando\"}";
        });

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
            int id = Integer.parseInt(request.params(":id"));
            Cancion encontrada = CancionBD.canciones.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (encontrada != null) {
                return mapper.writeValueAsString(encontrada);
            } else {
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
    }
}
