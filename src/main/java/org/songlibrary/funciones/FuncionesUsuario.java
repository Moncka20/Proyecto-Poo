package org.songlibrary.funciones;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.songlibrary.BD.UsuarioBD;
import org.songlibrary.modelos.Usuario;
import org.songlibrary.modelos.mensaje;


import io.javalin.Javalin;

public class FuncionesUsuario {

    public static void FuncionesCRUD(Javalin app, ObjectMapper mapper) {

        app.post("/usuarios", ctx -> {
            ctx.contentType("application/json");
            Usuario usuario = mapper.readValue(ctx.body(), Usuario.class);
            usuario.setId(UsuarioBD.autoId++);
            UsuarioBD.usuarios.add(usuario);
            ctx.json(new mensaje("Usuario agregado", ctx.body()));
        });

        app.get("/usuarios", ctx -> {
            ctx.contentType("application/json");
            ctx.json(UsuarioBD.usuarios);
        });

        app.get("/usuarios/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            if (id == null) {
                ctx.status(400);
                ctx.json(new mensaje("ID no proporcionado", ""));
                return;
            }
            Usuario encontrado = null;
            for (Usuario u : UsuarioBD.usuarios) {
                if (u.getId() == Integer.parseInt(id)) {
                    encontrado = u;
                    break;
                }
            }
            if (encontrado != null) {
                ctx.json(encontrado);
            } else {
                ctx.status(404);
                ctx.json(new mensaje("Usuario no encontrado", ""));
            }
        });

        app.put("/usuarios/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            Usuario actualizado = mapper.readValue(ctx.body(), Usuario.class);
            actualizado.setId(Integer.parseInt(id));
            for (int i = 0; i < UsuarioBD.usuarios.size(); i++) {
                if (UsuarioBD.usuarios.get(i).getId() == Integer.parseInt(id)) {
                    UsuarioBD.usuarios.set(i, actualizado);
                    ctx.json(new mensaje("Usuario actualizado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Usuario no encontrado", ""));
        });

        app.delete("/usuarios/{id}", ctx -> {
            ctx.contentType("application/json");
            String id = ctx.pathParam("id");
            for (int i = 0; i < UsuarioBD.usuarios.size(); i++) {
                if (UsuarioBD.usuarios.get(i).getId() == Integer.parseInt(id)) {
                    UsuarioBD.usuarios.remove(i);
                    ctx.json(new mensaje("Usuario eliminado", ""));
                    return;
                }
            }
            ctx.status(404);
            ctx.json(new mensaje("Usuario no encontrado", ""));
        });
    }
}
