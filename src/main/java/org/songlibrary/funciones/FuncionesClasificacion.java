package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Clasificacion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.ClasificacionServicio;
import java.util.List;

public class FuncionesClasificacion {
    private static final ClasificacionServicio servicio = new ClasificacionServicio();

    public static void configurar(Javalin app) {
        app.post("/clasificaciones", FuncionesClasificacion::agregar);
        app.get("/clasificaciones/{id}", FuncionesClasificacion::obtener);
        app.put("/clasificaciones/{id}", FuncionesClasificacion::actualizar);
        app.delete("/clasificaciones/{id}", FuncionesClasificacion::eliminar);
        app.get("/clasificaciones", FuncionesClasificacion::listar);
    }

    private static void agregar(Context ctx) {
        Clasificacion obj = ctx.bodyAsClass(Clasificacion.class);
        servicio.guardarClasificacion(obj);
        ctx.status(201).json(new mensaje<>("Clasificación agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Clasificacion obj = servicio.obtenerClasificacion(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Clasificación no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Clasificacion objActualizado = ctx.bodyAsClass(Clasificacion.class);
        servicio.actualizarClasificacion(id, objActualizado);
        ctx.json(new mensaje<>("Clasificación actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarClasificacion(id);
        ctx.json(new mensaje<>("Clasificación eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Clasificacion> lista = servicio.obtenerClasificaciones();
        ctx.json(lista);
    }
}