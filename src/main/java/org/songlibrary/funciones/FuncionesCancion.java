package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Cancion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.CancionServicio;
import java.util.List;

public class FuncionesCancion {
    private static final CancionServicio servicio = new CancionServicio();

    public static void configurar(Javalin app) {
        app.post("/canciones", FuncionesCancion::agregar);
        app.get("/canciones/{id}", FuncionesCancion::obtener);
        app.put("/canciones/{id}", FuncionesCancion::actualizar);
        app.delete("/canciones/{id}", FuncionesCancion::eliminar);
        app.get("/canciones", FuncionesCancion::listar);
    }

    private static void agregar(Context ctx) {
        Cancion obj = ctx.bodyAsClass(Cancion.class);
        servicio.guardarCancion(obj);
        ctx.status(201).json(new mensaje<>("Cancion agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Cancion obj = servicio.obtenerCancion(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Cancion no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Cancion objActualizado = ctx.bodyAsClass(Cancion.class);
        servicio.actualizarCancion(id, objActualizado);
        ctx.json(new mensaje<>("Cancion actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarCancion(id);
        ctx.json(new mensaje<>("Cancion eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Cancion> lista = servicio.obtenerCanciones();
        ctx.json(lista);
    }
}