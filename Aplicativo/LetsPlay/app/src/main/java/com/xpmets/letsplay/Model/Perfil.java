package com.xpmets.letsplay.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Perfil implements Serializable{

    private String idJogo;
    private String nomeJogo;
    private String descricao;
    private ArrayList<Horario> horarios = new ArrayList<>();

    public Perfil(){

    }

    public Perfil(String nomeJogo, String descricao, ArrayList<Horario> horarios){
        this.nomeJogo = nomeJogo;
        this.descricao = descricao;
        this.horarios = horarios;
    }

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
