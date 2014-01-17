package com.medicalflame.cardiapp;

/**
 * Created by danilopmn on 11/30/13.
 */
public class Fran {

    private final String id;
    private final String fran;
    private final String date;
    private final String idade;
    private final String ct;
    private final String hdl;
    private final String pas;
    private final String pad;
    private final String sexo;
    private final String fuma;
    private final String diabetes;

    public Fran(String id, String fran, String date, String idade, String ct, String hdl, String pas, String pad, String sexo, String fuma, String diabetes){

        this.id = id;
        this.fran = fran;
        this.date = date;
        this.idade = idade;
        this.ct = ct;
        this.hdl = hdl;
        this.pas = pas;
        this.pad = pad;
        this.sexo = sexo;
        this.fuma = fuma;
        this.diabetes = diabetes;
    }

    public String getId() {
        return id;
    }

    public String getFran() {
        return fran;
    }

    public String getDate() {
        return date;
    }

    public String getIdade() {
        return idade;
    }

    public String getCt() {
        return ct;
    }

    public String getHdl() {
        return hdl;
    }

    public String getPas() {
        return pas;
    }

    public String getPad() {
        return pad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getFuma() {
        return fuma;
    }

    public String getDiabetes() {
        return diabetes;
    }
}
