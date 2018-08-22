package com.vimalcvs.colledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ComputerScience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
