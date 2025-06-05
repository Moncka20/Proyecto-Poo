package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.EstadisticaCancion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.EstadisticaCancionServicio;
import java.util.List;

public class FuncionesEstadisticaCancion {
    private static final EstadisticaCancionServicio servicio = new EstadisticaCancionServicio();

    public static void configurar(Javalin app) {
        app.post("/estadisticas", FuncionesEstadisticaCancion::agregar);
        app.get("/estadisticas/{id}", FuncionesEstadisticaCancion::obtener);
        app.put("/estadisticas/{id}", FuncionesEstadisticaCancion::actualizar);
        app.delete("/estadisticas/{id}", FuncionesEstadisticaCancion::eliminar);
        app.get("/estadisticas", FuncionesEstadisticaCancion::listar);
    }

    private static void agregar(Context ctx) {
        EstadisticaCancion obj = ctx.bodyAsClass(EstadisticaCancion.class);
        servicio.guardarEstadistica(obj);
        ctx.status(201).json(new mensaje<>("Estadística agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        EstadisticaCancion obj = servicio.obtenerEstadistica(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Estadística no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        EstadisticaCancion objActualizado = ctx.bodyAsClass(EstadisticaCancion.class);
        servicio.actualizarEstadistica(id, objActualizado);
        ctx.json(new mensaje<>("Estadística actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarEstadistica(id);
        ctx.json(new mensaje<>("Estadística eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<EstadisticaCancion> lista = servicio.obtenerEstadisticas();
        ctx.json(lista);
    }
}