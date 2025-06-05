package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.ListaDeReproduccion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.ListaDeReproduccionServicio;
import java.util.List;

public class FuncionesListaDeReproduccion {
    private static final ListaDeReproduccionServicio servicio = new ListaDeReproduccionServicio();

    public static void configurar(Javalin app) {
        app.post("/listas", FuncionesListaDeReproduccion::agregar);
        app.get("/listas/{id}", FuncionesListaDeReproduccion::obtener);
        app.put("/listas/{id}", FuncionesListaDeReproduccion::actualizar);
        app.delete("/listas/{id}", FuncionesListaDeReproduccion::eliminar);
        app.get("/listas", FuncionesListaDeReproduccion::listar);
    }

    private static void agregar(Context ctx) {
        ListaDeReproduccion obj = ctx.bodyAsClass(ListaDeReproduccion.class);
        servicio.guardarLista(obj);
        ctx.status(201).json(new mensaje<>("Lista de reproducción agregada correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ListaDeReproduccion obj = servicio.obtenerLista(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Lista no encontrada", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ListaDeReproduccion objActualizado = ctx.bodyAsClass(ListaDeReproduccion.class);
        servicio.actualizarLista(id, objActualizado);
        ctx.json(new mensaje<>("Lista de reproducción actualizada correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarLista(id);
        ctx.json(new mensaje<>("Lista de reproducción eliminada correctamente", null));
    }

    private static void listar(Context ctx) {
        List<ListaDeReproduccion> lista = servicio.obtenerListas();
        ctx.json(lista);
    }
}