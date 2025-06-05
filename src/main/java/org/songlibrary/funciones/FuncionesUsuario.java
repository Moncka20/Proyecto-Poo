package org.songlibrary.funciones;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.songlibrary.modelos.Usuario;
import org.songlibrary.modelos.mensaje;
import org.songlibrary.servicio.UsuarioServicio;

import java.util.List;

public class FuncionesUsuario {

    private static final UsuarioServicio servicio = new UsuarioServicio();

    public static void configurar(Javalin app) {
        app.post("/usuarios", FuncionesUsuario::agregar);
        app.get("/usuarios/{id}", FuncionesUsuario::obtener);
        app.put("/usuarios/{id}", FuncionesUsuario::actualizar);
        app.delete("/usuarios/{id}", FuncionesUsuario::eliminar);
        app.get("/usuarios", FuncionesUsuario::listar);
    }

    private static void agregar(Context ctx) {
        Usuario obj = ctx.bodyAsClass(Usuario.class);
        servicio.guardarUsuario(obj);
        mensaje<Usuario> respuesta = new mensaje<>("Usuario agregado correctamente", obj);
        ctx.status(201).json(respuesta);
    }

    private static void obtener(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Usuario obj = servicio.obtenerUsuario(id);
        if (obj != null) {
            ctx.json(obj);
        } else {
            ctx.status(404).json(new mensaje<>("Usuario no encontrado", null));
        }
    }

    private static void actualizar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Usuario objActualizado = ctx.bodyAsClass(Usuario.class);
        servicio.actualizarUsuario(id, objActualizado);
        ctx.json(new mensaje<>("Usuario actualizado correctamente", objActualizado));
    }

    private static void eliminar(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        servicio.eliminarUsuario(id);
        ctx.json(new mensaje<>("Usuario eliminado correctamente", null));
    }

    private static void listar(Context ctx) {
        List<Usuario> lista = servicio.obtenerUsuarios();
        ctx.json(lista);
    }
}