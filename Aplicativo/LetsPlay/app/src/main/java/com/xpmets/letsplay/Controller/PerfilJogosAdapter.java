package com.xpmets.letsplay.Controller;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xpmets.letsplay.Model.Horario;
import com.xpmets.letsplay.Model.Perfil;
import com.xpmets.letsplay.R;
import com.xpmets.letsplay.View.JogoPerfilDetalhado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PerfilJogosAdapter extends RecyclerView.Adapter<PerfilJogosAdapter.PerfilViewHolder> implements Serializable {

    List<Perfil> perfilJogos;
    static private List<Perfil> perfilJogosG = new ArrayList<>();
    private Context context;

    public PerfilJogosAdapter(List<Perfil> jogosPerfil, Context context) {
        this.context = context;
        this.perfilJogos = new ArrayList<>();
        this.perfilJogos = jogosPerfil;
        perfilJogosG = jogosPerfil;
    }

    //inicializa a viewHolder (cardView)
    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jogo_perfil, parent, false);
        PerfilViewHolder vacinaView = new PerfilViewHolder(v);
        return vacinaView;
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nome_jogo;
        Button segunda, terca, quarta, quinta, sexta, sabado, domingo;

        PerfilViewHolder(final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            nome_jogo = itemView.findViewById(R.id.nome_jogo);
            segunda = itemView.findViewById(R.id.segunda);
            terca = itemView.findViewById(R.id.terca);
            quarta = itemView.findViewById(R.id.quarta);
            quinta = itemView.findViewById(R.id.quinta);
            sexta = itemView.findViewById(R.id.sexta);
            sabado = itemView.findViewById(R.id.sabado);
            domingo = itemView.findViewById(R.id.domingo);

            //card abre a informação do perfil do jogo
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it;
                    it = new Intent(itemView.getContext(), JogoPerfilDetalhado.class);
                    it.putExtra("perfilJogo", perfilJogosG.get(getAdapterPosition()));

                    itemView.getContext().startActivity(it);
                }
            });
        }
    }

    //adiciona valores aos itens da view
    @Override
    public void onBindViewHolder(PerfilViewHolder holder, int position) {
        holder.nome_jogo.setText(perfilJogos.get(position).getNomeJogo());
        for (int i = 0; i < perfilJogos.get(position).getHorarios().size(); i++) {
            Horario horario = perfilJogos.get(position).getHorarios().get(i);
            if (horario.getDia().equals("Seg")) {
                holder.segunda.setBackgroundResource(R.drawable.round_button);
                holder.segunda.setTextAppearance(context, R.style.DiasFontOn);
            } else if (horario.getDia().equals("Ter")) {
                holder.terca.setBackgroundResource(R.drawable.round_button);
                holder.terca.setTextAppearance(context, R.style.DiasFontOn);
            } else if (horario.getDia().equals("Qua")) {
                holder.quarta.setBackgroundResource(R.drawable.round_button);
                holder.quarta.setTextAppearance(context, R.style.DiasFontOn);
            } else if (horario.getDia().equals("Qui")) {
                holder.quinta.setBackgroundResource(R.drawable.round_button);
                holder.quinta.setTextAppearance(context, R.style.DiasFontOn);
            } else if (horario.getDia().equals("Sex")) {
                holder.sexta.setBackgroundResource(R.drawable.round_button);
                holder.sexta.setTextAppearance(context, R.style.DiasFontOn);
            } else if (horario.getDia().equals("Sab")) {
                holder.sabado.setBackgroundResource(R.drawable.round_button);
                holder.sabado.setTextAppearance(context, R.style.DiasFontOn);
            } else if (horario.getDia().equals("Dom")) {
                holder.domingo.setBackgroundResource(R.drawable.round_button);
                holder.domingo.setTextAppearance(context, R.style.DiasFontOn);
            }
        }
    }

    @Override
    public int getItemCount() {

        if (perfilJogos.isEmpty()) {
            return 0;
        }
        return perfilJogos.size();
    }
}

