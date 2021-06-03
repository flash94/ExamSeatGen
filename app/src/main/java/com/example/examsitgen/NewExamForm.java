package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.DepartmentModel;
import com.example.examsitgen.models.ExamModel;

public class NewExamForm extends AppCompatActivity implements View.OnClickListener{

    private EditText examNameEt,examCodeEt, examDeptEt, examLevelEt;
    Button submit;
    String timeStamp;
    ActionBar actionBar;

    //db Helper
    private DbHelper dbHelper;

    //userModel
    private ExamModel examModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exam_form);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Add Exam");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        examDeptEt = findViewById(R.id.examDeptEt);
        examCodeEt = findViewById(R.id.examCodeEt);
        examDeptEt = findViewById(R.id.examDeptEt);
        examLevelEt = findViewById(R.id.examLevelEt);
        submit= findViewById(R.id.Submit);

    }

    private void initObjects(){
        dbHelper = new DbHelper(this);
        examModel= new ExamModel();
    }


    private void initListeners(){
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Submit:
                createExam();
                break;
        }
    }

    private void createExam() {
        // progressbar.setVisibility(View.VISIBLE);
        timeStamp = ""+System.currentTimeMillis();

        if(examNameEt.getText().toString().matches("") || examCodeEt.getText().toString().matches("") || examDeptEt.getText().toString().matches("") ){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else {
            if(!dbHelper.checkExamExist(examCodeEt.getText().toString().trim())){
                examModel.setCourseTitle(examNameEt.getText().toString().trim());
                examModel.setCourseCode(examCodeEt.getText().toString().trim());
                examModel.setCourseLevel(examLevelEt.getText().toString().trim());
                examModel.setDepartment(examDeptEt.getText().toString().trim());
                examModel.setAddedTime(timeStamp);
                examModel.setUpdatedTime(timeStamp);

                dbHelper.insertExam(examModel);
                // progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "New Exam" + "Added", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                emptyInputEditText();
            }
            else{
                Toast.makeText(this, "Registration failed! User already exists", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void emptyInputEditText() {
        examDeptEt.setText(null);
        examLevelEt.setText(null);
       examCodeEt.setText(null);
        examNameEt.setText(null);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }
}