package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.ListaDeReproduccionBD;
import org.songlibrary.modelos.ListaDeReproduccion;
import org.songlibrary.modelos.mensaje;

import static spark.Spark.*;

public class FuncionesListaDeReproduccion {

    public static void FuncionesCRUD(ObjectMapper mapper) {

        // Crear lista
        post("/listas", (request, response) -> {
            response.type("application/json");
            ListaDeReproduccion lista = mapper.readValue(request.body(), ListaDeReproduccion.class);
            lista.setId(ListaDeReproduccionBD.autoId++);
            ListaDeReproduccionBD.listas.add(lista);
            return mapper.writeValueAsString(new mensaje("Lista de reproducción agregada", request.body()));
        });

        // Obtener todas las listas
        get("/listas", (request, response) -> {
            response.type("application/json");
            return mapper.writeValueAsString(ListaDeReproduccionBD.listas);
        });

        // Obtener lista por ID
        get("/listas/:id", (request, response) -> {
            response.type("application/json");
            try {
                int idInt = Integer.parseInt(request.params(":id"));
                for (ListaDeReproduccion l : ListaDeReproduccionBD.listas) {
                    if (l.getId() == idInt) {
                        return mapper.writeValueAsString(l);
                    }
                }
                response.status(404);
                return mapper.writeValueAsString(new mensaje("Lista no encontrada", ""));
            } catch (NumberFormatException e) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID inválido", ""));
            }
        });

        // Actualizar lista
        put("/listas/:id", (request, response) -> {
            response.type("application/json");
            try {
                int idInt = Integer.parseInt(request.params(":id"));
                ListaDeReproduccion actualizada = mapper.readValue(request.body(), ListaDeReproduccion.class);
                actualizada.setId(idInt);

                for (int i = 0; i < ListaDeReproduccionBD.listas.size(); i++) {
                    if (ListaDeReproduccionBD.listas.get(i).getId() == idInt) {
                        ListaDeReproduccionBD.listas.set(i, actualizada);
                        return mapper.writeValueAsString(new mensaje("Lista actualizada", ""));
                    }
                }

                response.status(404);
                return mapper.writeValueAsString(new mensaje("Lista no encontrada", ""));
            } catch (NumberFormatException e) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID inválido", ""));
            }
        });

        // Eliminar lista
        delete("/listas/:id", (request, response) -> {
            response.type("application/json");
            try {
                int idInt = Integer.parseInt(request.params(":id"));
                for (int i = 0; i < ListaDeReproduccionBD.listas.size(); i++) {
                    if (ListaDeReproduccionBD.listas.get(i).getId() == idInt) {
                        ListaDeReproduccionBD.listas.remove(i);
                        return mapper.writeValueAsString(new mensaje("Lista eliminada", ""));
                    }
                }

                response.status(404);
                return mapper.writeValueAsString(new mensaje("Lista no encontrada", ""));
            } catch (NumberFormatException e) {
                response.status(400);
                return mapper.writeValueAsString(new mensaje("ID inválido", ""));
            }
        });
    }
}
