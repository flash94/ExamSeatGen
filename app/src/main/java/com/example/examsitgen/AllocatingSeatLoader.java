package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kloadingspin.KLoadingSpin;

public class AllocatingSeatLoader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_allocating_seat_loader);
        getSupportActionBar().hide();
        // For showing
        KLoadingSpin a = findViewById(R.id.KLoadingSpin);
        a.startAnimation();
        a.setIsVisible(true);

        // For hiding
//        KLoadingSpin a = findViewById(R.id.KLoadingSpin);
//        a.stopAnimation()
    }


}