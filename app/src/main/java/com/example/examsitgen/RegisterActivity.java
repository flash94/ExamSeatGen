package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.UserModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userNameEt, passwordEt, emailEt;
    Button signUp;
    TextView signInText;
    String role, timeStamp;
    int roleId;
   // View progressbar = findViewById(R.id.llProgressBar);

    //db Helper
    private DbHelper dbHelper;

    //userModel
    private UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        initViews();
        initObjects();
        initListeners();
    }

    private void initViews(){
        signInText = findViewById(R.id.signIn);
        signUp = findViewById(R.id.registerBtn);
        userNameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        emailEt= findViewById(R.id.emailEt);

    }

    private void initObjects(){
        dbHelper = new DbHelper(this);
        userModel = new UserModel();
    }


    private void initListeners(){
        signUp.setOnClickListener(this);
        signInText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerBtn:
                showUserTypeDialog();
                break;
            case R.id.signIn:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
        }
    }

    private void registerUser() {
       // progressbar.setVisibility(View.VISIBLE);
        timeStamp = ""+System.currentTimeMillis();


        if(userNameEt.getText().toString().matches("") || passwordEt.getText().toString().matches("") || emailEt.getText().toString().matches("") ){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else {
            if(!dbHelper.checkUserExist(emailEt.getText().toString().trim())){
                userModel.setUserName(userNameEt.getText().toString().trim());
                userModel.setEmail(emailEt.getText().toString().trim());
                userModel.setPassword(passwordEt.getText().toString().trim());
                userModel.setRoleId(roleId);
                userModel.setRole(role.trim());
                userModel.setAddedTimeStamp(timeStamp);

                dbHelper.insertUser(userModel);
               // progressbar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "New" + role + "Registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                emptyInputEditText();
            }
            else{
                Toast.makeText(this, "Registration failed! User already exists", Toast.LENGTH_LONG).show();
            }
        }

    }
    private void emptyInputEditText(){
        userNameEt.setText(null);
        emailEt.setText(null);
        passwordEt.setText(null);
    }

    private void showUserTypeDialog() {
        //options to display in dialog
        String[] options = {"Admin", "Student"};
        //dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //add items to dialog
        builder.setTitle("Register as ");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //handle item clicks
                if (which==0){
                    //Edit is clicked
                    role = "Admin";
                    roleId = 1;
                    registerUser();
                }
                else if(which==1){
                    role = "Student";
                    roleId = 5;
                    //delete is clicked
                    registerUser();

                }
            }
        });
        //show dialog
        builder.create().show();
    }

    private void registerStudent() {
        Toast.makeText(this, "Login student", Toast.LENGTH_LONG).show();
    }
}