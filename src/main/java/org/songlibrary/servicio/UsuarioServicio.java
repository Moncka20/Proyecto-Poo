package org.songlibrary.servicio;

import org.songlibrary.BD.UsuarioBD;
import org.songlibrary.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioServicio {

    public void guardarUsuario(Usuario usuario) {
        usuario.setId(UsuarioBD.autoId++);
        UsuarioBD.usuarios.add(usuario);
    }

    public void eliminarUsuario(int id) {
        UsuarioBD.usuarios.removeIf(u -> u.getId() == id);
    }

    public void actualizarUsuario(int id, Usuario usuarioActualizado) {
        for (int i = 0; i < UsuarioBD.usuarios.size(); i++) {
            if (UsuarioBD.usuarios.get(i).getId() == id) {
                usuarioActualizado.setId(id);
                UsuarioBD.usuarios.set(i, usuarioActualizado);
                return;
            }
        }
    }

    public Usuario obtenerUsuario(int id) {
        for (Usuario usuario : UsuarioBD.usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(UsuarioBD.usuarios);
    }
}