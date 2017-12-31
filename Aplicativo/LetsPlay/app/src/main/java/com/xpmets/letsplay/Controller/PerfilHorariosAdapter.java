package com.xpmets.letsplay.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xpmets.letsplay.Model.Horario;
import com.xpmets.letsplay.R;

import java.util.ArrayList;
import java.util.List;

public class PerfilHorariosAdapter extends RecyclerView.Adapter<PerfilHorariosAdapter.HorarioViewHolder>{

    List<Horario> horarios;

    public PerfilHorariosAdapter(List<Horario> horarios, Context context) {
        this.horarios = new ArrayList<>();
        this.horarios = horarios;
    }

    //inicializa a viewHolder
    @Override
    public HorarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horario, parent, false);
        HorarioViewHolder horarioView = new HorarioViewHolder(v);
        return horarioView;
    }

    public static class HorarioViewHolder extends RecyclerView.ViewHolder {
        Button dia;
        TextView horarioInicial;
        TextView horarioFinal;

        HorarioViewHolder(final View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.botao_dia);
            horarioInicial = itemView.findViewById(R.id.text_horario_inicial);
            horarioFinal = itemView.findViewById(R.id.text_horario_final);

            //card abre a informação do perfil do jogo
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
    }

    //adiciona valores aos itens da view
    @Override
    public void onBindViewHolder(HorarioViewHolder holder, int position) {
        holder.dia.setText(horarios.get(position).getDia());
        holder.horarioInicial.setText(horarios.get(position).getHoraInical());
        holder.horarioFinal.setText(horarios.get(position).getHoraFinal());
    }

    @Override
    public int getItemCount() {

        if (horarios.isEmpty()) {
            return 0;
        }
        return horarios.size();
    }
}
