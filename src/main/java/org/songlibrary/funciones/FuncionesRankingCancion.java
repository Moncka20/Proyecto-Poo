package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.RankingCancionBD;
import org.songlibrary.modelos.RankingCancion;
import org.songlibrary.modelos.mensaje;
import io.javalin.Javalin;

public class FuncionesRankingCancion {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/rankings", ctx -> {
            ctx.contentType("application/json");
            RankingCancion ranking = mapper.readValue(ctx.body(), RankingCancion.class);
            ranking.setId(RankingCancionBD.autoId++);
            RankingCancionBD.rankings.add(ranking);
            ctx.json(new mensaje("Ranking agregado", ctx.body()));
        });

        app.get("/rankings", ctx -> {
            ctx.contentType("application/json");
            ctx.json(RankingCancionBD.rankings);
        });

        app.get("/rankings/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            RankingCancion encontrado = null;
            for (RankingCancion r : RankingCancionBD.rankings) {
                if (r.getId() == Integer.parseInt(id)) {
                    encontrado = r;
                    break;
                }
            }
            if (encontrado != null) {
                ctx.json(encontrado);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Ranking no encontrado", ""));
            }
        });

        app.put("/rankings/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            RankingCancion actualizado = mapper.readValue(ctx.body(), RankingCancion.class);
            actualizado.setId(Integer.parseInt(id));
            for (int i = 0; i < RankingCancionBD.rankings.size(); i++) {
                if (RankingCancionBD.rankings.get(i).getId() == Integer.parseInt(id)) {
                    RankingCancionBD.rankings.set(i, actualizado);
                    ctx.json(new mensaje("Ranking actualizado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Ranking no encontrado", ""));
        });

        app.delete("/rankings/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < RankingCancionBD.rankings.size(); i++) {
                if (RankingCancionBD.rankings.get(i).getId() == Integer.parseInt(id)) {
                    RankingCancionBD.rankings.remove(i);
                    ctx.json(new mensaje("Ranking eliminado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Ranking no encontrado", ""));
        });
    }
}
