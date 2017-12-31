package com.xpmets.letsplay.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xpmets.letsplay.R;

import java.util.ArrayList;
import java.util.List;

public class AmigosRecycleAdapter extends RecyclerView.Adapter<AmigosRecycleAdapter.AmigoViewHolder> {

    List<String> amigos;

    public AmigosRecycleAdapter(List<String> amigos) {
        this.amigos = amigos;
    }

    //inicializa a viewHolder (cardView)
    @Override
    public AmigoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_amigo, parent, false);
        AmigoViewHolder horarioView = new AmigoViewHolder(v);
        return horarioView;
    }

    public static class AmigoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeUsuario;

        AmigoViewHolder(final View itemView) {
            super(itemView);
            nomeUsuario = itemView.findViewById(R.id.nome_amigo);

            //card abre a informação do perfil do jogo
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(AmigoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
