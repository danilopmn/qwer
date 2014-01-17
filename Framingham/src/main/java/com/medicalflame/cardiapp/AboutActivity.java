package com.medicalflame.cardiapp;

import android.app.Activity;
import android.os.Bundle;

import com.medicalflame.cardiapp.R;

import java.util.List;

public class AboutActivity extends Activity {

    private MyPerformanceArrayAdapter adapter;
    private long userid;
    private String name;
    private List<Fran> frans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

    }

}
