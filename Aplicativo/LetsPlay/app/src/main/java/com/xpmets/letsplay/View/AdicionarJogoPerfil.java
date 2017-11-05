package com.xpmets.letsplay.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xpmets.letsplay.Controller.TimePickerFragment;
import com.xpmets.letsplay.DAO.UsuarioDAO;
import com.xpmets.letsplay.Model.Horario;
import com.xpmets.letsplay.Model.Perfil;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;

import java.util.ArrayList;

public class AdicionarJogoPerfil extends AppCompatActivity implements View.OnClickListener {

    private Button seg, ter, qua, qui, sex, sab, dom;
    private ArrayList<Horario> horarios = new ArrayList<>();
    private Usuario usuario;
    private MaterialDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jogo_perfil);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        Button botaoCancelar = findViewById(R.id.bttnCancelar_cadastro_jogo);
        botaoCancelar.setOnClickListener(AdicionarJogoPerfil.this);
        Button botaoSalvar = findViewById(R.id.bttnFinalizar_cadastro_jogo);
        botaoSalvar.setOnClickListener(AdicionarJogoPerfil.this);
    }

    private void mostrarRelogio(View view, Button button) {
        TimePickerFragment timePiker = new TimePickerFragment();
        timePiker.setBotaoDia(button);
        timePiker.setEditText(retornaEditText(view));
        DialogFragment newFragment = timePiker;
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private EditText retornaEditText(View view) {
        EditText editText = (EditText) view;
        return editText;
    }

    public void horarioSegunda(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaosegunda));
    }

    public void horarioTerca(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaoterca));
    }

    public void horarioQuarta(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaoquarta));
    }

    public void horarioQuinta(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaoquinta));
    }

    public void horarioSexta(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaosexta));
    }

    public void horarioSabado(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaosabado));
    }

    public void horarioDomingo(View view) {
        mostrarRelogio(view, (Button) findViewById(R.id.botaodomingo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bttnCancelar_cadastro_jogo:
                super.onBackPressed();
                setResult(Activity.RESULT_CANCELED);
                break;
            case R.id.bttnFinalizar_cadastro_jogo:
                dialog = new MaterialDialog.Builder(this).content(R.string.atualizando_cadastro).progress(true, 0).show();
                getHorariosDias();
                try {
                    EditText nomeJogo = findViewById(R.id.edit_nome_jogo);
                    EditText descricaoJogo = findViewById(R.id.edit_descricao_jogo);
                    Perfil perfil = new Perfil(nomeJogo.getText().toString(), descricaoJogo.getText().toString(), horarios);
                    usuario.adicionarJogoPerfil(perfil);

                    UsuarioDAO.atualizarUsuario(usuario);
                    dialog.dismiss();
                    finish();
                } catch (Exception e) {
                    dialog.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), R.string.cadastro_erro_geral, Snackbar.LENGTH_LONG).show();
                }
        }
    }

    private void getHorariosDias() {
        seg = findViewById(R.id.botaosegunda);
        ter = findViewById(R.id.botaoterca);
        qua = findViewById(R.id.botaoquarta);
        qui = findViewById(R.id.botaoquinta);
        sex = findViewById(R.id.botaosexta);
        sab = findViewById(R.id.botaosabado);
        dom = findViewById(R.id.botaodomingo);
        if (seg.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_segunda_inicial);
            EditText editFim = findViewById(R.id.edit_hora_segunda_final);
            Horario horario = new Horario("Seg", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
        if (ter.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_terca_inicial);
            EditText editFim = findViewById(R.id.edit_hora_terca_final);
            Horario horario = new Horario("Ter", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
        if (qua.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_quarta_inicial);
            EditText editFim = findViewById(R.id.edit_hora_quarta_final);
            Horario horario = new Horario("Qua", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
        if (qui.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_quinta_inicial);
            EditText editFim = findViewById(R.id.edit_hora_quinta_final);
            Horario horario = new Horario("Qui", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
        if (sex.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_sexta_inicial);
            EditText editFim = findViewById(R.id.edit_hora_sexta_final);
            Horario horario = new Horario("Sex", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
        if (sab.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_sabado_inicial);
            EditText editFim = findViewById(R.id.edit_hora_sabado_final);
            Horario horario = new Horario("Sab", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
        if (dom.isClickable() == true) {
            EditText editInic = findViewById(R.id.edit_hora_domingo_inicial);
            EditText editFim = findViewById(R.id.edit_hora_domingo_final);
            Horario horario = new Horario("Dom", editInic.getText().toString(), editFim.getText().toString());
            horarios.add(horario);
        }
    }
}
