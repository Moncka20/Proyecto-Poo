package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.ListaFavoritos;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.ListaFavoritosServicio;
import java.util.List;

public class FuncionesListaFavoritos {
    private static final ListaFavoritosServicio servicio = new ListaFavoritosServicio();

    public static void configurar(Javalin app) {
        app.post("/favoritos", FuncionesListaFavoritos::agregar);
        app.get("/favoritos/{id}", FuncionesListaFavoritos::obtener);
        app.put("/favoritos/{id}", FuncionesListaFavoritos::actualizar);
        app.delete("/favoritos/{id}", FuncionesListaFavoritos::eliminar);
        app.get("/favoritos", FuncionesListaFavoritos::listar);
    }

    private static void agregar(Context ctx) {
        ListaFavoritos obj = ctx.bodyAsClass(ListaFavoritos.class);
        servicio.guardarFavorito(obj);
        ctx.status(201).json(new mensaje<>("Favorito agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ListaFavoritos obj = servicio.obtenerFavorito(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Favorito no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ListaFavoritos objActualizado = ctx.bodyAsClass(ListaFavoritos.class);
        servicio.actualizarFavorito(id, objActualizado);
        ctx.json(new mensaje<>("Favorito actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarFavorito(id);
        ctx.json(new mensaje<>("Favorito eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<ListaFavoritos> lista = servicio.obtenerFavoritos();
        ctx.json(lista);
    }
}