package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.DiscoBD;
import org.songlibrary.modelos.Disco;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesDisco {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        // Crear un disco
        post("/discos", (request, response) -> {
            response.type("application/json");
            Disco disco = mapper.readValue(request.body(), Disco.class);
            disco.setId(DiscoBD.autoId++);
            DiscoBD.discos.add(disco);
            return mapper.writeValueAsString(new mensaje("Disco agregado", request.body()));
        });

        // Obtener todos los discos
        get("/discos", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(DiscoBD.discos);
        });

        // Obtener disco por ID
        get("/discos/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (id == null) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID no proporcionado", ""));
            }

            Disco disco = null;
            for (Disco d : DiscoBD.discos) {
                if (d.getId() == Integer.parseInt(id)) {
                    disco = d;
                    break;
                }
            }

            if (disco != null) {
                return mapper.writeValueAsString(disco);
            } else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Disco no encontrado", ""));
            }
        });

        // Actualizar disco
        put("/discos/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");
            Disco actualizado = mapper.readValue(request.body(), Disco.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < DiscoBD.discos.size(); i++) {
                if (DiscoBD.discos.get(i).getId() == Integer.parseInt(id)) {
                    DiscoBD.discos.set(i, actualizado);
                    return mapper.writeValueAsString(new mensaje("Disco actualizado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Disco no encontrado", ""));
        });

        // Eliminar disco
        delete("/discos/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            for (int i = 0; i < DiscoBD.discos.size(); i++) {
                if (DiscoBD.discos.get(i).getId() == Integer.parseInt(id)) {
                    DiscoBD.discos.remove(i);
                    return mapper.writeValueAsString(new mensaje("Disco eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Disco no encontrado", ""));
        });
    }
}
