package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Busqueda;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.BusquedaServicio;
import java.util.List;

public class FuncionesBusqueda {
    private static final BusquedaServicio servicio = new BusquedaServicio();

    public static void configurar(Javalin app) {
        app.post("/busquedas", FuncionesBusqueda::agregar);
        app.get("/busquedas/{id}", FuncionesBusqueda::obtener);
        app.put("/busquedas/{id}", FuncionesBusqueda::actualizar);
        app.delete("/busquedas/{id}", FuncionesBusqueda::eliminar);
        app.get("/busquedas", FuncionesBusqueda::listar);
    }

    private static void agregar(Context ctx) {
        Busqueda obj = ctx.bodyAsClass(Busqueda.class);
        servicio.guardarBusqueda(obj);
        ctx.status(201).json(new mensaje<>("Busqueda agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Busqueda obj = servicio.obtenerBusqueda(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Busqueda no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Busqueda objActualizado = ctx.bodyAsClass(Busqueda.class);
        servicio.actualizarBusqueda(id, objActualizado);
        ctx.json(new mensaje<>("Busqueda actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarBusqueda(id);
        ctx.json(new mensaje<>("Busqueda eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Busqueda> lista = servicio.obtenerBusquedas();
        ctx.json(lista);
    }
}