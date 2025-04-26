package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.GeneroBD;
import org.songlibrary.modelos.Genero;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesGenero {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        // Crear un género
        post("/generos", (request, response) -> {
            response.type("application/json");
            Genero genero = mapper.readValue(request.body(), Genero.class);
            genero.setId(GeneroBD.autoId++);
            GeneroBD.generos.add(genero);
            return mapper.writeValueAsString(new mensaje("Género agregado", request.body()));
        });

        // Obtener todos los géneros
        get("/generos", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(GeneroBD.generos);
        });

        // Obtener género por ID
        get("/generos/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (id == null) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID no proporcionado", ""));
            }

            Genero genero = null;
            for (Genero g : GeneroBD.generos) {
                if (g.getId() == Integer.parseInt(id)) {
                    genero = g;
                    break;
                }
            }

            if (genero != null) {
                return mapper.writeValueAsString(genero);
            } else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Género no encontrado", ""));
            }
        });

        // Actualizar género
        put("/generos/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            Genero actualizado = mapper.readValue(request.body(), Genero.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < GeneroBD.generos.size(); i++) {
                if (GeneroBD.generos.get(i).getId() == Integer.parseInt(id)) {
                    GeneroBD.generos.set(i, actualizado);
                    return mapper.writeValueAsString(new mensaje("Género actualizado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Género no encontrado", ""));
        });

        // Eliminar género
        delete("/generos/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            for (int i = 0; i < GeneroBD.generos.size(); i++) {
                if (GeneroBD.generos.get(i).getId() == Integer.parseInt(id)) {
                    GeneroBD.generos.remove(i);
                    return mapper.writeValueAsString(new mensaje("Género eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Género no encontrado", ""));
        });
    }
}
