package com.medicalflame.cardiapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.medicalflame.cardiapp.R;

public class ResultActivity extends Activity {

    private int porcentagem;
    private boolean flagJatem;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getExtras();
        flagJatem = false;
        if (extras.containsKey("userid")){
            flagJatem = true;
            id = extras.getLong("userid");
        }
        porcentagem = extras.getInt("porcentagem");
        TextView tv = (TextView) findViewById(R.id.resultText);
        TextView tv2 = (TextView) findViewById(R.id.resultText2);
        TextView tv3 = (TextView) findViewById(R.id.negoco);


        ImageView ima = (ImageView) findViewById(R.id.imageView);
        TextView poct = (TextView) findViewById(R.id.porce);

        poct.setText(porcentagem+"%");

        if(porcentagem <= 9){
            tv.setTextColor(Color.parseColor("#469F67"));
            tv2.setTextColor(Color.parseColor("#469F67"));
            tv3.setTextColor(Color.parseColor("#469F67"));

            tv.setText(R.string.parabens);
            tv2.setText(R.string.parabens2);
            tv3.setText(R.string.negoco);

            poct.setTextColor(Color.parseColor("#469F67"));
            ima.setImageResource(R.drawable.coracao);
        } else if (porcentagem <= 19){
            tv.setTextColor(Color.parseColor("#ffa800"));
            tv2.setTextColor(Color.parseColor("#ffa800"));
            tv3.setTextColor(Color.parseColor("#ffa800"));
            tv.setText(R.string.atencao);
            tv2.setText(R.string.atencao2);
            tv3.setText(R.string.negoco);

            poct.setTextColor(Color.parseColor("#ffa800"));
            ima.setImageResource(R.drawable.coracao3);
        } else {
            tv.setTextColor(Color.parseColor("#9e4547"));
            tv2.setTextColor(Color.parseColor("#9e4547"));
            tv3.setTextColor(Color.parseColor("#9e4547"));

            tv.setText(R.string.cuidado);
            tv2.setText(R.string.cuidado2);
            tv3.setText(R.string.negoco);

            poct.setTextColor(Color.parseColor("#9e4547"));
            ima.setImageResource(R.drawable.coracao2);
        }
    }

    public void save() {
        if( !flagJatem ){
            Intent a = new Intent(this,ChooseUserActivity.class);
            a.putExtra("porcentagem",porcentagem);
            a.putExtras(getIntent().getExtras());
            startActivityForResult(a,90);
        } else {
            UsersDataSource uds = new UsersDataSource(this);
            uds.open();
            Bundle ae = getIntent().getExtras();
            uds.insertFran(id,(new Integer(porcentagem)).toString(),new Integer(ae.getInt("idade")).toString(),new Integer(ae.getInt("ct")).toString(),new Integer(ae.getInt("hdl")).toString(),new Integer(ae.getInt("pas")).toString(),new Integer(ae.getInt("pad")).toString(),new Integer(ae.getInt("sexo")).toString(),new Integer(ae.getInt("fuma")).toString(),new Integer(ae.getInt("diabetes")).toString());
            uds.close();
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 90){
            if( resultCode == RESULT_OK){
                setResult(RESULT_OK,data);
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                break;
            case R.id.action_conduta:
                conduta();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void conduta() {
        Intent ae = new Intent(getApplicationContext(),CondutaActivity.class);
        ae.putExtras(getIntent().getExtras());
        startActivity(ae);
    }
}
