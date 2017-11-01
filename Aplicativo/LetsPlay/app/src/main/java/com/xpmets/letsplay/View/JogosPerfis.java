package com.xpmets.letsplay.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xpmets.letsplay.Controller.PerfilJogosAdapter;
import com.xpmets.letsplay.Model.Perfil;
import com.xpmets.letsplay.R;

import java.util.ArrayList;
import java.util.List;

public class JogosPerfis extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    private View view;
    private List<Perfil> perfilJogos = new ArrayList<>();
    private TextView perfilVazio;
    private RecyclerView recyclerPerfilJogos;
    private PerfilJogosAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_jogos_perfil, null);
        recyclerPerfilJogos = view.findViewById(R.id.recycle_view_perfis);
        setRecyclerPerfilJogos(perfilJogos);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AdicionarJogoPerfil.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setRecyclerPerfilJogos(List listaJogos) {
        perfilVazio = view.findViewById(R.id.perfil_vazio);
        if (perfilJogos.isEmpty()) {
            perfilVazio.setText(R.string.perfil_vazio);
        } else {
            perfilVazio.setText(null);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerPerfilJogos.setLayoutManager(llm);
            adapter = new PerfilJogosAdapter(listaJogos);
            recyclerPerfilJogos.setAdapter(adapter);
        }
    }
}
