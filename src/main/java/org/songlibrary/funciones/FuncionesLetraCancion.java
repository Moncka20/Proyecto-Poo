package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.LetraCancion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.LetraCancionServicio;
import java.util.List;

public class FuncionesLetraCancion {
    private static final LetraCancionServicio servicio = new LetraCancionServicio();

    public static void configurar(Javalin app) {
        app.post("/letras", FuncionesLetraCancion::agregar);
        app.get("/letras/{id}", FuncionesLetraCancion::obtener);
        app.put("/letras/{id}", FuncionesLetraCancion::actualizar);
        app.delete("/letras/{id}", FuncionesLetraCancion::eliminar);
        app.get("/letras", FuncionesLetraCancion::listar);
    }

    private static void agregar(Context ctx) {
        LetraCancion obj = ctx.bodyAsClass(LetraCancion.class);
        servicio.guardarLetraCancion(obj);
        ctx.status(201).json(new mensaje<>("Letra agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        LetraCancion obj = servicio.obtenerLetraCancion(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Letra no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        LetraCancion objActualizado = ctx.bodyAsClass(LetraCancion.class);
        servicio.actualizarLetraCancion(id, objActualizado);
        ctx.json(new mensaje<>("Letra actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarLetraCancion(id);
        ctx.json(new mensaje<>("Letra eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<LetraCancion> lista = servicio.obtenerLetrasCancion();
        ctx.json(lista);
    }
}