package com.xpmets.letsplay.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {

    private String id;
    private String nome;
    private String usuario;
    private String email;
    private String sexo;
    private String senha;
    private String sobre = "";
    private int diaNascimento, mesNascimento, anoNascimento;
    private ArrayList<Perfil> perfis = new ArrayList<>();
    private ArrayList<String> amigosId = new ArrayList<>();
    private static FirebaseUser currentFirebaseUser;

    public Usuario() {

    }

    public Usuario(String nome, String usuario, String email, String sexo,
                   int diaNascimento, int mesNascimento, int anoNascimento) {
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.sexo = sexo;
        this.diaNascimento = diaNascimento;
        this.mesNascimento = mesNascimento;
        this.anoNascimento = anoNascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public int getDiaNascimento() {
        return diaNascimento;
    }

    public void setDiaNascimento(int diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    public int getMesNascimento() {
        return mesNascimento;
    }

    public void setMesNascimento(int mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public ArrayList<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(ArrayList<Perfil> perfis) {
        this.perfis = perfis;
    }

    public ArrayList<String> getAmigosId() {
        return amigosId;
    }

    public void setAmigosId(ArrayList<String> amigosId) {
        this.amigosId = amigosId;
    }

    public static FirebaseUser getCurrentFirebaseUser() {
        return currentFirebaseUser;
    }

    public static void setCurrentFirebaseUser(FirebaseUser currentFirebaseUser) {
        Usuario.currentFirebaseUser = currentFirebaseUser;
    }

    public void adicionarJogoPerfil(Perfil perfil){
        this.perfis.add(perfil);
    }

    public static String getmUserId() {
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentFirebaseUser.getUid();
    }

    public static FirebaseUser getFireBaseUser() {
        return currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }
}
