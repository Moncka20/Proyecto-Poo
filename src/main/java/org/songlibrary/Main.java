package org.songlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.songlibrary.funciones.*;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        port(4567);

        // Ruta de prueba
        get("/", (request, response) -> {
            response.type("application/json");
            return "{\"mensaje\": \"funcionando\"}";
        });

        // Cargar las funciones de cada modelo
        FuncionesCancion.FuncionesCRUD(mapper);
        FuncionesArtista.FuncionesCRUD(mapper);
        FuncionesDisco.FuncionesCRUD(mapper);
        FuncionesGenero.FuncionesCRUD(mapper);
        FuncionesPodcast.FuncionesCRUD(mapper);
        FuncionesProductor.FuncionesCRUD(mapper);
        FuncionesLetraCancion.FuncionesCRUD(mapper);
    }
}
