package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.GeneroBD;
import org.songlibrary.modelos.Genero;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesGenero {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear un género
        app.post("/generos", ctx -> {
            ctx.contentType("application/json");
            Genero genero = mapper.readValue(ctx.body(), Genero.class);
            genero.setId(GeneroBD.autoId++);
            GeneroBD.generos.add(genero);
            ctx.json(new mensaje("Género agregado", ctx.body()));
        });

        // Obtener todos los géneros
        app.get("/generos", ctx -> {
            ctx.contentType("application/json");
            ctx.json(GeneroBD.generos);
        });

        // Obtener género por ID
        app.get("/generos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }

            Genero genero = null;
            for (Genero g : GeneroBD.generos) {
                if (g.getId() == Integer.parseInt(id)) {
                    genero = g;
                    break;
                }
            }

            if (genero != null) {
                ctx.json(genero);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Género no encontrado", ""));
            }
        });

        // Actualizar género
        app.put("/generos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Genero actualizado = mapper.readValue(ctx.body(), Genero.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < GeneroBD.generos.size(); i++) {
                if (GeneroBD.generos.get(i).getId() == Integer.parseInt(id)) {
                    GeneroBD.generos.set(i, actualizado);
                    ctx.json(new mensaje("Género actualizado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Género no encontrado", ""));
        });

        // Eliminar género
        app.delete("/generos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            for (int i = 0; i < GeneroBD.generos.size(); i++) {
                if (GeneroBD.generos.get(i).getId() == Integer.parseInt(id)) {
                    GeneroBD.generos.remove(i);
                    ctx.json(new mensaje("Género eliminado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Género no encontrado", ""));
        });
    }
}
