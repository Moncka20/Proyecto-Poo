package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.SelloDiscografico;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.SelloDiscograficoServicio;
import java.util.List;

public class FuncionesSelloDiscografico {
    private static final SelloDiscograficoServicio servicio = new SelloDiscograficoServicio();

    public static void configurar(Javalin app) {
        app.post("/sellos", FuncionesSelloDiscografico::agregar);
        app.get("/sellos/{id}", FuncionesSelloDiscografico::obtener);
        app.put("/sellos/{id}", FuncionesSelloDiscografico::actualizar);
        app.delete("/sellos/{id}", FuncionesSelloDiscografico::eliminar);
        app.get("/sellos", FuncionesSelloDiscografico::listar);
    }

    private static void agregar(Context ctx) {
        SelloDiscografico obj = ctx.bodyAsClass(SelloDiscografico.class);
        servicio.guardarSello(obj);
        ctx.status(201).json(new mensaje<>("Sello agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        SelloDiscografico obj = servicio.obtenerSello(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Sello no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        SelloDiscografico objActualizado = ctx.bodyAsClass(SelloDiscografico.class);
        servicio.actualizarSello(id, objActualizado);
        ctx.json(new mensaje<>("Sello actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarSello(id);
        ctx.json(new mensaje<>("Sello eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<SelloDiscografico> lista = servicio.obtenerSellos();
        ctx.json(lista);
    }
}