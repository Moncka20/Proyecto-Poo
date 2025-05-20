package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.CancionBD;
import org.songlibrary.modelos.Cancion;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesCancion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear una canción
        app.post("/canciones", ctx -> {
            ctx.contentType("application/json");
            Cancion cancion = mapper.readValue(ctx.body(), Cancion.class);
            cancion.setId(CancionBD.autoId++);
            CancionBD.canciones.add(cancion);
            ctx.json(new mensaje("Canción agregada", ctx.body()));
        });

        // Obtener todas las canciones
        app.get("/canciones", ctx -> {
            ctx.contentType("application/json");
            ctx.json(CancionBD.canciones);
        });

        // Obtener canción por ID
        app.get("/canciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }

            Cancion cancion = null;
            for (Cancion c : CancionBD.canciones) {
                if (c.getId() == Integer.parseInt(id)) {
                    cancion = c;
                    break;
                }
            }

            if (cancion != null) {
                ctx.json(cancion);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Canción no encontrada", ""));
            }
        });

        // Actualizar canción
        app.put("/canciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Cancion actualizada = mapper.readValue(ctx.body(), Cancion.class);
            actualizada.setId(Integer.parseInt(id));

            for (int i = 0; i < CancionBD.canciones.size(); i++) {
                if (CancionBD.canciones.get(i).getId() == Integer.parseInt(id)) {
                    CancionBD.canciones.set(i, actualizada);
                    ctx.json(new mensaje("Canción actualizada", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Canción no encontrada", ""));
        });

        // Eliminar canción
        app.delete("/canciones/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");

            for (int i = 0; i < CancionBD.canciones.size(); i++) {
                if (CancionBD.canciones.get(i).getId() == Integer.parseInt(id)) {
                    CancionBD.canciones.remove(i);
                    ctx.json(new mensaje("Canción eliminada", ""));
                    return;
                }
            }

            ctx.status(404);
            ctx.json(new mensaje("Canción no encontrada", ""));
        });
    }
}
