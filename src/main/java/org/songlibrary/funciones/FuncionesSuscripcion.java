package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Suscripcion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.SuscripcionServicio;
import java.util.List;

public class FuncionesSuscripcion {
    private static final SuscripcionServicio servicio = new SuscripcionServicio();

    public static void configurar(Javalin app) {
        app.post("/suscripciones", FuncionesSuscripcion::agregar);
        app.get("/suscripciones/{id}", FuncionesSuscripcion::obtener);
        app.put("/suscripciones/{id}", FuncionesSuscripcion::actualizar);
        app.delete("/suscripciones/{id}", FuncionesSuscripcion::eliminar);
        app.get("/suscripciones", FuncionesSuscripcion::listar);
    }

    private static void agregar(Context ctx) {
        Suscripcion obj = ctx.bodyAsClass(Suscripcion.class);
        servicio.guardarSuscripcion(obj);
        ctx.status(201).json(new mensaje<>("Suscripción agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Suscripcion obj = servicio.obtenerSuscripcion(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Suscripción no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Suscripcion objActualizado = ctx.bodyAsClass(Suscripcion.class);
        servicio.actualizarSuscripcion(id, objActualizado);
        ctx.json(new mensaje<>("Suscripción actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarSuscripcion(id);
        ctx.json(new mensaje<>("Suscripción eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Suscripcion> lista = servicio.obtenerSuscripciones();
        ctx.json(lista);
    }
}