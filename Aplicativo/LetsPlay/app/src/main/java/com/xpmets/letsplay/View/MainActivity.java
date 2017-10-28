package com.xpmets.letsplay.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
    private FragmentManager fm = getSupportFragmentManager();
    private long lastBackPressTime = 0;
    private Toast toast;
    private static Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };


        if (savedInstanceState == null) {
            JogosPerfis jogosPerfis = new JogosPerfis();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.frameFragment, jogosPerfis, "jogosPerfis");
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, R.string.fechar_app, Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
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
        // Itens de Navegação da Barra Lateral
        int id = item.getItemId();

        if (id == R.id.perfilJogos) {
            JogosPerfis jogosPerfis = new JogosPerfis();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameFragment, jogosPerfis, "jogosPerfis");
            fragmentTransaction.addToBackStack("das");
            fragmentTransaction.commit();
        } else if (id == R.id.comunidade) {

        } else if (id == R.id.amigos) {

        } else if (id == R.id.conversas) {

        } else if (id == R.id.conta) {
            Intent intent = new Intent(getBaseContext(), Conta.class);
            intent.putExtra("usuario", this.usuario);
            startActivity(intent);

        } else if (id == R.id.config) {

        } else if (id == R.id.sobre) {

        } else if (id == R.id.logout) {
            mFirebaseAuth.signOut();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Colocar as informações do usuario na barra lateral
    private void infoBarraLateral(Usuario user) {
        this.usuario = user;
        TextView nomeUsuario = findViewById(R.id.textNomeUsuario);
        TextView emailUsuario = findViewById(R.id.textEmailUsuario);
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
                    Usuario use = vacSnapshot.getValue(Usuario.class);
                    infoBarraLateral(use);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
