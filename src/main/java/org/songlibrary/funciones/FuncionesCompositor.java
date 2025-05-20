package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.CompositorBD;
import org.songlibrary.modelos.Compositor;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesCompositor {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear compositor
        app.post("/compositores", ctx -> {
            ctx.contentType("application/json");
            Compositor compositor = mapper.readValue(ctx.body(), Compositor.class);
            compositor.setId(CompositorBD.autoId++);
            CompositorBD.compositores.add(compositor);
            ctx.json(new mensaje("Compositor agregado", ctx.body()));
        });

        // Obtener todos los compositores
        app.get("/compositores", ctx -> {
            ctx.contentType("application/json");
            ctx.json(CompositorBD.compositores);
        });

        // Obtener compositor por ID
        app.get("/compositores/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            Compositor compositor = null;
            for (Compositor c : CompositorBD.compositores) {
                if (c.getId() == Integer.parseInt(id)) {
                    compositor = c;
                    break;
                }
            }
            if (compositor != null) {
                ctx.json(compositor);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Compositor no encontrado", ""));
            }
        });

        // Actualizar compositor
        app.put("/compositores/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Compositor actualizado = mapper.readValue(ctx.body(), Compositor.class);
            actualizado.setId(Integer.parseInt(id));
            for (int i = 0; i < CompositorBD.compositores.size(); i++) {
                if (CompositorBD.compositores.get(i).getId() == Integer.parseInt(id)) {
                    CompositorBD.compositores.set(i, actualizado);
                    ctx.json(new mensaje("Compositor actualizado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Compositor no encontrado", ""));
        });

        // Eliminar compositor
        app.delete("/compositores/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < CompositorBD.compositores.size(); i++) {
                if (CompositorBD.compositores.get(i).getId() == Integer.parseInt(id)) {
                    CompositorBD.compositores.remove(i);
                    ctx.json(new mensaje("Compositor eliminado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Compositor no encontrado", ""));
        });
    }
}
