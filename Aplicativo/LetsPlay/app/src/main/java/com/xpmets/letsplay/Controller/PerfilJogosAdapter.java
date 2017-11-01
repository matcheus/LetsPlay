package com.xpmets.letsplay.Controller;


import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xpmets.letsplay.Model.Perfil;
import com.xpmets.letsplay.R;
import com.xpmets.letsplay.View.JogoPerfilDetalhado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PerfilJogosAdapter extends RecyclerView.Adapter<PerfilJogosAdapter.VacinaViewHolder> implements Serializable {

    List<Perfil> perfilJogos;
    static private List<Perfil> perfilJogosG = new ArrayList<>();

    public PerfilJogosAdapter(List<Perfil> vacinas) {
        this.perfilJogos = new ArrayList<>();
        this.perfilJogos = vacinas;
        perfilJogosG = vacinas;
    }

    public static class VacinaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nome_jogo;
        TextView dose_vacina;

        VacinaViewHolder(final View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            nome_jogo = itemView.findViewById(R.id.nome_jogo);

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

    //inicializa a viewHolder (cardView)
    @Override
    public VacinaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jogo_perfil, parent, false);
        VacinaViewHolder vacinaView = new VacinaViewHolder(v);
        return vacinaView;
    }

    //adiciona valores aos itens da view
    @Override
    public void onBindViewHolder(VacinaViewHolder holder, int position) {
        holder.nome_jogo.setText(perfilJogos.get(position).getNomeJogo());
        //holder.dose_vacina.setText(perfilJogos.get(position).getDosesTomadas() + "/" + perfilJogos.get(position).getQuantidadeDoses());
    }

    @Override
    public int getItemCount() {

        if (perfilJogos.isEmpty()) {
            return 0;
        }
        return perfilJogos.size();
    }
}

