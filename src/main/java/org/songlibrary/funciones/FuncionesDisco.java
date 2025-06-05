package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Disco;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.DiscoServicio;

import java.util.List;

public class FuncionesDisco {

    private static final DiscoServicio servicio = new DiscoServicio();

    public static void configurar(Javalin app) {
        app.post("/discos", FuncionesDisco::agregar);
        app.get("/discos/{id}", FuncionesDisco::obtener);
        app.put("/discos/{id}", FuncionesDisco::actualizar);
        app.delete("/discos/{id}", FuncionesDisco::eliminar);
        app.get("/discos", FuncionesDisco::listar);
    }

    private static void agregar(Context ctx) {
        Disco obj = ctx.bodyAsClass(Disco.class);
        servicio.guardarDisco(obj);
        mensaje<Disco> respuesta = new mensaje<>("Disco agregado correctamente", obj);
        ctx.status(201).json(respuesta);
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Disco obj = servicio.obtenerDisco(id);
        if (obj != null) {
            ctx.json(obj);
        } else {
            ctx.status(404).json(new mensaje<>("Disco no encontrado", null));
        }
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Disco objActualizado = ctx.bodyAsClass(Disco.class);
        servicio.actualizarDisco(id, objActualizado);
        ctx.json(new mensaje<>("Disco actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarDisco(id);
        ctx.json(new mensaje<>("Disco eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Disco> lista = servicio.obtenerDiscos();
        ctx.json(lista);
    }
}
