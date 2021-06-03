package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.ExamModel;
import com.example.examsitgen.models.HallDetailsModel;
import com.example.examsitgen.models.StudentDetailsModel;

public class NewHallForm extends AppCompatActivity implements View.OnClickListener{

    ActionBar actionBar;

    private EditText hallNameEt, hallCapacityEt;
    Button submit;
    String timeStamp;

    //db Helper
    private DbHelper dbHelper;

    //userModel
    private HallDetailsModel hallDetailsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hall_form);
        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("Add Hall");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        initListeners();
        initViews();
        initObjects();
    }

    private void initViews(){
        hallNameEt = findViewById(R.id.hallNameEt);
        hallCapacityEt = findViewById(R.id.hallCapacityEt);
        submit= findViewById(R.id.Submit);

    }

    private void initObjects(){
        dbHelper = new DbHelper(this);
        hallDetailsModel = new HallDetailsModel();
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
                createHall();
                break;
        }
    }

    private void createHall() {
        // progressbar.setVisibility(View.VISIBLE);
        timeStamp = ""+System.currentTimeMillis();

        if(hallNameEt.getText().toString().matches("") || hallCapacityEt.getText().toString().matches("")  ){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else {
            if(!dbHelper.checkHallExist(hallNameEt.getText().toString().trim())){
                hallDetailsModel.setHallName(hallNameEt.getText().toString().trim());
                hallDetailsModel.setHallCapacity(hallCapacityEt.getText().toString().trim());

                hallDetailsModel.setAddedTime(timeStamp);
                hallDetailsModel.setUpdatedTime(timeStamp);

                dbHelper.insertHall(hallDetailsModel);
                // progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "New Hall" + "Added", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                emptyInputEditText();
            }
            else{
                Toast.makeText(this, "Registration failed! User already exists", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void emptyInputEditText() {
        hallNameEt.setText(null);
        hallCapacityEt.setText(null);
    }
}