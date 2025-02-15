package com.danilloteles.prototico_lista.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilloteles.prototico_lista.data.ClienteRepository;
import com.danilloteles.prototico_lista.model.Cliente;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ClienteViewModel extends ViewModel {

    private ClienteRepository repository;
    private final MutableLiveData<List<Cliente>> clientesLiveData = new MutableLiveData<>();

    @Inject
    public ClienteViewModel(ClienteRepository repository) {
        this.repository = repository;
        clientesLiveData.setValue(repository.getListaClientes());
    }

    public void adicionarCliente(String nome) {
        repository.adicionarCliente(nome);
        clientesLiveData.setValue(repository.getListaClientes());
    }

    public void editarCliente(int id, String novoNome) {
        repository.editarCliente(id, novoNome);
        clientesLiveData.setValue(repository.getListaClientes());
    }

    public void removerCliente(int id) {
        repository.removerCliente(id);
        clientesLiveData.setValue(repository.getListaClientes());
    }

    public LiveData<List<Cliente>> getClientesLiveData() {
        return clientesLiveData;
    }

    public boolean existeCliente(String nome) {
        return repository.getListaClientes().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(nome));
    }

    public List<Cliente> getListaClientes() {
        return repository.getListaClientes();
    }

    public List<Cliente> getClientesOrdenadosAZ() {
        return repository.getClientesOrdenadosAZ();
    }

    public List<Cliente> getClientesOrdenadosZA() {
        return repository.getClientesOrdenadosZA();
    }
}
