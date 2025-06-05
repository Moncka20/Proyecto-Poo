package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Compositor;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.CompositorServicio;

import java.util.List;

public class FuncionesCompositor {

    private static final CompositorServicio servicio = new CompositorServicio();

    public static void configurar(Javalin app) {
        app.post("/compositores", FuncionesCompositor::agregar);
        app.get("/compositores/{id}", FuncionesCompositor::obtener);
        app.put("/compositores/{id}", FuncionesCompositor::actualizar);
        app.delete("/compositores/{id}", FuncionesCompositor::eliminar);
        app.get("/compositores", FuncionesCompositor::listar);
    }

    private static void agregar(Context ctx) {
        Compositor obj = ctx.bodyAsClass(Compositor.class);
        servicio.guardarCompositor(obj);
        mensaje<Compositor> respuesta = new mensaje<>("Compositor agregado correctamente", obj);
        ctx.status(201).json(respuesta);
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Compositor obj = servicio.obtenerCompositor(id);
        if (obj != null) {
            ctx.json(obj);
        } else {
            ctx.status(404).json(new mensaje<>("Compositor no encontrado", null));
        }
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Compositor objActualizado = ctx.bodyAsClass(Compositor.class);
        servicio.actualizarCompositor(id, objActualizado);
        ctx.json(new mensaje<>("Compositor actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarCompositor(id);
        ctx.json(new mensaje<>("Compositor eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Compositor> lista = servicio.obtenerCompositores();
        ctx.json(lista);
    }
}