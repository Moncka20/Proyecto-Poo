package org.songlibrary.servicio;

import org.songlibrary.BD.PodcastBD;
import org.songlibrary.modelos.Podcast;

import java.util.ArrayList;
import java.util.List;

public class PodcastServicio {

    public void guardarPodcast(Podcast podcast) {
        podcast.setId(PodcastBD.autoId++);
        PodcastBD.podcasts.add(podcast);
    }

    public void eliminarPodcast(int id) {
        PodcastBD.podcasts.removeIf(p -> p.getId() == id);
    }

    public void actualizarPodcast(int id, Podcast podcastActualizado) {
        for (int i = 0; i < PodcastBD.podcasts.size(); i++) {
            if (PodcastBD.podcasts.get(i).getId() == id) {
                podcastActualizado.setId(id);
                PodcastBD.podcasts.set(i, podcastActualizado);
                return;
            }
        }
    }

    public Podcast obtenerPodcast(int id) {
        for (Podcast podcast : PodcastBD.podcasts) {
            if (podcast.getId() == id) {
                return podcast;
            }
        }
        return null;
    }

    public List<Podcast> obtenerPodcasts() {
        return new ArrayList<>(PodcastBD.podcasts);
    }
}