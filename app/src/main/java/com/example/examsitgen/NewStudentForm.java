package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examsitgen.database.Constants;
import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.DepartmentModel;
import com.example.examsitgen.models.StudentDetailsModel;
import com.example.examsitgen.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class NewStudentForm extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText studentNameEt, studentIdEt, studentDeptEt, studentCourseEt, studentLevelEt;
    Button submit;
    String timeStamp, studentDepartment, studentLevel;
    // Spinner element
    Spinner spinner, spinner2;

    //db Helper
    private DbHelper dbHelper;
    ActionBar actionBar;

    //sort options
    String orderByNewest = Constants.D_ADDED_TIMESTAMP + " DESC";
    //for refreshing items, refresh with last choosen sort option
    String currentOrderByStatus = orderByNewest;

    //userModel
    private StudentDetailsModel studentDetailsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student_form);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Add Student");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initObjects();
        initListeners();


        // Loading spinner data from database
        loadSpinnerData();
        loadLevelSpinnerData();
    }

    private void loadLevelSpinnerData() {
        List<String> levels = new ArrayList<String>();
        levels.add("100");
        levels.add("200");
        levels.add("300");
        levels.add("400");
        levels.add("500");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, levels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter);
    }

    private void loadSpinnerData() {
        List<String> departments = new ArrayList<String>();
        ArrayList<DepartmentModel> x = dbHelper.getAllDepartments(orderByNewest);
        for (DepartmentModel d : x) {
            departments.add(d.getDepartment());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, departments);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }




    private void initViews(){
        // Spinner element
        spinner = (Spinner) findViewById(R.id.codeSpinner);
        spinner2 = (Spinner) findViewById(R.id.levelSpinner);
        studentNameEt = findViewById(R.id.studentNameEt);
        studentIdEt = findViewById(R.id.studentIdEt);
        //studentDeptEt = findViewById(R.id.studentDeptEt);
       // studentCourseEt = findViewById(R.id.studentCourseEt);
        //studentLevelEt= findViewById(R.id.studentLevelEt);
        submit= findViewById(R.id.Submit);

    }

    private void initObjects(){
        dbHelper = new DbHelper(this);
        studentDetailsModel = new StudentDetailsModel();
    }


    private void initListeners(){
        submit.setOnClickListener(this);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(new LevelsSpinnerClass());
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

        if(studentNameEt.getText().toString().matches("") || studentIdEt.getText().toString().matches("") || studentLevel.matches("") ){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else {
            if(!dbHelper.checkStudentExist(studentIdEt.getText().toString().trim())){
                studentDetailsModel.setStudentName(studentNameEt.getText().toString().trim());
                studentDetailsModel.setStudentId(studentIdEt.getText().toString().trim());
//                studentDetailsModel.setStudentCourse(studentCourseEt.getText().toString().trim());
                studentDetailsModel.setStudentLevel(studentLevel);
                studentDetailsModel.setStudentDepartment(studentDepartment);
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
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        studentDepartment = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    class LevelsSpinnerClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            studentLevel = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}