package com.xpmets.letsplay.Controller;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.xpmets.letsplay.R;
import com.xpmets.letsplay.View.AdicionarJogoPerfil;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private Button botaoDia;
    private EditText editText;
    private TimePickerDialog time;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        time = new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        time.setButton(DialogInterface.BUTTON_NEUTRAL, "Limpar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editText.setText("");
                desativaBotaoDia(botaoDia);
            }
        });
        return time;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
        editText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
        ativarBotaoDia(botaoDia);
    }

    private void ativarBotaoDia(Button button) {
        button.setClickable(true);
        button.setBackgroundResource(R.drawable.round_button);
        button.setTextAppearance(getContext(), R.style.DiasFontOn);
    }

    private void desativaBotaoDia(Button button) {
        button.setClickable(false);
        button.setBackgroundResource(R.drawable.round_button_off);
        button.setTextAppearance(getContext(), R.style.DiasFontOff);
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public void setBotaoDia(Button button) {
        this.botaoDia = button;
    }
}
