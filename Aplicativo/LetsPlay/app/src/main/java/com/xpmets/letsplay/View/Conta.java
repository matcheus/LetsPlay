package com.xpmets.letsplay.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xpmets.letsplay.Controller.DataMacara;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;

public class Conta extends AppCompatActivity {

    private Usuario usuario;
    private TextView nome, user, email, data, sexo, sobreMim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        setContentView(R.layout.activity_conta);
        preencherInfo(usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.conta_editar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void preencherInfo(Usuario usua){
        nome = (TextView) findViewById(R.id.text_nome_conta);
        nome.setText(usua.getNome());
        user = (TextView) findViewById(R.id.text_usuario_conta);
        user.setText(usua.getUsuario());
        email = (TextView) findViewById(R.id.text_email_conta);
        email.setText(usua.getEmail());
        data = (TextView) findViewById(R.id.text_data_conta);
        DataMacara mascara = new DataMacara();
        data.setText(mascara.dataTransforma(usua.getDiaNascimento(), usua.getMesNascimento(),
                usua.getAnoNascimento()));
        sexo = (TextView) findViewById(R.id.text_genero_conta);
        sexo.setText(usua.getSexo());
        sobreMim = (TextView) findViewById(R.id.text_sobre_conta);
        if (!usua.getSobre().equals("")){
            sobreMim.setText(usua.getSobre());
        }
    }
}
