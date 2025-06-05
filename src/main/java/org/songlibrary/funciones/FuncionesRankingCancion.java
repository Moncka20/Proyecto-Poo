package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.RankingCancion;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.RankingCancionServicio;
import java.util.List;

public class FuncionesRankingCancion {
    private static final RankingCancionServicio servicio = new RankingCancionServicio();

    public static void configurar(Javalin app) {
        app.post("/rankings", FuncionesRankingCancion::agregar);
        app.get("/rankings/{id}", FuncionesRankingCancion::obtener);
        app.put("/rankings/{id}", FuncionesRankingCancion::actualizar);
        app.delete("/rankings/{id}", FuncionesRankingCancion::eliminar);
        app.get("/rankings", FuncionesRankingCancion::listar);
    }

    private static void agregar(Context ctx) {
        RankingCancion obj = ctx.bodyAsClass(RankingCancion.class);
        servicio.guardarRanking(obj);
        ctx.status(201).json(new mensaje<>("Ranking agregado correctamente", obj));
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        RankingCancion obj = servicio.obtenerRanking(id);
        if (obj != null) ctx.json(obj);
        else ctx.status(404).json(new mensaje<>("Ranking no encontrado", null));
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        RankingCancion objActualizado = ctx.bodyAsClass(RankingCancion.class);
        servicio.actualizarRanking(id, objActualizado);
        ctx.json(new mensaje<>("Ranking actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarRanking(id);
        ctx.json(new mensaje<>("Ranking eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<RankingCancion> lista = servicio.obtenerRankings();
        ctx.json(lista);
    }
}