package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class StudentsMain extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    private LinearLayout addStudent;
    private LinearLayout allStudentsEv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_main);
        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Students Menu");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        initViews();
        initListeners();
    }

    private void initViews(){
        addStudent= findViewById(R.id.addStudent);
        allStudentsEv = findViewById(R.id.allStudents);
    }

    private void initListeners(){
        addStudent.setOnClickListener(this);
        allStudentsEv.setOnClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addStudent:
                startActivity(new Intent(getApplicationContext(), NewStudentForm.class));
                break;
            case R.id.allStudents:
                startActivity(new Intent(getApplicationContext(), AllStudents.class));
                break;
        }
    }
}