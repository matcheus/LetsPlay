package com.xpmets.letsplay.Controller;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import com.xpmets.letsplay.R;
import com.xpmets.letsplay.View.Cadastro;


/**
 * Classe responável por realizar a validação dos EditText do aplicativo
 * */
public class Validador {

    public static int validaCadastro(EditText nomeEditText, EditText emailEditText, EditText passwordEditText) {

        String nome = nomeEditText.getText().toString();

        if (nome.isEmpty()) {
            return 1;
        }

        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            return 2;
        }

        String password = passwordEditText.getText().toString().trim();

        if (password.length() < 6) {
            return 3;
        }

        if (Cadastro.getCheckNascimento() == false) {
            return 5;
        }

        return 0;
    }

    public static void mostrarNotfCadastro(View view, int validador) {

        switch (validador) {
            case 1:
                Snackbar.make(view, R.string.cadastro_nome_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 2:
                Snackbar.make(view, R.string.cadastro_email_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 3:
                Snackbar.make(view, R.string.cadastro_senha_erro, Snackbar.LENGTH_LONG).show();
                break;
            case 4:
                Snackbar.make(view, R.string.cadastro_nascimento_erro, Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(view, R.string.cadastro_erro_geral, Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
