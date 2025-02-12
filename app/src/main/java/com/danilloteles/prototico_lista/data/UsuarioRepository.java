package com.danilloteles.prototico_lista.data;

import com.danilloteles.prototico_lista.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public List<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    public List<Usuario> getUsuariosOrdenadosAZ() {
        List<Usuario> copiaLista = new ArrayList<>(listaUsuarios);
        copiaLista.sort((usuario1, usuario2) -> usuario1.getNome().compareToIgnoreCase(usuario2.getNome()));
        return copiaLista;
    }

    public List<Usuario> getUsuariosOrdenadosZA() {
        List<Usuario> copiaLista = new ArrayList<>(listaUsuarios);
        copiaLista.sort((usuario1, usuario2) -> usuario2.getNome().compareToIgnoreCase(usuario1.getNome()));
        return copiaLista;
    }
}
