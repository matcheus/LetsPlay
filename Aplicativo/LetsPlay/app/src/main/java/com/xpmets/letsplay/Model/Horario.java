package com.xpmets.letsplay.Model;

import java.io.Serializable;

public class Horario implements Serializable{

    private String dia;
    private String horaInical, horaFinal;

    public Horario() {

    }

    public Horario(String dia, String horaInical, String horaFinal) {
        this.dia = dia;
        this.horaInical = horaInical;
        this.horaFinal = horaFinal;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInical() {
        return horaInical;
    }

    public void setHoraInical(String horaInical) {
        this.horaInical = horaInical;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
}
