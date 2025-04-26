package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.modelos.ProductorMusical;
import org.songlibrary.modelos.ProductorMusicalBD;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesProductor {

    public static void configurarFunciones(ObjectMapper mapper) {

        // Crear un productor musical
        post("/productores", (request, response) -> {
            response.type("application/json");
            ProductorMusical productor = mapper.readValue(request.body(), ProductorMusical.class);
            productor.setId(ProductorMusicalBD.autoId++);
            ProductorMusicalBD.productores.add(productor);
            return mapper.writeValueAsString(new mensaje("Productor agregado", request.body()));
        });

        // Obtener todos los productores
        get("/productores", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(ProductorMusicalBD.productores);
        });

        // Obtener productor por ID
        get("/productores/:id", (request, response) -> {
            response.type("application/json");
            String id = request.params(":id");

            if (id == null) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID no proporcionado", ""));
            }

            ProductorMusical encontrado = null;
            for (ProductorMusical p : ProductorMusicalBD.productores) {
                if (p.getId() == Integer.parseInt(id)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                return mapper.writeValueAsString(encontrado);
            } else {
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Productor no encontrado", ""));
            }
        });

        // Actualizar productor
        put("/productores/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            ProductorMusical actualizado = mapper.readValue(request.body(), ProductorMusical.class);
            actualizado.setId(id);

            for (int i = 0; i < ProductorMusicalBD.productores.size(); i++) {
                if (ProductorMusicalBD.productores.get(i).getId() == id) {
                    ProductorMusicalBD.productores.set(i, actualizado);
                    return mapper.writeValueAsString(new mensaje("Productor actualizado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Productor no encontrado", ""));
        });

        // Eliminar productor
        delete("/productores/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (int i = 0; i < ProductorMusicalBD.productores.size(); i++) {
                if (ProductorMusicalBD.productores.get(i).getId() == id) {
                    ProductorMusicalBD.productores.remove(i);
                    return mapper.writeValueAsString(new mensaje("Productor eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Productor no encontrado", ""));
        });
    }
}