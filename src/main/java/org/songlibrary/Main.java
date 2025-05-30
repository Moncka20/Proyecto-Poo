package org.songlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.songlibrary.funciones.*;

import io.javalin.Javalin;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Javalin app = Javalin.create().start(4567);
        // Ruta de prueba
        app.get("/", ctx -> {
            ctx.json(Map.of("mensaje", "funcionando"));
        });

        // Cargar las funciones de cada modelo
        FuncionesCancion.FuncionesCRUD(app,mapper);
        FuncionesArtista.FuncionesCRUD(app,mapper);
        FuncionesDisco.FuncionesCRUD(app,mapper);
        FuncionesGenero.FuncionesCRUD(app,mapper);
        FuncionesPodcast.FuncionesCRUD(app,mapper);
        FuncionesProductor.FuncionesCRUD(app,mapper);
        FuncionesLetraCancion.FuncionesCRUD(app,mapper);
        FuncionesCompositor.FuncionesCRUD(app,mapper);
        FuncionesSelloDiscografico.FuncionesCRUD(app,mapper);
        FuncionesListaDeReproduccion.FuncionesCRUD(app,mapper);
        FuncionesUsuario.FuncionesCRUD(app,mapper);
        FuncionesHistorialReproduccion.FuncionesCRUD(app,mapper);
        FuncionesRankingCancion.FuncionesCRUD(app,mapper);
        FuncionesSuscripcion.FuncionesCRUD(app,mapper);
        FuncionesEstadisticaCancion.FuncionesCRUD(app,mapper);
        FuncionesRecomendacion.FuncionesCRUD(app,mapper);
        FuncionesBusqueda.FuncionesCRUD(app,mapper);
        FuncionesListaBloqueo.FuncionesCRUD(app,mapper);
        FuncionesClasificacion.FuncionesCRUD(app,mapper);
    }
}
