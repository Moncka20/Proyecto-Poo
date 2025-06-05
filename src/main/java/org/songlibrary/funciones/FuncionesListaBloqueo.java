package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.ListaBloqueo;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.ListaBloqueoServicio;
import java.util.List;

public class FuncionesListaBloqueo {
    private static final ListaBloqueoServicio servicio = new ListaBloqueoServicio();

    public static void configurar(Javalin app) {
        app.post("/bloqueos", FuncionesListaBloqueo::agregar);
        app.get("/bloqueos/{id}", FuncionesListaBloqueo::obtener);
        app.put("/bloqueos/{id}", FuncionesListaBloqueo::actualizar);
        app.delete("/bloqueos/{id}", FuncionesListaBloqueo::eliminar);
        app.get("/bloqueos", FuncionesListaBloqueo::listar);
    }

    private static void agregar(Context ctx) {
        ListaBloqueo obj = ctx.bodyAsClass(ListaBloqueo.class);
        servicio.guardarListaBloqueo(obj);
        ctx.status(201).json(new mensaje<>("Bloqueo agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ListaBloqueo obj = servicio.obtenerListaBloqueo(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Bloqueo no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ListaBloqueo objActualizado = ctx.bodyAsClass(ListaBloqueo.class);
        servicio.actualizarListaBloqueo(id, objActualizado);
        ctx.json(new mensaje<>("Bloqueo actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarListaBloqueo(id);
        ctx.json(new mensaje<>("Bloqueo eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<ListaBloqueo> lista = servicio.obtenerListasBloqueo();
        ctx.json(lista);
    }
}