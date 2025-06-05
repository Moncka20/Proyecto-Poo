package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Artista;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.ArtistaServicio;
import java.util.List;

public class FuncionesArtista {
    private static final ArtistaServicio servicio = new ArtistaServicio();

    public static void configurar(Javalin app) {
        app.post("/artistas", FuncionesArtista::agregar);
        app.get("/artistas/{id}", FuncionesArtista::obtener);
        app.put("/artistas/{id}", FuncionesArtista::actualizar);
        app.delete("/artistas/{id}", FuncionesArtista::eliminar);
        app.get("/artistas", FuncionesArtista::listar);
    }

    private static void agregar(Context ctx) {
        Artista obj = ctx.bodyAsClass(Artista.class);
        servicio.guardarArtista(obj);
        ctx.status(201).json(new mensaje<>("Artista agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Artista obj = servicio.obtenerArtista(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Artista no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Artista objActualizado = ctx.bodyAsClass(Artista.class);
        servicio.actualizarArtista(id, objActualizado);
        ctx.json(new mensaje<>("Artista actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarArtista(id);
        ctx.json(new mensaje<>("Artista eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Artista> lista = servicio.obtenerArtista();
        ctx.json(lista);
    }
}