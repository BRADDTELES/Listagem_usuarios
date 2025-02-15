package com.danilloteles.prototico_lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilloteles.prototico_lista.R;
import com.danilloteles.prototico_lista.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    private List<Cliente> clientes;
    private final OnClienteClickListener listener;

    public ClienteAdapter(OnClienteClickListener listener) {
        this.clientes = new ArrayList<>();
        this.listener = listener;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente cliente = clientes.get(position);
        holder.bind(cliente, listener);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNomeUsuario;
        ImageButton btnEditar, btnRemover;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomeUsuario = itemView.findViewById(R.id.textViewNomeUsuario);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnRemover = itemView.findViewById(R.id.btnRemover);
        }

        public void bind(Cliente cliente, OnClienteClickListener listener) {

            textViewNomeUsuario.setText(cliente.getNome());

            btnEditar.setOnClickListener(v -> listener.onEditarCliente(cliente));
            btnRemover.setOnClickListener(v -> listener.onRemoverCliente(cliente));

        }
    }

    public interface OnClienteClickListener {
        void onEditarCliente(Cliente cliente);
        void onRemoverCliente(Cliente cliente);
    }
}
