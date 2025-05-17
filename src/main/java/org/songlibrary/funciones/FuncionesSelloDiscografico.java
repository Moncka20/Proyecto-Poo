package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.SelloDiscograficoBD;
import org.songlibrary.modelos.SelloDiscografico;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesSelloDiscografico {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        // Crear sello
        post("/sellos", (request, response) -> {
            response.type("application/json");
            SelloDiscografico sello = mapper.readValue(request.body(), SelloDiscografico.class);
            sello.setId(SelloDiscograficoBD.autoId++);
            SelloDiscograficoBD.sellos.add(sello);
            return mapper.writeValueAsString(new mensaje("Sello agregado", request.body()));
        });

        // Obtener todos los sellos
        get("/sellos", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(SelloDiscograficoBD.sellos);
        });

        // Obtener sello por ID
        get("/sellos/:id", (request, response) -> {
            response.type("application/json");
            try {
                int idInt = Integer.parseInt(request.params(":id"));
                for (SelloDiscografico s : SelloDiscograficoBD.sellos) {
                    if (s.getId() == idInt) {
                        return mapper.writeValueAsString(s);
                    }
                }
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Sello no encontrado", ""));
            } catch (NumberFormatException e) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID inválido", ""));
            }
        });

        // Actualizar sello
        put("/sellos/:id", (request, response) -> {
            response.type("application/json");
            try {
                int idInt = Integer.parseInt(request.params(":id"));
                SelloDiscografico actualizado = mapper.readValue(request.body(), SelloDiscografico.class);
                actualizado.setId(idInt);

                for (int i = 0; i < SelloDiscograficoBD.sellos.size(); i++) {
                    if (SelloDiscograficoBD.sellos.get(i).getId() == idInt) {
                        SelloDiscograficoBD.sellos.set(i, actualizado);
                        return mapper.writeValueAsString(new mensaje("Sello actualizado", ""));
                    }
                }

                response.status(404);
                return mapper.writeValueAsString(new mensaje("Sello no encontrado", ""));
            } catch (NumberFormatException e) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID inválido", ""));
            }
        });

        // Eliminar sello
        delete("/sellos/:id", (request, response) -> {
            response.type("application/json");
            try {
                int idInt = Integer.parseInt(request.params(":id"));
                for (int i = 0; i < SelloDiscograficoBD.sellos.size(); i++) {
                    if (SelloDiscograficoBD.sellos.get(i).getId() == idInt) {
                        SelloDiscograficoBD.sellos.remove(i);
                        return mapper.writeValueAsString(new mensaje("Sello eliminado", ""));
                    }
                }

                response.status(404);
                return mapper.writeValueAsString(new mensaje("Sello no encontrado", ""));
            } catch (NumberFormatException e) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID inválido", ""));
            }
        });
    }
}
