package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Productor;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.ProductorServicio;
import java.util.List;

public class FuncionesProductor {
    private static final ProductorServicio servicio = new ProductorServicio();

    public static void configurar(Javalin app) {
        app.post("/productores", FuncionesProductor::agregar);
        app.get("/productores/{id}", FuncionesProductor::obtener);
        app.put("/productores/{id}", FuncionesProductor::actualizar);
        app.delete("/productores/{id}", FuncionesProductor::eliminar);
        app.get("/productores", FuncionesProductor::listar);
    }

    private static void agregar(Context ctx) {
        Productor obj = ctx.bodyAsClass(Productor.class);
        servicio.guardarProductor(obj);
        ctx.status(201).json(new mensaje<>("Productor agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Productor obj = servicio.obtenerProductor(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Productor no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Productor objActualizado = ctx.bodyAsClass(Productor.class);
        servicio.actualizarProductor(id, objActualizado);
        ctx.json(new mensaje<>("Productor actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarProductor(id);
        ctx.json(new mensaje<>("Productor eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Productor> lista = servicio.obtenerProductores();
        ctx.json(lista);
    }
}