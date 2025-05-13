package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.CompositorBD;
import org.songlibrary.modelos.Compositor;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesCompositor {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        post("/compositores", (request, response) -> {
            response.type("application/json");
            Compositor compositor = mapper.readValue(request.body(), Compositor.class);
            compositor.setId(CompositorBD.autoId++);
            CompositorBD.compositores.add(compositor);
            return mapper.writeValueAsString(new mensaje("Compositor agregado", request.body()));
        });

        get("/compositores", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(CompositorBD.compositores);
        });

        get("/compositores/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (Compositor c : CompositorBD.compositores) {
                if (c.getId() == id) {
                    return mapper.writeValueAsString(c);
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Compositor no encontrado", ""));
        });

        put("/compositores/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            Compositor actualizado = mapper.readValue(request.body(), Compositor.class);
            actualizado.setId(id);

            for (int i = 0; i < CompositorBD.compositores.size(); i++) {
                if (CompositorBD.compositores.get(i).getId() == id) {
                    CompositorBD.compositores.set(i, actualizado);
                    return mapper.writeValueAsString(new mensaje("Compositor actualizado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Compositor no encontrado", ""));
        });

        delete("/compositores/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));

            for (int i = 0; i < CompositorBD.compositores.size(); i++) {
                if (CompositorBD.compositores.get(i).getId() == id) {
                    CompositorBD.compositores.remove(i);
                    return mapper.writeValueAsString(new mensaje("Compositor eliminado", ""));
                }
            }

            response.status(404);
            return mapper.writeValueAsString(new mensaje("Compositor no encontrado", ""));
        });
    }
}
