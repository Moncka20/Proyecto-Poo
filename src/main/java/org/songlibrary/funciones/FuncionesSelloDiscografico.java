package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.SelloDiscograficoBD;
import org.songlibrary.modelos.SelloDiscografico;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesSelloDiscografico {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear sello
        app.post("/sellos", ctx -> {
            ctx.contentType("application/json");
            SelloDiscografico sello = mapper.readValue(ctx.body(), SelloDiscografico.class);
            sello.setId(SelloDiscograficoBD.autoId++);
            SelloDiscograficoBD.sellos.add(sello);
            ctx.json(new mensaje("Sello agregado", ctx.body()));
        });

        // Obtener todos los sellos
        app.get("/sellos", ctx -> {
            ctx.contentType("application/json");
            ctx.json(SelloDiscograficoBD.sellos);
        });

        // Obtener sello por ID
        app.get("/sellos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            try {
                int idInt = Integer.parseInt(id);
                for (SelloDiscografico s : SelloDiscograficoBD.sellos) {
                    if (s.getId() == idInt) {
                        ctx.json(s);
                        return;
                    }
                }
                ctx.status(404);
                ctx.json(new mensaje("Sello no encontrado", ""));
            } catch (NumberFormatException e) {
                ctx.status(400);
                ctx.json(new mensaje("ID inválido", ""));
            }
        });

        // Actualizar sello
        app.put("/sellos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            try {
                int idInt = Integer.parseInt(id);
                SelloDiscografico actualizado = mapper.readValue(ctx.body(), SelloDiscografico.class);
                actualizado.setId(idInt);

                for (int i = 0; i < SelloDiscograficoBD.sellos.size(); i++) {
                    if (SelloDiscograficoBD.sellos.get(i).getId() == idInt) {
                        SelloDiscograficoBD.sellos.set(i, actualizado);
                        ctx.json(new mensaje("Sello actualizado", ""));
                        return;
                    }
                }

                ctx.status(404);
                ctx.json(new mensaje("Sello no encontrado", ""));
            } catch (NumberFormatException e) {
                ctx.status(400);
                ctx.json(new mensaje("ID inválido", ""));
            }
        });

        // Eliminar sello
        app.delete("/sellos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            try {
                int idInt = Integer.parseInt(id);
                for (int i = 0; i < SelloDiscograficoBD.sellos.size(); i++) {
                    if (SelloDiscograficoBD.sellos.get(i).getId() == idInt) {
                        SelloDiscograficoBD.sellos.remove(i);
                        ctx.json(new mensaje("Sello eliminado", ""));
                        return;
                    }
                }
                ctx.status(404);
                ctx.json(new mensaje("Sello no encontrado", ""));
            } catch (NumberFormatException e) {
                ctx.status(400);
                ctx.json(new mensaje("ID inválido", ""));
            }
        });
    }
}
