package com.xpmets.letsplay.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xpmets.letsplay.Controller.DataMacara;
import com.xpmets.letsplay.DAO.UsuarioDAO;
import com.xpmets.letsplay.Model.Usuario;
import com.xpmets.letsplay.R;

import java.util.Calendar;


public class EditarCadastro extends AppCompatActivity implements View.OnClickListener {

    static final int DialogId = 0;
    private Usuario usuario;
    static boolean isUpdating;
    private Boolean checkNascimento = false;
    protected Spinner spinner;
    private MaterialDialog dialog;
    final Calendar c = Calendar.getInstance();
    int ano = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    EditText usuarioEdit, nomeEdit, sobreMimEdit;
    TextView dataTextView;
    private FirebaseAuth mFirebaseAuth;
    FirebaseUser user;
    String result = "Usuário atualizado com sucesso.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cadastro);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("usuario");

        spinner = (Spinner) findViewById(R.id.sexo_cadastro);
        configuraSpinner(spinner);
        showDialog();

        preencherInfo();

        Button botaoCancelar = (Button) findViewById(R.id.bttnCancelar_editar_cadastro);
        botaoCancelar.setOnClickListener(EditarCadastro.this);
        Button botaoSalvar = (Button) findViewById(R.id.bttnSalvar_editar_cadastro);
        botaoSalvar.setOnClickListener(EditarCadastro.this);

        // Iniciando o FirebaseAuth
        try {
            mFirebaseAuth = FirebaseAuth.getInstance();
            user = Usuario.getFireBaseUser();
        } catch (Exception e) {
            mFirebaseAuth = null;
        }
    }

    private void preencherInfo() {
        nomeEdit = (EditText) findViewById(R.id.edit_nome_editar_cadastro);
        sobreMimEdit = (EditText) findViewById(R.id.edit_sobre_mim);
        usuarioEdit = (EditText) findViewById(R.id.edit_usuario_editar_cadastro);

        nomeEdit.setText(usuario.getNome());
        usuarioEdit.setText(usuario.getUsuario());
        sobreMimEdit.setText(usuario.getSobre());
        DataMacara dataMasc = new DataMacara();
        String data = dataMasc.dataTransforma(usuario.getDiaNascimento(), usuario.getMesNascimento(),
                usuario.getAnoNascimento());
        dataTextView = (TextView) findViewById(R.id.txtData_cadastro);
        dataTextView.setText(data);
    }

    private void configuraSpinner(Spinner spinner) {
        String[] sexoStr = new String[]{"Masculino", "Feminino"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sexoStr);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (usuario.getSexo().equals("Masculino")) {
            spinner.setSelection(0);
        } else {
            spinner.setSelection(1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bttnCancelar_editar_cadastro:
                super.onBackPressed();
                setResult(Activity.RESULT_CANCELED);
                break;

            case R.id.bttnSalvar_editar_cadastro:

                dialog = new MaterialDialog.Builder(this).content(R.string.atualizando_cadastro).progress(true, 0).show();
                try {
                    if (checkNascimento == true) {
                        usuario.setDiaNascimento(dia);
                        usuario.setMesNascimento(mes);
                        usuario.setAnoNascimento(ano);
                    }
                    usuario.setNome(nomeEdit.getText().toString());
                    usuario.setUsuario(usuarioEdit.getText().toString());
                    usuario.setSobre(sobreMimEdit.getText().toString());
                    usuario.setSexo(spinner.getSelectedItem().toString());

                    UsuarioDAO.atualizarUsuario(usuario);

                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("usuario", usuario);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } catch (Exception e) {
                    dialog.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), R.string.cadastro_erro_geral, Snackbar.LENGTH_LONG).show();
                }
        }
    }

    public static void setIsUpdating(boolean isUpdating) {
        EditarCadastro.isUpdating = isUpdating;
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

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            checkNascimento = true;
            ano = year;
            mes = month + 1;
            dia = dayOfMonth;

            DataMacara data = new DataMacara();

            dataTextView.setText(data.dataTransforma(dia, mes, ano));
        }
    };
}
