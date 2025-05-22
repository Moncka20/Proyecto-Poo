package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.EstadisticaCancionBD;
import org.songlibrary.modelos.EstadisticaCancion;
import org.songlibrary.modelos.mensaje;
import io.javalin.Javalin;

public class FuncionesEstadisticaCancion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/estadisticas", ctx -> {
            ctx.contentType("application/json");
            EstadisticaCancion estadistica = mapper.readValue(ctx.body(), EstadisticaCancion.class);
            estadistica.setId(EstadisticaCancionBD.autoId++);
            EstadisticaCancionBD.estadisticas.add(estadistica);
            ctx.json(new mensaje("Estadística de canción agregada", ctx.body()));
        });

        app.get("/estadisticas", ctx -> {
            ctx.contentType("application/json");
            ctx.json(EstadisticaCancionBD.estadisticas);
        });

        app.get("/estadisticas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            EstadisticaCancion encontrada = null;
            for (EstadisticaCancion e : EstadisticaCancionBD.estadisticas) {
                if (e.getId() == Integer.parseInt(id)) {
                    encontrada = e;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Estadística de canción no encontrada", ""));
            }
        });

        app.put("/estadisticas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            EstadisticaCancion actualizada = mapper.readValue(ctx.body(), EstadisticaCancion.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < EstadisticaCancionBD.estadisticas.size(); i++) {
                if (EstadisticaCancionBD.estadisticas.get(i).getId() == Integer.parseInt(id)) {
                    EstadisticaCancionBD.estadisticas.set(i, actualizada);
                    ctx.json(new mensaje("Estadística de canción actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Estadística de canción no encontrada", ""));
        });

        app.delete("/estadisticas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < EstadisticaCancionBD.estadisticas.size(); i++) {
                if (EstadisticaCancionBD.estadisticas.get(i).getId() == Integer.parseInt(id)) {
                    EstadisticaCancionBD.estadisticas.remove(i);
                    ctx.json(new mensaje("Estadística de canción eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Estadística de canción no encontrada", ""));
        });
    }
}
