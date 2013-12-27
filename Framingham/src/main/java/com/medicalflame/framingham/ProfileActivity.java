package com.medicalflame.framingham;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends ActionBarActivity {

    private MyPerformanceArrayAdapter adapter;
    private long userid;
    private String name;
    private List<Fran> frans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UsersDataSource uds = new UsersDataSource(this);
        uds.open();
        //User user = uds.
        name = uds.getUserName(getIntent().getExtras().getLong("id"));
        userid = getIntent().getExtras().getLong("id");
        uds.close();

        final ListView listview = (ListView) findViewById(R.id.listView);

        ArrayList<ItemAdapter> ial = getList();
        adapter = new MyPerformanceArrayAdapter(this, ial,false, name);
        listview.setAdapter(adapter);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent ae = new Intent(getApplicationContext(),ProfileResultActivity.class);
                ae.putExtra("ct",frans.get(position).getCt());
                ae.putExtra("date",frans.get(position).getDate());
                ae.putExtra("diabetes",frans.get(position).getDiabetes());
                ae.putExtra("fran",frans.get(position).getFran());
                ae.putExtra("fuma",frans.get(position).getFuma());
                ae.putExtra("hdl",frans.get(position).getHdl());
                ae.putExtra("idade",frans.get(position).getIdade());
                ae.putExtra("pad",frans.get(position).getPad());
                ae.putExtra("pas",frans.get(position).getPas());
                ae.putExtra("sexo",frans.get(position).getSexo());

                startActivity(ae);
            }
        };
        listview.setOnItemClickListener(mMessageClickedHandler);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name);

    }

    private ArrayList<ItemAdapter> getList() {
        UsersDataSource uds = new UsersDataSource(this);
        uds.open();
        final List<String> dates2 = uds.getDates(userid);
        final List<String> dates = new ArrayList<String>();
        for(String d : dates2){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date ae = dateFormat.parse(d);
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String foi = dateFormat2.format(date);
                dates.add(foi);

            } catch (ParseException e) {
            }

        }
        frans = uds.getAllFrans(userid);
        uds.close();
        ArrayList<ItemAdapter> ial = new ArrayList<ItemAdapter>();
        for (int i = 0; i < dates.size(); ++i) {
            ial.add(new ItemAdapter(dates.get(i), frans.get(i).getFran()));
        }
        return ial;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_new_calc_profile:
                newCalc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void newCalc(){
        Intent a = new Intent(this,NewCalcActivity.class);
        Bundle extras = getIntent().getExtras();
        a.putExtra("userid",userid);
        a.putExtra("name",name);
        this.startActivityForResult(a, 88);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 88){
            if( resultCode == RESULT_OK){
                ArrayList<ItemAdapter> lista = getList();
                adapter.setList(lista);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
