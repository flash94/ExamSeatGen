package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.StudentDetailsModel;
import com.example.examsitgen.models.UserModel;

public class NewStudentForm extends AppCompatActivity implements View.OnClickListener {

    private EditText studentNameEt, studentIdEt, studentDeptEt, studentCourseEt, studentLevelEt;
    Button submit;
    String timeStamp;

    //db Helper
    private DbHelper dbHelper;
    ActionBar actionBar;

    //userModel
    private StudentDetailsModel studentDetailsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student_form);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Exam Menu");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initObjects();
        initListeners();
    }


    private void initViews(){
        studentNameEt = findViewById(R.id.studentNameEt);
        studentIdEt = findViewById(R.id.studentIdEt);
        studentDeptEt = findViewById(R.id.studentDeptEt);
        studentCourseEt = findViewById(R.id.studentCourseEt);
        studentLevelEt= findViewById(R.id.studentLevelEt);
        submit= findViewById(R.id.Submit);

    }

    private void initObjects(){
        dbHelper = new DbHelper(this);
        studentDetailsModel = new StudentDetailsModel();
    }


    private void initListeners(){
        submit.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Submit:
                registerStudent();
                break;
        }
    }

    private void registerStudent() {
        // progressbar.setVisibility(View.VISIBLE);
        timeStamp = ""+System.currentTimeMillis();

        if(studentNameEt.getText().toString().matches("") || studentIdEt.getText().toString().matches("") || studentLevelEt.getText().toString().matches("") ){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else {
            if(!dbHelper.checkStudentExist(studentIdEt.getText().toString().trim())){
                studentDetailsModel.setStudentName(studentNameEt.getText().toString().trim());
                studentDetailsModel.setStudentId(studentIdEt.getText().toString().trim());
                studentDetailsModel.setStudentCourse(studentCourseEt.getText().toString().trim());
                studentDetailsModel.setStudentLevel(studentLevelEt.getText().toString().trim());
                studentDetailsModel.setStudentDepartment(studentDeptEt.getText().toString().trim());
                studentDetailsModel.setAddedTime(timeStamp);
                studentDetailsModel.setUpdatedTime(timeStamp);

                dbHelper.insertStudent(studentDetailsModel);
                // progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "New Student" + "Registered", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                emptyInputEditText();
            }
            else{
                Toast.makeText(this, "Registration failed! User already exists", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void emptyInputEditText() {
        studentNameEt.setText(null);
        studentIdEt.setText(null);
        studentCourseEt.setText(null);
        studentLevelEt.setText(null);
        studentDeptEt.setText(null);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }
}