package com.danilloteles.prototico_lista.data;

import com.danilloteles.prototico_lista.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> listaClientes = new ArrayList<>();
    private int proximoId = 1;

    public void adicionarCliente(String nome) {
        Cliente cliente = new Cliente(proximoId++, nome);
        listaClientes.add(cliente);
    }

    public void editarCliente(int id, String novoNome) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
                cliente.setNome(novoNome);
                break;
            }
        }
    }

    public void removerCliente(int id) {
        listaClientes.removeIf(cliente -> cliente.getId() == id);
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Cliente> getClientesOrdenadosAZ() {
        List<Cliente> lista = new ArrayList<>(listaClientes);
        lista.sort((cliente1, cliente2) -> cliente1.getNome().compareToIgnoreCase(cliente2.getNome()));
        return lista;
    }

    public List<Cliente> getClientesOrdenadosZA() {
        List<Cliente> lista = new ArrayList<>(listaClientes);
        lista.sort((cliente1, cliente2) -> cliente2.getNome().compareToIgnoreCase(cliente1.getNome()));
        return lista;
    }
}
