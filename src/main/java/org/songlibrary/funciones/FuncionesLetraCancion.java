package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.LetraCancionBD;
import org.songlibrary.modelos.LetraCancion;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesLetraCancion {

    public static void FuncionesCRUD(Javalin app,ObjectMapper mapper) {

        app.post("/letras", ctx -> {
            ctx.contentType("application/json");
            LetraCancion letra = mapper.readValue(ctx.body(), LetraCancion.class);
            letra.setId(LetraCancionBD.autoId++);
            LetraCancionBD.letras.add(letra);
            ctx.json(new mensaje("Letra agregada", ctx.body()));
        });

        app.get("/letras", ctx -> {
            ctx.contentType("application/json");
            ctx.json(LetraCancionBD.letras);
        });

        app.get("/letras/{id}", ctx -> {
            ctx.contentType("application/json");
            int id = Integer.parseInt(ctx.pathParam("id"));

            for (LetraCancion l : LetraCancionBD.letras) {
                if (l.getId() == id) {
                    ctx.json(l);
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Letra no encontrada", ""));
        });

        app.put("/letras/{id}", ctx -> {
            ctx.contentType("application/json");
            int id = Integer.parseInt(ctx.pathParam("id"));
            LetraCancion actualizada = mapper.readValue(ctx.body(), LetraCancion.class);
            actualizada.setId(id);

            for (int i = 0; i < LetraCancionBD.letras.size(); i++) {
                if (LetraCancionBD.letras.get(i).getId() == id) {
                    LetraCancionBD.letras.set(i, actualizada);
                    ctx.json(new mensaje("Letra actualizada", ""));
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Letra no encontrada", ""));
        });

        app.delete("/letras/{id}", ctx -> {
            ctx.contentType("application/json");
            int id = Integer.parseInt(ctx.pathParam("id"));

            for (int i = 0; i < LetraCancionBD.letras.size(); i++) {
                if (LetraCancionBD.letras.get(i).getId() == id) {
                    LetraCancionBD.letras.remove(i);
                    ctx.json(new mensaje("Letra eliminada", ""));
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Letra no encontrada", ""));
        });
    }
}
