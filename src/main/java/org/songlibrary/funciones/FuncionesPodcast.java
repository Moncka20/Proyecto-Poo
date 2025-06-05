package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Podcast;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.PodcastServicio;

import java.util.List;

public class FuncionesPodcast {

    private static final PodcastServicio servicio = new PodcastServicio();

    public static void configurar(Javalin app) {
        app.post("/podcasts", FuncionesPodcast::agregar);
        app.get("/podcasts/{id}", FuncionesPodcast::obtener);
        app.put("/podcasts/{id}", FuncionesPodcast::actualizar);
        app.delete("/podcasts/{id}", FuncionesPodcast::eliminar);
        app.get("/podcasts", FuncionesPodcast::listar);
    }

    private static void agregar(Context ctx) {
        Podcast obj = ctx.bodyAsClass(Podcast.class);
        servicio.guardarPodcast(obj);
        mensaje<Podcast> respuesta = new mensaje<>("Podcast agregado correctamente", obj);
        ctx.status(201).json(respuesta);
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Podcast obj = servicio.obtenerPodcast(id);
        if (obj != null) {
            ctx.json(obj);
        } else {
            ctx.status(404).json(new mensaje<>("Podcast no encontrado", null));
        }
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Podcast objActualizado = ctx.bodyAsClass(Podcast.class);
        servicio.actualizarPodcast(id, objActualizado);
        ctx.json(new mensaje<>("Podcast actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarPodcast(id);
        ctx.json(new mensaje<>("Podcast eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Podcast> lista = servicio.obtenerPodcasts();
        ctx.json(lista);
    }
}