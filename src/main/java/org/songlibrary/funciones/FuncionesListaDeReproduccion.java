package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ListaDeReproduccionBD;
import org.songlibrary.modelos.ListaDeReproduccion;
import org.songlibrary.modelos.mensaje;

import io.javalin.Javalin;

public class FuncionesListaDeReproduccion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        // Crear una lista de reproducción
        app.post("/listas", ctx -> {
            ctx.contentType("application/json");
            ListaDeReproduccion lista = mapper.readValue(ctx.body(), ListaDeReproduccion.class);
            lista.setId(ListaDeReproduccionBD.autoId++);
            ListaDeReproduccionBD.listas.add(lista);
            ctx.json(new mensaje("Lista de reproducción agregada", ctx.body()));
        });

        // Obtener todas las listas de reproducción
        app.get("/listas", ctx -> {
            ctx.contentType("application/json");
            ctx.json(ListaDeReproduccionBD.listas);
        });

        // Obtener lista de reproducción por ID
        app.get("/listas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            ListaDeReproduccion encontrada = null;
            for (ListaDeReproduccion l : ListaDeReproduccionBD.listas) {
                if (l.getId() == Integer.parseInt(id)) {
                    encontrada = l;
                    break;
                }
            }
            if (encontrada != null) {
                ctx.json(encontrada);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Lista de reproducción no encontrada", ""));
            }
        });

        // Actualizar lista de reproducción
        app.put("/listas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            ListaDeReproduccion actualizada = mapper.readValue(ctx.body(), ListaDeReproduccion.class);
            actualizada.setId(Integer.parseInt(id));
            for (int i = 0; i < ListaDeReproduccionBD.listas.size(); i++) {
                if (ListaDeReproduccionBD.listas.get(i).getId() == Integer.parseInt(id)) {
                    ListaDeReproduccionBD.listas.set(i, actualizada);
                    ctx.json(new mensaje("Lista de reproducción actualizada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Lista de reproducción no encontrada", ""));
        });

        // Eliminar lista de reproducción
        app.delete("/listas/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < ListaDeReproduccionBD.listas.size(); i++) {
                if (ListaDeReproduccionBD.listas.get(i).getId() == Integer.parseInt(id)) {
                    ListaDeReproduccionBD.listas.remove(i);
                    ctx.json(new mensaje("Lista de reproducción eliminada", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Lista de reproducción no encontrada", ""));
        });
    }
}
