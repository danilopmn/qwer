package com.medicalflame.framingham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProfileResultActivity extends ActionBarActivity {


    private String[] myStringArray = {"Entre 20 e 34 anos", "Entre 35 e 39 anos", "Entre 40 e 44 anos", "Entre 45 e 49 anos", "Entre 50 e 54 anos", "Entre 55 e 59 anos" , "Entre 60 e 64 anos", "Entre 65 e 69 anos", "Entre 70 e 74 anos"};
    private int[] mensPoints = {-1,0,1,2,3,4,5,6,7};
    private int[] womansPoints = {-9,-4,0,3,6,7,8,8,8};

    private String[] myColesterolStringArray = {"Menos de 160", "Entre 160 e 199", "Entre 200 e 239", "Entre 240 e 279", "Maior ou igual 280"};
    private int[] mensColesterolPoints = {-3,0,1,2,3};
    private int[] womansColesterolPoints = {-2,0,1,1,3};

    private String[] myColesterolHDLStringArray = {"Menos de 35", "Entre 35 e 44", "Entre 45 e 49", "Entre 50 e 59", "Mais de 60"};
    private int[] mensColesterolHDLPoints = {2,1,0,0,-1};
    private int[] womansColesterolHDLPoints = {5,2,1,0,-3};

    private String[] myPressurePASStringArray = {"Menos de 120", "Entre 120 e 129", "Entre 130 e 139", "Entre 140 e 159", "Mais de 160"};
    private int[] mensPressurePoints = {-3,0,1,2,3};
    private int[] womansPressurePoints = {-2,0,1,1,3};

    private String[] myPressurePADStringArray = {"Menos de 80", "Entre 80 e 84", "Entre 85 e 89", "Entre 90 e 99", "Mais de 100"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_result_layout);

        Bundle extras = getIntent().getExtras();

        ImageView ima = (ImageView) findViewById(R.id.imageView);

        TextView poct = (TextView) findViewById(R.id.porce);

        TextView title = ((TextView)findViewById(R.id.parabens));
        poct.setText(extras.getString("fran")+"%");
        if(Integer.parseInt(extras.getString("fran")) <= 9){
            poct.setTextColor(Color.parseColor("#469F67"));
            title.setTextColor(Color.parseColor("#469F67"));
            title.setText(R.string.parabens);
            ima.setImageResource(R.drawable.coracao);
        } else if (Integer.parseInt(extras.getString("fran"))  <= 19){
            title.setTextColor(Color.parseColor("#ffa800"));
            title.setText(R.string.atencao);
            poct.setTextColor(Color.parseColor("#ffa800"));
            ima.setImageResource(R.drawable.coracao3);
        } else {
            ima.setImageResource(R.drawable.coracao2);
            title.setText(R.string.cuidado);
            title.setTextColor(Color.parseColor("#9e4547"));
            poct.setTextColor(Color.parseColor("#9e4547"));
        }

        if( extras.getString("sexo") != "1") {
            ((TextView)findViewById(R.id.sexo)).setText("Masculino");
        } else {
            ((TextView)findViewById(R.id.sexo)).setText("Feminino");
        }

        if( extras.getString("fuma") == "1") {
            ((TextView)findViewById(R.id.fuma)).setText("Sim");
        } else {
            ((TextView)findViewById(R.id.fuma)).setText("Não");
        }

        if( extras.getString("diabetes") == "1") {
            ((TextView)findViewById(R.id.diabetes)).setText("Sim");
        } else {
            ((TextView)findViewById(R.id.diabetes)).setText("Não");
        }

        ((TextView)findViewById(R.id.idade)).setText(myStringArray[Integer.parseInt(extras.getString("idade"))]);
        ((TextView)findViewById(R.id.ct)).setText(myColesterolStringArray[Integer.parseInt(extras.getString("ct"))]);
        ((TextView)findViewById(R.id.hdl)).setText(myColesterolHDLStringArray[Integer.parseInt(extras.getString("hdl"))]);
        ((TextView)findViewById(R.id.pas)).setText(myPressurePASStringArray[Integer.parseInt(extras.getString("pas"))]);
        ((TextView)findViewById(R.id.pad)).setText(myPressurePADStringArray[Integer.parseInt(extras.getString("pad"))]);



    }

}
