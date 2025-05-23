package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.BusquedaBD;
import org.songlibrary.modelos.Busqueda;
import org.songlibrary.modelos.mensaje;
import io.javalin.Javalin;

public class FuncionesBusqueda {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/busquedas", ctx -> {
            ctx.contentType("application/json");
            Busqueda busqueda = mapper.readValue(ctx.body(), Busqueda.class);
            busqueda.setId(BusquedaBD.autoId++);
            BusquedaBD.busquedas.add(busqueda);
            ctx.json(new mensaje("Búsqueda agregada", ctx.body()));
        });

        app.get("/busquedas", ctx -> {
            ctx.contentType("application/json");
            ctx.json(BusquedaBD.busquedas);
        });

        app.get("/busquedas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            Busqueda encontrada = null;
            for (Busqueda b : BusquedaBD.busquedas) {
                if (b.getId() == Integer.parseInt(id)) {
                    encontrada = b;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Búsqueda no encontrada", ""));
            }
        });

        app.put("/busquedas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Busqueda actualizada = mapper.readValue(ctx.body(), Busqueda.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < BusquedaBD.busquedas.size(); i++) {
                if (BusquedaBD.busquedas.get(i).getId() == Integer.parseInt(id)) {
                    BusquedaBD.busquedas.set(i, actualizada);
                    ctx.json(new mensaje("Búsqueda actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Búsqueda no encontrada", ""));
        });

        app.delete("/busquedas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < BusquedaBD.busquedas.size(); i++) {
                if (BusquedaBD.busquedas.get(i).getId() == Integer.parseInt(id)) {
                    BusquedaBD.busquedas.remove(i);
                    ctx.json(new mensaje("Búsqueda eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Búsqueda no encontrada", ""));
        });
    }
}