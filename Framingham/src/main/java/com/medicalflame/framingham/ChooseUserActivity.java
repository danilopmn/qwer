package com.medicalflame.framingham;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;
import static android.R.layout.simple_list_item_single_choice;
import static android.widget.Toast.LENGTH_SHORT;

public class ChooseUserActivity extends ActionBarActivity {

    private ArrayList<ItemAdapter> la;
    private MyPerformanceArrayAdapter adapter;
    private int porcentagem;
    private List<User> lu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user_layout);
        Bundle extras = getIntent().getExtras();
        porcentagem = extras.getInt("porcentagem");

        la = new ArrayList<ItemAdapter>();

        adapter = new MyPerformanceArrayAdapter(this, la,true);
        updateListAdapter(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        //TextView tv = (TextView) getLayoutInflater().inflate(R.layout., null);
        //tv.setText("Colesterol");
        //listView.addHeaderView(tv);
        listView.setAdapter(adapter);
        final Context ae = this;
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                long userId = lu.get(position).getId();
                UsersDataSource uds = new UsersDataSource(ae);
                uds.open();
                Bundle ae = getIntent().getExtras();
                uds.insertFran(userId,(new Integer(porcentagem)).toString(),new Integer(ae.getInt("idade")).toString(),new Integer(ae.getInt("ct")).toString(),new Integer(ae.getInt("hdl")).toString(),new Integer(ae.getInt("pas")).toString(),new Integer(ae.getInt("pad")).toString(),new Integer(ae.getInt("sexo")).toString(),new Integer(ae.getInt("fuma")).toString(),new Integer(ae.getInt("diabetes")).toString());
                uds.close();

                back(userId);
            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    public void updateListAdapter(Context context) {
        la.clear();
        UsersDataSource uds = new UsersDataSource(context);
        uds.open();
        lu = uds.getAllUsers();


        for(int i = 0; i < lu.size(); i++){
            la.add(new ItemAdapter(lu.get(i).getUser(),lu.get(i).getFran()));
        }
        adapter.setList(la);
        adapter.notifyDataSetChanged();
        uds.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_new_calc:
                newCalc();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void newCalc(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(R.string.novo_paciente);
        alert.setMessage(R.string.nome_do_paciente);

// Set an EditText view to get user input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        int maxLength = 20;
        input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        alert.setView(input);

        alert.setPositiveButton(R.string.salvar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                UsersDataSource uds = new UsersDataSource(getApplicationContext());
                uds.open();
                Bundle ae = getIntent().getExtras();
                long id = uds.createUser(value,""+porcentagem,new Integer(ae.getInt("idade")).toString(),new Integer(ae.getInt("ct")).toString(),new Integer(ae.getInt("hdl")).toString(),new Integer(ae.getInt("pas")).toString(),new Integer(ae.getInt("pad")).toString(),new Integer(ae.getInt("sexo")).toString(),new Integer(ae.getInt("fuma")).toString(),new Integer(ae.getInt("diabetes")).toString()).getId();
                uds.close();
                back(id);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
        //Intent a = new Intent(this,NewPacientActivity.class);
        //a.putExtra("porcentagem",porcentagem);
        //this.startActivityForResult(a, 92);
    }

    private void back(long id){
        Intent data = new Intent();
        data.putExtra("id",id);
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 92){
            if( resultCode == RESULT_OK){
                setResult(RESULT_OK);
                finish();
            }
        }
    }
}
