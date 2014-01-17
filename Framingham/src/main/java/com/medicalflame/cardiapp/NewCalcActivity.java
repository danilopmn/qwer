package com.medicalflame.cardiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.medicalflame.cardiapp.R;

public class NewCalcActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private String[] myStringArray = {"Entre 20 e 34 anos", "Entre 35 e 39 anos", "Entre 40 e 44 anos", "Entre 45 e 49 anos", "Entre 50 e 54 anos", "Entre 55 e 59 anos" , "Entre 60 e 64 anos", "Entre 65 e 69 anos", "Entre 70 e 74 anos", "Mais de 75"};
    private int[] mensPoints = {-9,-4,0,3,6,8,10,11,12,13};
    private int[] womansPoints = {-7,-3,0,3,6,8,10,12,14,16};

    private String[] myColesterolStringArray = {"Menos de 160", "Entre 160 e 199", "Entre 200 e 239", "Entre 240 e 279", "Maior ou igual 280"};
    private int[][] mensColesterolPoints = {{0,4,7,9,11},{0,3,5,6,8},{0,2,3,4,5},{0,1,1,2,3},{0,0,0,1,1}};
    private int[][] womansColesterolPoints = {{0,4,8,11,13},{0,3,6,8,10},{0,2,4,5,7},{0,1,2,3,4},{0,1,1,2,2}};

    private String[] myColesterolHDLStringArray = {"Menos de 40", "Entre 40 e 49", "Entre 50 e 59", "Mais de 60"};
    private int[] mensColesterolHDLPoints = {2,1,0,-1};
    private int[] womansColesterolHDLPoints = {2,1,0,-1};

    private String[] myPressurePASStringArray = {"Menos de 120", "Entre 120 e 129", "Entre 130 e 139", "Entre 140 e 159", "Mais de 160"};
    private int[][] mensPressurePoints = {{0,0,1,1,3},{0,1,2,2,3}};
    private int[][] womansPressurePoints = {{0,1,2,3,4},{0,3,4,5,6}};
    private int tratada = 0;

    private int[][] mensFumo = {{0,8},{0,5},{0,3},{0,1},{0,1}};
    private int[][] womansFumo = {{0,9},{0,7},{0,4},{0,2},{0,1}};


    private int colesterolChoice = 0;
    private int colesterolChoiceHDL = 0;
    private int sexo = 0;
    private int pressureChoice = 0;
    private int fumo = 0;
    private int idadeChoice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_layout);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerColesterol);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myColesterolStringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.spinnerColesterolHDL);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myColesterolHDLStringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner = (Spinner) findViewById(R.id.spinnerPressao);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myPressurePASStringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        spinner = (Spinner) findViewById(R.id.spinnerIdade);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,myStringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        ((Button)findViewById(R.id.buttonRisco)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResults(null);
            }
        });

        CompoundButton cbt = (CompoundButton) findViewById(R.id.sexoOp);
        cbt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) sexo = 1;
                else sexo = 0;
            }
        });


        CheckedTextView cb = (CheckedTextView) findViewById(R.id.trata);
        final CheckedTextView finalCb = cb;
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCb.setChecked(!finalCb.isChecked());
                onCheckboxClickedDiabetes(v);
            }
        });

        cb = (CheckedTextView) findViewById(R.id.fumo);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCb.setChecked(!finalCb.isChecked());
                onCheckboxClickedFumo(v);
            }
        });
/*
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, myStringArray);
        ListView listView = (ListView) findViewById(R.id.listView2);
        TextView tv = (TextView) getLayoutInflater().inflate(R.layout.title, null);
        tv.setText("Idade");
        listView.addHeaderView(tv);
        listView.setAdapter(adapter);
        final Context a = this;
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(a, String.valueOf(mensPoints[position-1]), LENGTH_SHORT).show();
                Intent i = new Intent(a,ListUsers.class);
                i.putExtra("men",true);
                i.putExtra("points",mensPoints[position-1]);
                startActivity(i);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);*/
    }
    public void onCheckboxClickedDiabetes(View view){
        boolean checked = ((CheckedTextView) view).isChecked();
        if(checked){
            tratada = 1;
        }
    }

    public void onCheckboxClickedFumo(View view){
        boolean checked = ((CheckedTextView) view).isChecked();
        if(checked){
            fumo = 1;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.calc_rick:
                getResults(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 91){
            if( resultCode == RESULT_OK){
                setResult(RESULT_OK,data);
                finish();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(view == null) return;
        switch(adapterView.getId()) {
            case R.id.spinnerColesterol:
                colesterolChoice = i;
                break;
            case R.id.spinnerColesterolHDL:
                colesterolChoiceHDL = i;
                break;
            case R.id.spinnerPressao:
                pressureChoice = i;
                break;
            case R.id.spinnerIdade:
                idadeChoice = i;
                break;
        }
    }

    public void getResults(View view){
        Intent i = new Intent(this,ResultActivity.class);
        int pontos = 0;
        int porcentagem = 0;
        if(sexo == 0){
            pontos+=mensColesterolHDLPoints[colesterolChoiceHDL];
            pontos+=mensColesterolPoints[idadeChoice/2][colesterolChoice];
            pontos+=mensPressurePoints[tratada][pressureChoice];
            pontos+=mensPoints[idadeChoice];
            pontos+=mensFumo[idadeChoice/2][fumo];
            if( pontos <= -1){
                porcentagem = 1;
            }else if( pontos == 0 ){
                porcentagem = 1;
            }else if( pontos == 1 ){
                porcentagem = 1;
            }else if( pontos == 2 ){
                porcentagem = 1;
            }else if( pontos == 3 ){
                porcentagem = 1;
            }else if( pontos == 4){
                porcentagem = 1;
            }else if( pontos == 5 ){
                porcentagem = 2;
            }else if( pontos == 6 ){
                porcentagem= 2;
            }else if( pontos == 7 ){
                porcentagem = 3;
            }else if( pontos == 8 ){
                porcentagem= 4;
            }else if( pontos == 9 ){
                porcentagem= 5;
            }else if( pontos == 10 ){
                porcentagem = 6;
            }else if( pontos == 11 ){
                porcentagem= 8;
            }else if( pontos == 12 ){
                porcentagem = 10;
            }else if( pontos == 13 ){
                porcentagem= 12;
            }else if( pontos == 14 ){
                porcentagem= 16;
            }else if( pontos == 15 ){
                porcentagem= 20;
            }else if( pontos == 16 ){
                porcentagem= 25;
            }else {
                porcentagem= 30;
            }
        } else {
            pontos+=womansColesterolHDLPoints[colesterolChoiceHDL];
            pontos+=womansColesterolPoints[idadeChoice/2][colesterolChoice];
            pontos+=womansPressurePoints[tratada][pressureChoice];
            pontos+=womansPoints[idadeChoice];
            pontos+=womansFumo[idadeChoice/2][fumo];
            if( pontos <= 8){
                porcentagem = 1;
            }else if( pontos == 9 ){
                porcentagem= 1;
            }else if( pontos == 10 ){
                porcentagem = 1;
            }else if( pontos == 11 ){
                porcentagem= 1;
            }else if( pontos == 12 ){
                porcentagem = 1;
            }else if( pontos == 13 ){
                porcentagem= 2;
            }else if( pontos == 14 ){
                porcentagem= 2;
            }else if( pontos == 15 ){
                porcentagem= 3;
            }else if( pontos == 16 ){
                porcentagem= 4;
            }else if( pontos == 17 ){
                porcentagem= 5;
            }else if( pontos == 18 ){
                porcentagem= 6;
            }else if( pontos == 19 ){
                porcentagem= 8;
            }else if( pontos == 20 ){
                porcentagem= 11;
            }else if( pontos == 21 ){
                porcentagem= 14;
            }else if( pontos == 22 ){
                porcentagem= 17;
            }else if( pontos == 23 ){
                porcentagem= 22;
            }else if( pontos == 24 ){
                porcentagem= 27;
            }else {
                porcentagem= 30;
            }
        }
        i.putExtra("porcentagem",porcentagem);
        Bundle extras = getIntent().getExtras();
        i.putExtra("userid",extras.getLong("userid"));
        i.putExtra("name", getIntent().getExtras().getString("name"));
        i.putExtra("ct",colesterolChoice);
        i.putExtra("hdl",colesterolChoiceHDL);
        i.putExtra("diabetes",tratada);
        i.putExtra("pas",pressureChoice);
        i.putExtra("idade",idadeChoice);
        i.putExtra("sexo",sexo);
        i.putExtra("fumo",fumo);
        startActivityForResult(i,91);
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
