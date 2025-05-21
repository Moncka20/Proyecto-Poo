package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.HistorialReproduccionBD;
import org.songlibrary.modelos.HistorialReproduccion;
import org.songlibrary.modelos.mensaje;


import io.javalin.Javalin;

public class FuncionesHistorialReproduccion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/historiales", ctx -> {
            ctx.contentType("application/json");
            HistorialReproduccion historial = mapper.readValue(ctx.body(), HistorialReproduccion.class);
            historial.setId(HistorialReproduccionBD.autoId++);
            HistorialReproduccionBD.historiales.add(historial);
            ctx.json(new mensaje("Historial de reproducción agregado", ctx.body()));
        });

        app.get("/historiales", ctx -> {
            ctx.contentType("application/json");
            ctx.json(HistorialReproduccionBD.historiales);
        });

        app.get("/historiales/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            HistorialReproduccion encontrado = null;
            for (HistorialReproduccion h : HistorialReproduccionBD.historiales) {
                if (h.getId() == Integer.parseInt(id)) {
                    encontrado = h;
                    break;
                }
            }
            if (encontrado != null) {
                ctx.json(encontrado);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Historial de reproducción no encontrado", ""));
            }
        });

        app.put("/historiales/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            HistorialReproduccion actualizado = mapper.readValue(ctx.body(), HistorialReproduccion.class);
            actualizado.setId(Integer.parseInt(id));
            for (int i = 0; i < HistorialReproduccionBD.historiales.size(); i++) {
                if (HistorialReproduccionBD.historiales.get(i).getId() == Integer.parseInt(id)) {
                    HistorialReproduccionBD.historiales.set(i, actualizado);
                    ctx.json(new mensaje("Historial de reproducción actualizado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Historial de reproducción no encontrado", ""));
        });

        app.delete("/historiales/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < HistorialReproduccionBD.historiales.size(); i++) {
                if (HistorialReproduccionBD.historiales.get(i).getId() == Integer.parseInt(id)) {
                    HistorialReproduccionBD.historiales.remove(i);
                    ctx.json(new mensaje("Historial de reproducción eliminado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Historial de reproducción no encontrado", ""));
        });
    }
}
