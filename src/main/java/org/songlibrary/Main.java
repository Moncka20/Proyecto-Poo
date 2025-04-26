package org.songlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.songlibrary.funciones.FuncionesCancion;
import org.songlibrary.funciones.FuncionesArtista;

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
        FuncionesCancion.configurarFunciones(mapper);
        FuncionesArtista.configurarFunciones(mapper);
    }
}
