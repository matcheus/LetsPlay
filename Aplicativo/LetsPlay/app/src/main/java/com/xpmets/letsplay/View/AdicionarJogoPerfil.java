package com.xpmets.letsplay.View;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.xpmets.letsplay.Controller.TimePickerFragment;
import com.xpmets.letsplay.Model.Horario;
import com.xpmets.letsplay.R;

import java.util.ArrayList;

public class AdicionarJogoPerfil extends AppCompatActivity {

    private EditText nomeJogo, descricaoJogo;
    private ArrayList<Horario> horarios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jogo_perfil);

    }

    public void horarioSegunda(View view) {
        mostrarRelogio(view);
        ativarBotaoDia((Button) findViewById(R.id.botaosegunda));
    }

    private void mostrarRelogio(View view) {
        TimePickerFragment timePiker = new TimePickerFragment();
        timePiker.setEditText(retornaEditText(view));
        DialogFragment newFragment = timePiker;
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private EditText retornaEditText(View view) {
        EditText editText = (EditText) view;
        return editText;
    }

    private void ativarBotaoDia(Button button) {
        button.setBackgroundResource(R.drawable.round_button);
        button.setTextAppearance(this, R.style.DiasFontOn);
    }
}
