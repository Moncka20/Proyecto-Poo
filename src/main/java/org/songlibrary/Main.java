package org.songlibrary;

import io.javalin.Javalin;
import io.javalin.http.HttpResponseException;
import org.songlibrary.funciones.*;
import org.songlibrary.errores.ErrorApi;
import org.songlibrary.errores.RespuestaError;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
        });

        // Configuración de endpoints
        FuncionesDisco.configurar(app);
        FuncionesGenero.configurar(app);
        FuncionesPodcast.configurar(app);
        FuncionesCompositor.configurar(app);
        FuncionesUsuario.configurar(app);
        FuncionesCancion.configurar(app);
        FuncionesArtista.configurar(app);
        FuncionesProductor.configurar(app);
        FuncionesLetraCancion.configurar(app);
        FuncionesSelloDiscografico.configurar(app);
        FuncionesListaDeReproduccion.configurar(app);
        FuncionesListaFavoritos.configurar(app);
        FuncionesHistorialReproduccion.configurar(app);
        FuncionesRankingCancion.configurar(app);
        FuncionesSuscripcion.configurar(app);
        FuncionesEstadisticaCancion.configurar(app);
        FuncionesRecomendacion.configurar(app);
        FuncionesBusqueda.configurar(app);
        FuncionesListaBloqueo.configurar(app);
        FuncionesClasificacion.configurar(app);

        // Manejo global de errores personalizados
        app.exception(ErrorApi.class, (e, ctx) -> {
            ctx.status(e.getEstado()).json(new RespuestaError(e.getMessage()));
        });

        app.exception(HttpResponseException.class, (e, ctx) -> {
            ctx.status(e.getStatus()).json(new RespuestaError(e.getMessage()));
        });

        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500).json(new RespuestaError("Error interno: " + e.getMessage()));
        });

        app.error(404, ctx -> {
            ctx.json(new RespuestaError("Recurso no encontrado"));
        });


        app.before(ctx -> ctx.header("Content-Type", "application/json"));
        app.start(4567);
    }
}