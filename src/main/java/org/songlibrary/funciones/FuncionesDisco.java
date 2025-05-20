package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.DiscoBD;
import org.songlibrary.modelos.Disco;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesDisco {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear un disco
        app.post("/discos", ctx -> {
            ctx.contentType("application/json");
            Disco disco = mapper.readValue(ctx.body(), Disco.class);
            disco.setId(DiscoBD.autoId++);
            DiscoBD.discos.add(disco);
            ctx.json(new mensaje("Disco agregado", ctx.body()));
        });

        // Obtener todos los discos
        app.get("/discos", ctx -> {
            ctx.contentType("application/json");
            ctx.json(DiscoBD.discos);
        });

        // Obtener disco por ID
        app.get("/discos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }

            Disco disco = null;
            for (Disco d : DiscoBD.discos) {
                if (d.getId() == Integer.parseInt(id)) {
                    disco = d;
                    break;
                }
            }

            if (disco != null) {
                ctx.json(disco);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Disco no encontrado", ""));
            }
        });

        // Actualizar disco
        app.put("/discos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Disco actualizado = mapper.readValue(ctx.body(), Disco.class);
            actualizado.setId(Integer.parseInt(id));

            for (int i = 0; i < DiscoBD.discos.size(); i++) {
                if (DiscoBD.discos.get(i).getId() == Integer.parseInt(id)) {
                    DiscoBD.discos.set(i, actualizado);
                    ctx.json(new mensaje("Disco actualizado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Disco no encontrado", ""));
        });

        // Eliminar disco
        app.delete("/discos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            for (int i = 0; i < DiscoBD.discos.size(); i++) {
                if (DiscoBD.discos.get(i).getId() == Integer.parseInt(id)) {
                    DiscoBD.discos.remove(i);
                    ctx.json(new mensaje("Disco eliminado", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Disco no encontrado", ""));
        });
    }
}
