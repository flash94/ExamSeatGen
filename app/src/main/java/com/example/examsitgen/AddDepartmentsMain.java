package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AddDepartmentsMain extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    private LinearLayout addDepartment;
    private LinearLayout allDepartments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_departments_main);
        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Department Menu");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initListeners();
    }

    private void initViews(){
        addDepartment= findViewById(R.id.addDepartment);
        allDepartments = findViewById(R.id.allDepartments);
    }

    private void initListeners(){
        addDepartment.setOnClickListener(this);
        allDepartments.setOnClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addDepartment:
                startActivity(new Intent(getApplicationContext(), NewDeptForm.class));
                break;
            case R.id.allDepartments:
                startActivity(new Intent(getApplicationContext(), AllDepartments.class));
                break;
        }
    }
}