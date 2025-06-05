package org.songlibrary.servicio;

import org.songlibrary.BD.ProductorBD;
import org.songlibrary.modelos.Productor;

import java.util.ArrayList;
import java.util.List;

public class ProductorServicio {

    public void guardarProductor(Productor productor) {
        productor.setId(ProductorBD.autoId++);
        ProductorBD.productores.add(productor);
    }

    public void eliminarProductor(int id) {
        ProductorBD.productores.removeIf(p -> p.getId() == id);
    }

    public void actualizarProductor(int id, Productor productorActualizado) {
        for (int i = 0; i < ProductorBD.productores.size(); i++) {
            if (ProductorBD.productores.get(i).getId() == id) {
                productorActualizado.setId(id);
                ProductorBD.productores.set(i, productorActualizado);
                return;
            }
        }
    }

    public Productor obtenerProductor(int id) {
        for (Productor productor : ProductorBD.productores) {
            if (productor.getId() == id) {
                return productor;
            }
        }
        return null;
    }

    public List<Productor> obtenerProductores() {
        return new ArrayList<>(ProductorBD.productores);
    }
}
