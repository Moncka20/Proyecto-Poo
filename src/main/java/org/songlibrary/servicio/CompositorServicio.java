package org.songlibrary.servicio;

import org.songlibrary.BD.CompositorBD;
import org.songlibrary.modelos.Compositor;

import java.util.ArrayList;
import java.util.List;

public class CompositorServicio {

    public void guardarCompositor(Compositor compositor) {
        compositor.setId(CompositorBD.autoId++);
        CompositorBD.compositores.add(compositor);
    }

    public void eliminarCompositor(int id) {
        CompositorBD.compositores.removeIf(c -> c.getId() == id);
    }

    public void actualizarCompositor(int id, Compositor compositorActualizado) {
        for (int i = 0; i < CompositorBD.compositores.size(); i++) {
            if (CompositorBD.compositores.get(i).getId() == id) {
                compositorActualizado.setId(id);
                CompositorBD.compositores.set(i, compositorActualizado);
                return;
            }
        }
    }

    public Compositor obtenerCompositor(int id) {
        for (Compositor compositor : CompositorBD.compositores) {
            if (compositor.getId() == id) {
                return compositor;
            }
        }
        return null;
    }

    public List<Compositor> obtenerCompositores() {
        return new ArrayList<>(CompositorBD.compositores);
    }
}