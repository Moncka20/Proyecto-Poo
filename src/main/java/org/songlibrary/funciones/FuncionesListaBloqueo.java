package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ListaBloqueoBD;
import org.songlibrary.modelos.ListaBloqueo;
import org.songlibrary.modelos.mensaje;
import io.javalin.Javalin;

public class FuncionesListaBloqueo {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/bloqueos", ctx -> {
            ctx.contentType("application/json");
            ListaBloqueo bloqueo = mapper.readValue(ctx.body(), ListaBloqueo.class);
            bloqueo.setId(ListaBloqueoBD.autoId++);
            ListaBloqueoBD.bloqueos.add(bloqueo);
            ctx.json(new mensaje("Bloqueo agregado", ctx.body()));
        });

        app.get("/bloqueos", ctx -> {
            ctx.contentType("application/json");
            ctx.json(ListaBloqueoBD.bloqueos);
        });

        app.get("/bloqueos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            ListaBloqueo encontrada = null;
            for (ListaBloqueo b : ListaBloqueoBD.bloqueos) {
                if (b.getId() == Integer.parseInt(id)) {
                    encontrada = b;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Bloqueo no encontrado", ""));
            }
        });

        app.put("/bloqueos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            ListaBloqueo actualizada = mapper.readValue(ctx.body(), ListaBloqueo.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < ListaBloqueoBD.bloqueos.size(); i++) {
                if (ListaBloqueoBD.bloqueos.get(i).getId() == Integer.parseInt(id)) {
                    ListaBloqueoBD.bloqueos.set(i, actualizada);
                    ctx.json(new mensaje("Bloqueo actualizado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Bloqueo no encontrado", ""));
        });

        app.delete("/bloqueos/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < ListaBloqueoBD.bloqueos.size(); i++) {
                if (ListaBloqueoBD.bloqueos.get(i).getId() == Integer.parseInt(id)) {
                    ListaBloqueoBD.bloqueos.remove(i);
                    ctx.json(new mensaje("Bloqueo eliminado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Bloqueo no encontrado", ""));
        });
    }
}
