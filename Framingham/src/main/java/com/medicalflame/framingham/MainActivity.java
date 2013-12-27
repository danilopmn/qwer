package com.medicalflame.framingham;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.R.layout.simple_list_item_1;
import static android.R.layout.simple_list_item_single_choice;
import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    protected static ViewPager mViewPager;
    private boolean flagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flagList = true;
        if (savedInstanceState == null) {
       //     getSupportFragmentManager().beginTransaction()
         //           .add(R.id.container, new PlaceholderFragment())
           //         .commit();
        }

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowHomeEnabled(false);
  //      actionBar.setDisplayShowTitleEnabled(false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setTabListener(this).setIcon(mSectionsPagerAdapter.getPageIcon(
                            i)));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mViewPager.getCurrentItem() == 1) { //Back key pressed
            mViewPager.setCurrentItem(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.

        if(tab.getPosition() == 0){
            flagList = true;
        } else {
            flagList = false;
        }
        mViewPager.setCurrentItem(tab.getPosition());

    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.about:
                Intent a = new Intent(this,AboutActivity.class);
                startActivity(a);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0){
                return new PlaceholderFragment();
            } else if ( position == 1 ){
                return new PlaceholderFragment2();
            } else if(position==2){
                return new PlaceholderFragment3();
            } else {
                return new PlaceholderFragment4();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Pacientes".toUpperCase(l);
                case 1:
                    return "Calcular".toUpperCase(l);
                case 2:
                    return "Sobre".toUpperCase(l);
                case 3:
                    return "Sobre2".toUpperCase(l);
            }
            return null;
        }

        public int getPageIcon(int i) {
            switch (i) {
                case 0:
                    return R.drawable.ic_action_group;
                case 1:
                    return R.drawable.ic_coracao;
            }
            return 0;
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {


        private String[] myStringArray = {"Danilo 1", "Danilo 2", "Danilo 3", "Danilo 4", "Danilo 5"};
        private String[] myPctArray = {"10","5","20","9","12"};
        private static ArrayList<ItemAdapter> la;
        private View rootView;
        private MyPerformanceArrayAdapter adapter;
        private List<User> lu;

        @Override
        public void onResume() {
            super.onResume();
            updateListAdapter(rootView.getContext());
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            la = new ArrayList<ItemAdapter>();
            setHasOptionsMenu(true);
            adapter = new MyPerformanceArrayAdapter(rootView.getContext(), la,true);

            updateListAdapter(rootView.getContext());


            ListView listView = (ListView) rootView.findViewById(R.id.listView);
            //TextView tv = (TextView) getLayoutInflater().inflate(R.layout., null);
            //tv.setText("Colesterol");
            //listView.addHeaderView(tv);
            listView.setAdapter(adapter);
            AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent a = new Intent(rootView.getContext(),ProfileActivity.class);
                    a.putExtra("id",lu.get(position).getId());
                    a.putExtra("name",lu.get(position).getUser());
                    startActivity(a);
                }
            };
            listView.setOnItemClickListener(mMessageClickedHandler);
            registerForContextMenu(listView);
            return rootView;
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

        public void updateAdapter(String ae){
            ae = ae.trim();
            ArrayList<ItemAdapter> nl = new ArrayList<ItemAdapter>();
            if(ae == ""){
                nl = la;
            } else {
                for(ItemAdapter ia : la){
                    if(ia.name.toLowerCase().contains(ae.toLowerCase())){
                        nl.add(ia);
                    }
                }
                adapter.setList(nl);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.main, menu);
            MenuItem searchItem = menu.findItem(R.id.searchi);
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    updateAdapter(s);
                    return true;
                }
            });
            super.onCreateOptionsMenu(menu, menuInflater);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,
                                        ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.delete, menu);
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            switch (item.getItemId()) {
                case R.id.action_delete:
                    delete(info.position);
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        }

        private void delete(int position) {
            UsersDataSource uds = new UsersDataSource(rootView.getContext());
            uds.open();
            uds.removeUser(lu.get(position).getId());
            uds.close();
            updateListAdapter(rootView.getContext());
        }
    }


    public static class PlaceholderFragment2 extends Fragment implements AdapterView.OnItemSelectedListener {

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
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            switch (item.getItemId()) {
                case R.id.calc_rick:
                    getResults(rootView);
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

        //@Override
        //public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

            // Inflate the menu; this adds items to the action bar if it is present.
 //           menuInflater.inflate(R.menu.main2, menu);
  //          super.onCreateOptionsMenu(menu, menuInflater);
   //     }

        public void onCheckboxClickedDiabetes(View view){
            boolean checked = ((CheckedTextView) view).isChecked();
            if(checked){
                tratada = 1;
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(requestCode == 91){
                if( resultCode == RESULT_OK){
                    if(data.hasExtra("id")){
                        long id = data.getLongExtra("id",-1);

                        mViewPager.setCurrentItem(0);

                        Intent a = new Intent(rootView.getContext(),ProfileActivity.class);
                        a.putExtra("id",id);
                        startActivity(a);
                    }
                }
            }
        }

        public void onCheckboxClickedFumo(View view){
            boolean checked = ((CheckedTextView) view).isChecked();
            if(checked){
                fumo = 1;
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
            Intent i = new Intent(rootView.getContext(),ResultActivity.class);
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
            i.putExtra("ct",colesterolChoice);
            i.putExtra("hdl",colesterolChoiceHDL);
            i.putExtra("pas",pressureChoice);
            i.putExtra("idade",idadeChoice);
            i.putExtra("sexo",sexo);
            i.putExtra("fumo",fumo);
            i.putExtra("diabetes",tratada);
            startActivityForResult(i,91);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        private View rootView;

        @Override
        public void onResume() {
            super.onResume();
        }

        public PlaceholderFragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.another_layout, container, false);
            setHasOptionsMenu(true);
            Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerColesterol);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.spinner_item,myColesterolStringArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            spinner = (Spinner) rootView.findViewById(R.id.spinnerColesterolHDL);
            adapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.spinner_item,myColesterolHDLStringArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            spinner = (Spinner) rootView.findViewById(R.id.spinnerPressao);
            adapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.spinner_item,myPressurePASStringArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            spinner = (Spinner) rootView.findViewById(R.id.spinnerIdade);
            adapter = new ArrayAdapter<String>(rootView.getContext(),R.layout.spinner_item,myStringArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);

            CompoundButton cbt = (CompoundButton) rootView.findViewById(R.id.sexoOp);
            cbt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) sexo = 1;
                    else sexo = 0;
                }
            });

            CheckedTextView cb = (CheckedTextView) rootView.findViewById(R.id.trata);
            final CheckedTextView finalCb = cb;
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalCb.setChecked(!finalCb.isChecked());
                    onCheckboxClickedDiabetes(v);
                }
            });

            cb = (CheckedTextView) rootView.findViewById(R.id.fumo);
            final CheckedTextView finalCb2 = cb;
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalCb2.setChecked(!finalCb2.isChecked());
                    onCheckboxClickedFumo(v);
                }
            });

            ((Button)rootView.findViewById(R.id.buttonRisco)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getResults(rootView);
                }
            });

            return rootView;
        }
    }

    public static class PlaceholderFragment3 extends Fragment {


        private View rootView;

        @Override
        public void onResume() {
            super.onResume();
        }

        public PlaceholderFragment3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.about_layout, container, false);
            return rootView;
        }
    }

    public static class PlaceholderFragment4 extends Fragment {


        private View rootView;

        @Override
        public void onResume() {
            super.onResume();
        }

        public PlaceholderFragment4() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.about_layout, container, false);
            return rootView;
        }
    }
}
