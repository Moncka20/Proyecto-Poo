package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Recomendacion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.RecomendacionServicio;
import java.util.List;

public class FuncionesRecomendacion {
    private static final RecomendacionServicio servicio = new RecomendacionServicio();

    public static void configurar(Javalin app) {
        app.post("/recomendaciones", FuncionesRecomendacion::agregar);
        app.get("/recomendaciones/{id}", FuncionesRecomendacion::obtener);
        app.put("/recomendaciones/{id}", FuncionesRecomendacion::actualizar);
        app.delete("/recomendaciones/{id}", FuncionesRecomendacion::eliminar);
        app.get("/recomendaciones", FuncionesRecomendacion::listar);
    }

    private static void agregar(Context ctx) {
        Recomendacion obj = ctx.bodyAsClass(Recomendacion.class);
        servicio.guardarRecomendacion(obj);
        ctx.status(201).json(new mensaje<>("Recomendación agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Recomendacion obj = servicio.obtenerRecomendacion(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Recomendación no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Recomendacion objActualizado = ctx.bodyAsClass(Recomendacion.class);
        servicio.actualizarRecomendacion(id, objActualizado);
        ctx.json(new mensaje<>("Recomendación actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarRecomendacion(id);
        ctx.json(new mensaje<>("Recomendación eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Recomendacion> lista = servicio.obtenerRecomendaciones();
        ctx.json(lista);
    }
}