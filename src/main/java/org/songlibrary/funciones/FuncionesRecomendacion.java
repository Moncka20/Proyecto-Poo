package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.RecomendacionBD;
import org.songlibrary.modelos.Recomendacion;
import org.songlibrary.modelos.mensaje;
import io.javalin.Javalin;

public class FuncionesRecomendacion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/recomendaciones", ctx -> {
            ctx.contentType("application/json");
            Recomendacion recomendacion = mapper.readValue(ctx.body(), Recomendacion.class);
            recomendacion.setId(RecomendacionBD.autoId++);
            RecomendacionBD.recomendaciones.add(recomendacion);
            ctx.json(new mensaje("Recomendación agregada", ctx.body()));
        });

        app.get("/recomendaciones", ctx -> {
            ctx.contentType("application/json");
            ctx.json(RecomendacionBD.recomendaciones);
        });

        app.get("/recomendaciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            Recomendacion encontrada = null;
            for (Recomendacion r : RecomendacionBD.recomendaciones) {
                if (r.getId() == Integer.parseInt(id)) {
                    encontrada = r;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Recomendación no encontrada", ""));
            }
        });

        app.put("/recomendaciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Recomendacion actualizada = mapper.readValue(ctx.body(), Recomendacion.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < RecomendacionBD.recomendaciones.size(); i++) {
                if (RecomendacionBD.recomendaciones.get(i).getId() == Integer.parseInt(id)) {
                    RecomendacionBD.recomendaciones.set(i, actualizada);
                    ctx.json(new mensaje("Recomendación actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Recomendación no encontrada", ""));
        });

        app.delete("/recomendaciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < RecomendacionBD.recomendaciones.size(); i++) {
                if (RecomendacionBD.recomendaciones.get(i).getId() == Integer.parseInt(id)) {
                    RecomendacionBD.recomendaciones.remove(i);
                    ctx.json(new mensaje("Recomendación eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Recomendación no encontrada", ""));
        });
    }
}