package com.xpmets.letsplay.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.xpmets.letsplay.Controller.Validador;
import com.xpmets.letsplay.DAO.UsuarioDAO;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;

import java.util.Calendar;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

    static final int DialogId = 0;
    protected static Boolean checkNascimento = false;
    static boolean isUpdating;
    final Calendar c = Calendar.getInstance();
    protected EditText nomeEditText;
    protected EditText usuarioEditText;
    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected Spinner spinner;
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    TextView diaTextView, mesTextView, anoTextView;
    private FirebaseAuth mFirebaseAuth;
    private MaterialDialog dialog;

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            checkNascimento = true;
            ano = year;
            mes = month + 1;
            dia = dayOfMonth;

            diaTextView = (TextView) findViewById(R.id.txtDia_cadastro);
            diaTextView.setText(" " + String.valueOf(dia));

            mesTextView = (TextView) findViewById(R.id.txtMes_cadastro);
            mesTextView.setText("/" + String.valueOf(mes));

            anoTextView = (TextView) findViewById(R.id.txtAno_cadastro);
            anoTextView.setText("/" + String.valueOf(ano));
        }
    };

    public static void setIsUpdating(boolean isUpdating) {
        Cadastro.isUpdating = isUpdating;
    }

    public static Boolean getCheckNascimento() {
        return checkNascimento;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        spinner = (Spinner) findViewById(R.id.sexo_cadastro);
        configuraSpinner(spinner);

        showDialog();

        nomeEditText = (EditText) findViewById(R.id.edit_nome_cadastro);
        usuarioEditText = (EditText) findViewById(R.id.edit_usuario_cadastro);

        Button botaoVoltar = (Button) findViewById(R.id.bttnVoltar_cadastro);
        botaoVoltar.setOnClickListener(Cadastro.this);
        Button botaoFinalizar = (Button) findViewById(R.id.bttnFinalizar_cadastro);
        botaoFinalizar.setOnClickListener(Cadastro.this);

        // Iniciando o FirebaseAuth
        try {
            mFirebaseAuth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            mFirebaseAuth = null;
        }

        passwordEditText = (EditText) findViewById(R.id.edit_senha_cadastro);
        emailEditText = (EditText) findViewById(R.id.edit_email_cadastro);

    }

    public void showDialog() {
        ImageButton btnData = (ImageButton) findViewById(R.id.bttnDataNascimento_cadastro);

        btnData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DialogId);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DialogId) {
            return new DatePickerDialog(this, dPickerListener, ano, mes, dia);
        } else {
            return null;
        }
    }

    public void configuraSpinner(Spinner spinner) {
        String[] sexoStr = new String[]{"Masculino", "Feminino", "Outro"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexoStr);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent it;
        switch (v.getId()) {
            case R.id.bttnVoltar_cadastro:
                it = new Intent(this, Login.class);
                startActivity(it);
                break;

            case R.id.bttnFinalizar_cadastro:

                int validador = Validador.validaCadastro(nomeEditText, emailEditText, passwordEditText);

                if (validador == 0) {

                    dialog = new MaterialDialog.Builder(this).content(R.string.realizando_cadastro).progress(true, 0).show();
                    mFirebaseAuth.createUserWithEmailAndPassword(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim())
                            .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Usuario usuario = new Usuario(nomeEditText.getText().toString(), usuarioEditText.getText().toString(),
                                                emailEditText.getText().toString().trim(), spinner.getSelectedItem().toString(),
                                                dia, mes, ano);

                                        UsuarioDAO.persistirUsuario(usuario);

                                        dialog.dismiss();
                                        Intent intent = new Intent(Cadastro.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra("Modo offline", false);
                                        startActivity(intent);
                                    } else {
                                        dialog.dismiss();
                                        Snackbar.make(findViewById(android.R.id.content), R.string.cadastro_erro_geral, Snackbar.LENGTH_LONG).show();
                                    }
                                }
                            });

                } else {
                    Validador.mostrarNotfCadastro(findViewById(android.R.id.content), validador);
                }
        }
    }
}
