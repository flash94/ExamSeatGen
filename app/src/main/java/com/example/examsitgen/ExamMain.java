package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ExamMain extends AppCompatActivity implements View.OnClickListener{

    ActionBar actionBar;
    private LinearLayout addExam;
    private LinearLayout allExams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_main);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Exam Menu");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initListeners();
    }

    private void initViews(){
        addExam= findViewById(R.id.addExam);
        allExams = findViewById(R.id.allExams);
    }

    private void initListeners(){
        addExam.setOnClickListener(this);
        allExams.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addExam:
                startActivity(new Intent(getApplicationContext(), NewExamForm.class));
                break;
//            case R.id.allHalls:
//                startActivity(new Intent(getApplicationContext(), NewStudentForm.class));
//                break;
        }
    }
}