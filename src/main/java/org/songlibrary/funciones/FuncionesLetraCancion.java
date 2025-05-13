package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.LetraCancionBD;
import org.songlibrary.modelos.LetraCancion;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesLetraCancion {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        post("/letras", (request, response) -> {
            response.type("application/json");
            LetraCancion letra = mapper.readValue(request.body(), LetraCancion.class);
            letra.setId(LetraCancionBD.autoId++);
            LetraCancionBD.letras.add(letra);
            return mapper.writeValueAsString(new mensaje("Letra agregada", request.body()));
        });

        get("/letras", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(LetraCancionBD.letras);
        });

        get("/letras/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (LetraCancion l : LetraCancionBD.letras) {
                if (l.getId() == id) {
                    return mapper.writeValueAsString(l);
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Letra no encontrada", ""));
        });

        put("/letras/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            LetraCancion actualizada = mapper.readValue(request.body(), LetraCancion.class);
            actualizada.setId(id);

            for (int i = 0; i < LetraCancionBD.letras.size(); i++) {
                if (LetraCancionBD.letras.get(i).getId() == id) {
                    LetraCancionBD.letras.set(i, actualizada);
                    return mapper.writeValueAsString(new mensaje("Letra actualizada", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Letra no encontrada", ""));
        });

        delete("/letras/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (int i = 0; i < LetraCancionBD.letras.size(); i++) {
                if (LetraCancionBD.letras.get(i).getId() == id) {
                    LetraCancionBD.letras.remove(i);
                    return mapper.writeValueAsString(new mensaje("Letra eliminada", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Letra no encontrada", ""));
        });
    }
}
