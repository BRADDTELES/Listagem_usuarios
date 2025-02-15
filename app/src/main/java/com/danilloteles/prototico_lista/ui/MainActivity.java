package com.danilloteles.prototico_lista.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danilloteles.prototico_lista.adapter.ClienteAdapter;
import com.danilloteles.prototico_lista.databinding.ActivityMainBinding;
import com.danilloteles.prototico_lista.model.Cliente;
import com.danilloteles.prototico_lista.viewmodel.ClienteViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements ClienteAdapter.OnClienteClickListener {

    private ActivityMainBinding binding;
    private ClienteViewModel viewModel;
    private ClienteAdapter clienteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

        clienteAdapter = new ClienteAdapter(this);
        binding.rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUsuarios.setAdapter(clienteAdapter);

        viewModel.getClientesLiveData().observe(this, clientes -> {
            clienteAdapter.setClientes(clientes);
        });

        String[] opcoesFiltragem = {"Selecione", "Ordem de Inserção", "Ordenar de A-Z", "Ordenar de Z-A"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoesFiltragem);
        binding.spinnerFiltragem.setAdapter(adapterSpinner);

        binding.spinnerFiltragem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        binding.rvUsuarios.setVisibility(View.GONE);
                        break;
                    case 1:
                        binding.rvUsuarios.setVisibility(View.VISIBLE);
                        clienteAdapter.setClientes(viewModel.getListaClientes());
                        break;
                    case 2:
                        binding.rvUsuarios.setVisibility(View.VISIBLE);
                        clienteAdapter.setClientes(viewModel.getClientesOrdenadosAZ());
                        break;
                    case 3:
                        binding.rvUsuarios.setVisibility(View.VISIBLE);
                        clienteAdapter.setClientes(viewModel.getClientesOrdenadosZA());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.btnSalvar.setOnClickListener( it -> {
            String nome = binding.editTextNome.getText().toString().trim();
            if (nome.isEmpty()) {
                binding.editTextNome.setError("Nome obrigatório");
                Toast.makeText(this, "Por favor, insira um nome", Toast.LENGTH_SHORT).show();
            } else if (viewModel.existeCliente(nome)) {
                binding.editTextNome.setError("Cliente já existe");
            } else {
                viewModel.adicionarCliente(nome);
                binding.editTextNome.setText("");
                Toast.makeText(this, "Cliente adicionado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditarCliente(Cliente cliente) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Usuário");

        final EditText input = new EditText(this);
        input.setText(cliente.getNome());
        builder.setView(input);

        builder.setPositiveButton("Salvar", (dialog, which) -> {
            String novoNome = input.getText().toString().trim();
            if (!novoNome.isEmpty()) {
                viewModel.editarCliente(cliente.getId(), novoNome);
                clienteAdapter.setClientes(viewModel.getClientesOrdenadosAZ());
                Toast.makeText(this, "Usuário editado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nome não pode estar vazio", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    @Override
    public void onRemoverCliente(Cliente cliente) {
        viewModel.removerCliente(cliente.getId());
        Toast.makeText(this, "Usuário removido com sucesso", Toast.LENGTH_SHORT).show();
    }
}