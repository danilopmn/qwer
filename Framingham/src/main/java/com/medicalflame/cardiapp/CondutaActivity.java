package com.medicalflame.cardiapp;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.medicalflame.cardiapp.R;

public class CondutaActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conduta_layout);
        Bundle extras = getIntent().getExtras();
        TextView title = (TextView)findViewById(R.id.title);
        TextView texto = (TextView)findViewById(R.id.texto);

        Resources r = getResources();


        String textoFinal = "";

        String[] sexo = r.getStringArray(R.array.sexo_array);
        textoFinal += sexo[extras.getInt("sexo")];
        textoFinal += " ";
        textoFinal += getString(R.string.conduta_idade);

        textoFinal += "\n";

        String[] ctotal = r.getStringArray(R.array.colesterol_total);
        textoFinal += ctotal[extras.getInt("ct")];

        textoFinal += " ";

        String[] tabaco = r.getStringArray(R.array.conduta_tabaco);
        textoFinal += tabaco[extras.getInt("fumo")];

        textoFinal += "\n";

        String[] diabetes = r.getStringArray(R.array.pressao);
        textoFinal += diabetes[extras.getInt("pas")];

        textoFinal += " ";

        if(extras.getInt("sexo") == 0) {
            String[] hdl = r.getStringArray(R.array.conduta_hdl_homem);
            textoFinal += hdl[extras.getInt("hdl")];

        } else {
            String[] hdl = r.getStringArray(R.array.conduta_hdl_mulher);
            textoFinal += hdl[extras.getInt("hdl")];
        }



        texto.setText(textoFinal);

        if(extras.getInt("porcentagem") <= 9){
            title.setTextColor(Color.parseColor("#469F67"));
        } else if (extras.getInt("porcentagem")  <= 19){
            title.setTextColor(Color.parseColor("#ffa800"));
        } else {
            title.setTextColor(Color.parseColor("#9e4547"));
        }
    }
}
