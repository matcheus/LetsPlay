package com.xpmets.letsplay.Controller;

public class DataMacara {

    public String dataTransforma(int dia, int mes, int ano) {

        String mesTexto = definirMes(mes);
        String dataFinal = dia + " de " + mesTexto + " de " + ano;
        return dataFinal;
    }

    private String definirMes(int mes) {
        switch (mes) {
            case 1:
                return "janeiro";
            case 2:
                return "fevereiro";
            case 3:
                return "março";
            case 4:
                return "abril";
            case 5:
                return "maio";
            case 6:
                return "Junho";
            case 7:
                return "julho";
            case 8:
                return "agosto";
            case 9:
                return "setembro";
            case 10:
                return "outubro";
            case 11:
                return "novembro";
            case 12:
                return "dezembro";
            default:
                return ("Este não é um mês válido!");
        }
    }
}
