package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.SuscripcionBD;
import org.songlibrary.modelos.Suscripcion;
import org.songlibrary.modelos.mensaje;


import io.javalin.Javalin;

public class FuncionesSuscripcion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/suscripciones", ctx -> {
            ctx.contentType("application/json");
            Suscripcion suscripcion = mapper.readValue(ctx.body(), Suscripcion.class);
            suscripcion.setId(SuscripcionBD.autoId++);
            SuscripcionBD.suscripciones.add(suscripcion);
            ctx.json(new mensaje("Suscripción agregada", ctx.body()));
        });

        app.get("/suscripciones", ctx -> {
            ctx.contentType("application/json");
            ctx.json(SuscripcionBD.suscripciones);
        });

        app.get("/suscripciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            Suscripcion encontrada = null;
            for (Suscripcion s : SuscripcionBD.suscripciones) {
                if (s.getId() == Integer.parseInt(id)) {
                    encontrada = s;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Suscripción no encontrada", ""));
            }
        });

        app.put("/suscripciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Suscripcion actualizada = mapper.readValue(ctx.body(), Suscripcion.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < SuscripcionBD.suscripciones.size(); i++) {
                if (SuscripcionBD.suscripciones.get(i).getId() == Integer.parseInt(id)) {
                    SuscripcionBD.suscripciones.set(i, actualizada);
                    ctx.json(new mensaje("Suscripción actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Suscripción no encontrada", ""));
        });

        app.delete("/suscripciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < SuscripcionBD.suscripciones.size(); i++) {
                if (SuscripcionBD.suscripciones.get(i).getId() == Integer.parseInt(id)) {
                    SuscripcionBD.suscripciones.remove(i);
                    ctx.json(new mensaje("Suscripción eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Suscripción no encontrada", ""));
        });
    }
}