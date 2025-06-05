package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Genero;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.GeneroServicio;

import java.util.List;

public class FuncionesGenero {

    private static final GeneroServicio servicio = new GeneroServicio();

    public static void configurar(Javalin app) {
        app.post("/generos", FuncionesGenero::agregar);
        app.get("/generos/{id}", FuncionesGenero::obtener);
        app.put("/generos/{id}", FuncionesGenero::actualizar);
        app.delete("/generos/{id}", FuncionesGenero::eliminar);
        app.get("/generos", FuncionesGenero::listar);
    }

    private static void agregar(Context ctx) {
        Genero obj = ctx.bodyAsClass(Genero.class);
        servicio.guardarGenero(obj);
        mensaje<Genero> respuesta = new mensaje<>("Genero agregado correctamente", obj);
        ctx.status(201).json(respuesta);
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Genero obj = servicio.obtenerGenero(id);
        if (obj != null) {
            ctx.json(obj);
        } else {
            ctx.status(404).json(new mensaje<>("Genero no encontrado", null));
        }
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Genero objActualizado = ctx.bodyAsClass(Genero.class);
        servicio.actualizarGenero(id, objActualizado);
        ctx.json(new mensaje<>("Genero actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarGenero(id);
        ctx.json(new mensaje<>("Genero eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Genero> lista = servicio.obtenerGeneros();
        ctx.json(lista);
    }
}