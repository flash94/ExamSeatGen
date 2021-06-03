package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HallMain extends AppCompatActivity implements View.OnClickListener{

    ActionBar actionBar;
    private LinearLayout addHall;
    private LinearLayout allHalls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_main);
        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Hall Menu");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initListeners();
    }

    private void initViews(){
        addHall= findViewById(R.id.addHall);
        allHalls = findViewById(R.id.allHalls);
    }

    private void initListeners(){
        addHall.setOnClickListener(this);
        allHalls.setOnClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addHall:
                startActivity(new Intent(getApplicationContext(), NewHallForm.class));
                break;
//            case R.id.allHalls:
//                startActivity(new Intent(getApplicationContext(), NewStudentForm.class));
//                break;
        }
    }
}