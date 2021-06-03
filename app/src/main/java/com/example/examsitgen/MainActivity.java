 package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private CardView studentCard;
     private CardView examHallCard;
     private CardView examCard;
     private CardView allocateSeatsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }

     private void initViews(){
         studentCard = findViewById(R.id.student_card);
         examHallCard = findViewById(R.id.exam_hall_card);
         examCard = findViewById(R.id.exam_card);
         allocateSeatsCard = findViewById(R.id.allocate_seat_card);
     }

     private void initListeners(){
         studentCard.setOnClickListener(this);
         examHallCard.setOnClickListener(this);
         examCard.setOnClickListener(this);
         allocateSeatsCard.setOnClickListener(this);
     }
     @Override
     public void onClick(View v) {
         switch (v.getId()){
             case R.id.student_card:
                 startActivity(new Intent(getApplicationContext(), StudentsMain.class));
                 break;
             case R.id.exam_hall_card:
                 startActivity(new Intent(getApplicationContext(), HallMain.class));
                 break;
             case R.id.exam_card:
                 startActivity(new Intent(getApplicationContext(), AddDepartmentsMain.class));
                 break;
             case R.id.allocate_seat_card:
                 startActivity(new Intent(getApplicationContext(), AllocateSeatMain.class));
                 break;
         }
     }
 }