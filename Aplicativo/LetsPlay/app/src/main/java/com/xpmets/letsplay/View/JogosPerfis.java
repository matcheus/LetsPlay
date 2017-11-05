package com.xpmets.letsplay.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xpmets.letsplay.Controller.PerfilJogosAdapter;
import com.xpmets.letsplay.Model.Perfil;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;

import java.util.ArrayList;
import java.util.List;

public class JogosPerfis extends Fragment {

    private View view;
    private Usuario usuario;
    private ArrayList<Perfil> perfilJogos = new ArrayList<>();
    private TextView perfilVazio;
    private RecyclerView recyclerPerfilJogos;
    private PerfilJogosAdapter adapter;
    Intent intent;
    private String mUserId = mFirebaseUser.getUid();
    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    static private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference refUsuario = mDatabase.child("users").child(mUserId).child("cadastro");
    LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_jogos_perfil, null);
        recyclerPerfilJogos = view.findViewById(R.id.recycle_view_perfis);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), AdicionarJogoPerfil.class);
                intent.putExtra("usuario", usuario);
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
            if (isAdded()) {
                LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
                recyclerPerfilJogos.setLayoutManager(llm);
                adapter = new PerfilJogosAdapter(listaJogos, this.getContext());
                recyclerPerfilJogos.setAdapter(adapter);
            }
        }
    }

    private void atualizarDados() {
        perfilJogos = usuario.getPerfis();
        if (isAdded()) {
            setRecyclerPerfilJogos(perfilJogos);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //Recuperando dados do Firebase
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        refUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    Usuario use = vacSnapshot.getValue(Usuario.class);
                    usuario = use;
                    atualizarDados();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
