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
import com.example.examsitgen.models.HallDetailsModel;

public class NewDeptForm extends AppCompatActivity implements View.OnClickListener {

    private EditText deptNameEt, deptLevelEt, deptNumberEt;
    Button submit;
    String timeStamp;
    ActionBar actionBar;

    //db Helper
    private DbHelper dbHelper;

    //userModel
    private DepartmentModel departmentModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dept_form);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Add Department");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        deptNameEt = findViewById(R.id.deptNameEt);
        submit= findViewById(R.id.Submit);
        deptLevelEt = findViewById(R.id.deptLevelEt);
        deptNumberEt = findViewById(R.id.deptCapacityEt);

    }

    private void initObjects(){
        dbHelper = new DbHelper(this);
        departmentModel = new DepartmentModel();
    }


    private void initListeners(){
        submit.setOnClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onSupportNavigateUp();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Submit:
                createDepartment();
                break;
        }
    }

    private void createDepartment() {
        // progressbar.setVisibility(View.VISIBLE);
        timeStamp = ""+System.currentTimeMillis();

        if(deptNameEt.getText().toString().matches("") ){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else {
            if(!dbHelper.checkDeptExist(deptNameEt.getText().toString().trim())){
                departmentModel.setDepartment(deptNameEt.getText().toString().trim());
                departmentModel.setAddedTime(timeStamp);
                departmentModel.setUpdatedTime(timeStamp);
                departmentModel.setLevel(deptLevelEt.getText().toString().trim());
                departmentModel.setStudentNo(deptNumberEt.getText().toString().trim());

                dbHelper.insertDepartment(departmentModel);
                // progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "New Department" + "Added", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                emptyInputEditText();
            }
            else{
                Toast.makeText(this, "Registration failed! User already exists", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void emptyInputEditText() {
        deptNameEt.setText(null);
        deptNumberEt.setText(null);
        deptLevelEt.setText(null);
    }
}