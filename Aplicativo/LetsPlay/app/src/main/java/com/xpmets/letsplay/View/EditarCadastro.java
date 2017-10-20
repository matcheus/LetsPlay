package com.xpmets.letsplay.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;


public class EditarCadastro extends AppCompatActivity {

    Usuario usuario;
    static boolean isUpdating;
    private FirebaseUser user;
    private String nome, emailUser, senhaAntiga, senhaNova;
    EditText oldPassEdit, newPassEdit;
    private MaterialDialog dialog;
    boolean flagEmail, flagSenha;
    EditText emailEdit, nomeEdt;
    String result = "Usuário atualizado com sucesso.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);

        Intent intent = getIntent();

        usuario = (Usuario) intent.getSerializableExtra("usuario");

        nome = usuario.getNome();
        nomeEdt = (EditText) findViewById(R.id.edit_nome_editar_cadastro);
        nomeEdt.setText(nome);

        emailUser = usuario.getEmail();
        emailEdit = (EditText) findViewById(R.id.edit_email_editar_cadastro);
        emailEdit.setText(emailUser);

        oldPassEdit = (EditText) findViewById(R.id.edit_senha_editar_cadastro_antiga);
        newPassEdit = (EditText) findViewById(R.id.edit_senha_editar_cadastro_nova);


        final Button concluir = (Button) findViewById(R.id.bttnSalvar_editar_cadastro);
        concluir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flagEmail = false;
                flagSenha = false;
                boolean flag = concluir();
                result = "";
                if (flag) {
                    Snackbar.make(findViewById(android.R.id.content), result, Snackbar.LENGTH_LONG).show();
                } else {
                    result = "";

                    if (flagEmail) {
                        result = result + "Email: atualizado\n";
                    } else {
                        result = result + "Email: não atualizado\n";
                    }
                    if (flagSenha) {
                        result = result + "Senha: atualizado\n";
                    } else {
                        result = result + "Senha: não atualizado\n";
                    }
//                    Snackbar.make(findViewById(android.R.id.content), result, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        final Button cancelar = (Button) findViewById(R.id.bttnCancelar_editar_cadastro);
        cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                voltar();
            }
        });
    }

    private boolean concluir() {
        senhaAntiga = oldPassEdit.getText().toString();
        senhaNova = newPassEdit.getText().toString();
        emailUser = emailEdit.getText().toString();
//        dialog = new MaterialDialog.Builder(this).content(R.string.atualizando_cadastro).progress(true, 0).show();
//
//        dialog.dismiss();

        updatePassword();
        updateEmail();

        if (flagEmail && flagSenha) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePassword() {
        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
            final String email = user.getEmail();
            AuthCredential credential = EmailAuthProvider.getCredential(email, senhaAntiga);
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(senhaNova).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    flagSenha = true;
                                }
                            }
                        });
                    }
                }
            });

            return flagSenha;

        } catch (Exception e) {
            flagSenha = false;
            return flagSenha;
        }
    }

    public boolean updateEmail() {
        try {
            user = FirebaseAuth.getInstance().getCurrentUser();
            final String email = user.getEmail();
            if (!email.equals(emailEdit.getText().toString())) {
                AuthCredential credential = EmailAuthProvider.getCredential(email, senhaAntiga);
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updateEmail(emailEdit.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        flagEmail = true;
                                    }
                                }
                            });
                        }
                    }
                });
            }
            return flagEmail;

        } catch (Exception e) {
            flagEmail = false;
            return flagEmail;
        }
    }

    private void voltar() {
        super.onBackPressed();
    }

    public static void setIsUpdating(boolean isUpdating) {
        EditarCadastro.isUpdating = isUpdating;
    }
}
