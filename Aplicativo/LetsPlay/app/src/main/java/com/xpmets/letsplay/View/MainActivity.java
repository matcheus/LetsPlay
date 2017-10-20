package com.xpmets.letsplay.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xpmets.letsplay.DAO.UsuarioDAO;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String mUserId = mFirebaseUser.getUid();
    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    static private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference refUsuario = mDatabase.child("users").child(mUserId).child("cadastro");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final CardView button = (CardView) findViewById(R.id.card_view);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JogoPerfilDetalhado.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfilJogos) {
            // Handle the camera action
        } else if (id == R.id.comunidade) {

        } else if (id == R.id.amigos) {

        } else if (id == R.id.conversas) {

        } else if (id == R.id.config) {

        } else if (id == R.id.sobre) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Colocar as informações do usuario na barra lateral
    private void infoBarraLateral(Usuario user){
        TextView nomeUsuario = (TextView) findViewById(R.id.textNomeUsuario);
        TextView emailUsuario = (TextView) findViewById(R.id.textEmailUsuario);
        nomeUsuario.setText(user.getNome());
        emailUsuario.setText(user.getEmail());
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Coletando a referencia do usuario
        UsuarioDAO user = new UsuarioDAO();
        user.getUserById(mAuthListener);

        //Recuperando dados do Firebase
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        refUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    //Log.e("Vacina adicionada: ", vacSnapshot.toString());
                    Usuario use = vacSnapshot.getValue(Usuario.class);
                    infoBarraLateral(use);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Editar Cadastro
        /*mFirebaseAuth.addAuthStateListener(mAuthListener);
        refUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    Usuario user = vacSnapshot.getValue(Usuario.class);
                    usuario = user;

                    classificaoTxt = (TextView) findViewById(R.id.classificao_carteira);
                    nomeHeadertxt = (TextView) findViewById(R.id.username_header);
                    emailHeadertxt = (TextView) findViewById(R.id.email_header);

                    String classificacaoStr, nomeHeaderStr, emailHeaderStr;
                    classificacaoStr = calculaClassificacao(usuario);
                    classificaoTxt.setText(classificacaoStr);

                    nomeHeaderStr = usuario.getNome();
                    nomeHeadertxt.setText(nomeHeaderStr);
                    nomeHeadertxt.setTextColor(ContextCompat.getColor(Carteira.this, black));

                    emailHeaderStr = usuario.getEmail();
                    emailHeadertxt.setText(emailHeaderStr);
                    emailHeadertxt.setTextColor(ContextCompat.getColor(Carteira.this, black));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/
    }
}
