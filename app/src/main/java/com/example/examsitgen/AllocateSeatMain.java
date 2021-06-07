package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AllocateSeatMain extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    private LinearLayout allocateSeat;
    private LinearLayout allSeats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_seat_main);
        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Allocate Seats");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initListeners();
    }

    private void initViews(){
        allocateSeat= findViewById(R.id.addSeat);
        allSeats = findViewById(R.id.allSeats);
    }

    private void initListeners(){
        allSeats.setOnClickListener(this);
        allocateSeat.setOnClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addSeat:
                startActivity(new Intent(getApplicationContext(), AllDepartments.class));
                break;

            case R.id.allSeats:
                startActivity(new Intent(getApplicationContext(), AllAllocatedStudents.class));
                break;
        }
    }
}