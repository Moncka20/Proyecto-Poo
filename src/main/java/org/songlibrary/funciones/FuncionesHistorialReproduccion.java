package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.HistorialReproduccion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.HistorialReproduccionServicio;
import java.util.List;

public class FuncionesHistorialReproduccion {
    private static final HistorialReproduccionServicio servicio = new HistorialReproduccionServicio();

    public static void configurar(Javalin app) {
        app.post("/historiales", FuncionesHistorialReproduccion::agregar);
        app.get("/historiales/{id}", FuncionesHistorialReproduccion::obtener);
        app.put("/historiales/{id}", FuncionesHistorialReproduccion::actualizar);
        app.delete("/historiales/{id}", FuncionesHistorialReproduccion::eliminar);
        app.get("/historiales", FuncionesHistorialReproduccion::listar);
    }

    private static void agregar(Context ctx) {
        HistorialReproduccion obj = ctx.bodyAsClass(HistorialReproduccion.class);
        servicio.guardarHistorial(obj);
        ctx.status(201).json(new mensaje<>("Historial agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        HistorialReproduccion obj = servicio.obtenerHistorial(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Historial no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        HistorialReproduccion objActualizado = ctx.bodyAsClass(HistorialReproduccion.class);
        servicio.actualizarHistorial(id, objActualizado);
        ctx.json(new mensaje<>("Historial actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarHistorial(id);
        ctx.json(new mensaje<>("Historial eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<HistorialReproduccion> lista = servicio.obtenerHistoriales();
        ctx.json(lista);
    }
}