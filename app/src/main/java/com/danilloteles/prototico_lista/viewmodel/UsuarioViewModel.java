package com.danilloteles.prototico_lista.viewmodel;

import androidx.lifecycle.ViewModel;

import com.danilloteles.prototico_lista.data.UsuarioRepository;
import com.danilloteles.prototico_lista.model.Usuario;

import java.util.List;

public class UsuarioViewModel extends ViewModel {
    private UsuarioRepository repository = new UsuarioRepository();

    public void adicionarUsuario(String nome) {
        repository.adicionarUsuario(new Usuario(nome));
    }

    public List<Usuario> getUsuarios() {
        return repository.getUsuarios();
    }

    public List<Usuario> getUsuariosOrdenadosAZ() {
        return repository.getUsuariosOrdenadosAZ();
    }

    public List<Usuario> getUsuariosOrdenadosZA() {
        return repository.getUsuariosOrdenadosZA();
    }
}
