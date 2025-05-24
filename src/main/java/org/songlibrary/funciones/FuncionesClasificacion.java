package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ClasificacionBD;
import org.songlibrary.modelos.Clasificacion;
import org.songlibrary.modelos.mensaje;
import io.javalin.Javalin;

public class FuncionesClasificacion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/clasificaciones", ctx -> {
            ctx.contentType("application/json");
            Clasificacion clasificacion = mapper.readValue(ctx.body(), Clasificacion.class);
            clasificacion.setId(ClasificacionBD.autoId++);
            ClasificacionBD.clasificaciones.add(clasificacion);
            ctx.json(new mensaje("Clasificación agregada", ctx.body()));
        });

        app.get("/clasificaciones", ctx -> {
            ctx.contentType("application/json");
            ctx.json(ClasificacionBD.clasificaciones);
        });

        app.get("/clasificaciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            Clasificacion encontrada = null;
            for (Clasificacion c : ClasificacionBD.clasificaciones) {
                if (c.getId() == Integer.parseInt(id)) {
                    encontrada = c;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Clasificación no encontrada", ""));
            }
        });

        app.put("/clasificaciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Clasificacion actualizada = mapper.readValue(ctx.body(), Clasificacion.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < ClasificacionBD.clasificaciones.size(); i++) {
                if (ClasificacionBD.clasificaciones.get(i).getId() == Integer.parseInt(id)) {
                    ClasificacionBD.clasificaciones.set(i, actualizada);
                    ctx.json(new mensaje("Clasificación actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Clasificación no encontrada", ""));
        });

        app.delete("/clasificaciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < ClasificacionBD.clasificaciones.size(); i++) {
                if (ClasificacionBD.clasificaciones.get(i).getId() == Integer.parseInt(id)) {
                    ClasificacionBD.clasificaciones.remove(i);
                    ctx.json(new mensaje("Clasificación eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Clasificación no encontrada", ""));
        });
    }
}
