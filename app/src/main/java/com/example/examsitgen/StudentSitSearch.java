package com.example.examsitgen;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examsitgen.adapters.StudentsAdapter;
import com.example.examsitgen.database.Constants;
import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.AllocatedSitModel;
import com.example.examsitgen.models.StudentDetailsModel;

import java.util.ArrayList;

public class StudentSitSearch extends AppCompatActivity implements View.OnClickListener {

    ActionBar actionBar;
    private DbHelper dbHelper;
    EditText searchEt;
    Button searchBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sit_search);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Student Portal");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init db helper class
        dbHelper = new DbHelper(this);
        searchEt = findViewById(R.id.searchEt);
        searchBtn = findViewById(R.id.searchBtn);
        progressBar = findViewById(R.id.loading);

        searchBtn.setOnClickListener(this);

    }

    private void loadStudents(String query) {
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<AllocatedSitModel> x = dbHelper.searchItems(query);
        if (x.size()==0){
            //Toast.makeText(this, "No sits allocated to the student ", Toast.LENGTH_LONG).show();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("No sits allocated to the student");
                    alertDialogBuilder.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });

//            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    finish();
//                }
//            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else{
            Constants.loadedSearchItems = x;
            Intent intent2 = new Intent(StudentSitSearch.this, AllAllocatedStudents.class);
            intent2.putExtra("isStudentSearch", "yes"); //want to add new data, set false
            startActivity(intent2);

        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchBtn:
                String xx = searchEt.getText().toString().trim();
                if(xx.equals("")){
                    Toast.makeText(this, "Please fill the field above to proceed", Toast.LENGTH_LONG).show();
                }
                else{
                    loadStudents(xx);
                }
                break;
            case R.id.signIn:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
    }
}