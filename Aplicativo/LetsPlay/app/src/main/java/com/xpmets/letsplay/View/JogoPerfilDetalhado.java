package com.xpmets.letsplay.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xpmets.letsplay.Controller.PerfilHorariosAdapter;
import com.xpmets.letsplay.Model.Horario;
import com.xpmets.letsplay.Model.Perfil;
import com.xpmets.letsplay.R;

import java.util.List;

public class JogoPerfilDetalhado extends AppCompatActivity {

    private Perfil perfilJogo;
    private List<Horario> horarios;
    private RecyclerView recyclerHorarios;
    private PerfilHorariosAdapter adapter;
    private TextView nomeJogo, descricaoJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_perfil_detalhado);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        recyclerHorarios = findViewById(R.id.recycle_horarios);

        Intent intent = getIntent();
        perfilJogo = (Perfil) intent.getSerializableExtra("perfilJogo");

        nomeJogo = findViewById(R.id.nome_jogo);
        descricaoJogo = findViewById(R.id.descricao_jogo);

        nomeJogo.setText(perfilJogo.getNomeJogo());
        descricaoJogo.setText(perfilJogo.getDescricao());

        horarios = perfilJogo.getHorarios();
        setRecyclerPerfilJogos(horarios);

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setRecyclerPerfilJogos(List listaHorarios) {
        if (horarios.isEmpty()) {
        } else {
            LinearLayoutManager llm = new LinearLayoutManager(this);
            recyclerHorarios.setLayoutManager(llm);
            adapter = new PerfilHorariosAdapter(listaHorarios, this);
            recyclerHorarios.setAdapter(adapter);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
