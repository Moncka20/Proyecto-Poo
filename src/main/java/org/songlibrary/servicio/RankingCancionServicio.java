package org.songlibrary.servicio;

import org.songlibrary.BD.RankingCancionBD;
import org.songlibrary.modelos.RankingCancion;

import java.util.ArrayList;
import java.util.List;

public class RankingCancionServicio {

    public void guardarRanking(RankingCancion ranking) {
        ranking.setId(RankingCancionBD.autoId++);
        RankingCancionBD.rankings.add(ranking);
    }

    public void eliminarRanking(int id) {
        RankingCancionBD.rankings.removeIf(r -> r.getId() == id);
    }

    public void actualizarRanking(int id, RankingCancion rankingActualizado) {
        for (int i = 0; i < RankingCancionBD.rankings.size(); i++) {
            if (RankingCancionBD.rankings.get(i).getId() == id) {
                rankingActualizado.setId(id);
                RankingCancionBD.rankings.set(i, rankingActualizado);
                return;
            }
        }
    }

    public RankingCancion obtenerRanking(int id) {
        for (RankingCancion ranking : RankingCancionBD.rankings) {
            if (ranking.getId() == id) {
                return ranking;
            }
        }
        return null;
    }

    public List<RankingCancion> obtenerRankings() {
        return new ArrayList<>(RankingCancionBD.rankings);
    }
}