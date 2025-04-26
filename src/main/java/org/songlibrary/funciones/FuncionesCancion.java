package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.CancionBD;
import org.songlibrary.modelos.Cancion;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesCancion {

    public static void FuncionesCRUD(ObjectMapper mapper) {

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
            } else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Canción no encontrada", ""));
            }
        });

        // Actualizar canción
        put("/canciones/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            Cancion actualizada = mapper.readValue(request.body(), Cancion.class);
            actualizada.setId(Integer.parseInt(id));

            for (int i = 0; i < CancionBD.canciones.size(); i++) {
                if (CancionBD.canciones.get(i).getId() == Integer.parseInt(id)) {
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
            String id = request.params(":id");

            for (int i = 0; i < CancionBD.canciones.size(); i++) {
                if (CancionBD.canciones.get(i).getId() == Integer.parseInt(id)) {
                    CancionBD.canciones.remove(i);
                    return mapper.writeValueAsString(new mensaje("Canción eliminada", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Canción no encontrada", ""));
        });
    }
}
