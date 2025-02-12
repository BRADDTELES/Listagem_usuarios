package com.danilloteles.prototico_lista.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danilloteles.prototico_lista.adapter.UsuarioAdapter;
import com.danilloteles.prototico_lista.databinding.ActivityMainBinding;
import com.danilloteles.prototico_lista.model.Usuario;
import com.danilloteles.prototico_lista.viewmodel.UsuarioViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UsuarioViewModel viewModel;
    private UsuarioAdapter usuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioAdapter = new UsuarioAdapter();
        binding.rvUsuarios.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUsuarios.setAdapter(usuarioAdapter);

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
                        usuarioAdapter.setUsuarios(viewModel.getUsuarios());
                        break;
                    case 2:
                        binding.rvUsuarios.setVisibility(View.VISIBLE);
                        usuarioAdapter.setUsuarios(viewModel.getUsuariosOrdenadosAZ());
                        break;
                    case 3:
                        binding.rvUsuarios.setVisibility(View.VISIBLE);
                        usuarioAdapter.setUsuarios(viewModel.getUsuariosOrdenadosZA());
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
            } else {
                viewModel.adicionarUsuario(nome);
                usuarioAdapter.setUsuarios(viewModel.getUsuarios());
                binding.editTextNome.setText("");
                Toast.makeText(this, "Usuário adicionado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }
}