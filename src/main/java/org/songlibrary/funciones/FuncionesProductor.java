package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.modelos.ProductorMusical;
import org.songlibrary.BD.ProductorMusicalBD;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesProductor {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear un productor musical
        app.post("/productores", ctx -> {
            ctx.contentType("application/json");
            ProductorMusical productor = mapper.readValue(ctx.body(), ProductorMusical.class);
            productor.setId(ProductorMusicalBD.autoId++);
            ProductorMusicalBD.productores.add(productor);
            ctx.json(new mensaje("Productor agregado", ctx.body()));
        });

        // Obtener todos los productores
        app.get("/productores", ctx -> {
            ctx.contentType("application/json");
            ctx.json(ProductorMusicalBD.productores);
        });

        // Obtener productor por ID
        app.get("/productores/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }

            ProductorMusical encontrado = null;
            for (ProductorMusical p : ProductorMusicalBD.productores) {
                if (p.getId() == Integer.parseInt(id)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                ctx.json(encontrado);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Productor no encontrado", ""));
            }
        });

        // Actualizar productor
        app.put("/productores/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            ProductorMusical actualizado = mapper.readValue(ctx.body(), ProductorMusical.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < ProductorMusicalBD.productores.size(); i++) {
                if (ProductorMusicalBD.productores.get(i).getId() == Integer.parseInt(id)) {
                    ProductorMusicalBD.productores.set(i, actualizado);
                    ctx.json(new mensaje("Productor actualizado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Productor no encontrado", ""));
        });

        // Eliminar productor
        app.delete("/productores/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            for (int i = 0; i < ProductorMusicalBD.productores.size(); i++) {
                if (ProductorMusicalBD.productores.get(i).getId() == Integer.parseInt(id)) {
                    ProductorMusicalBD.productores.remove(i);
                    ctx.json(new mensaje("Productor eliminado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Productor no encontrado", ""));
        });
    }
}