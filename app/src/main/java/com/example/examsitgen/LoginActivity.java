package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.UserModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signUpText;
    private EditText usernameEt, passwordEt;
    Button login;
    String role;


    //db helper
    private DbHelper dbHelper;

    //user model
    private UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initObject();
        initListeners();
    }

    private void initViews() {

        signUpText = findViewById(R.id.signUp);
        login = findViewById(R.id.loginBtn);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
    }

    private void initObject() {
        dbHelper = new DbHelper(this);
        userModel = new UserModel();
    }

    private void initListeners(){
        login.setOnClickListener(this);
        signUpText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loginBtn:
                showUserTypeDialog();
                break;
            case R.id.signUp:
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
        }

    }

    private void loginAdmin() {
        if(usernameEt.getText().toString().matches("") || passwordEt.getText().toString().matches("")){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else{
            if(dbHelper.loginUser(usernameEt.getText().toString(),passwordEt.getText().toString(),role)){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Login Failed! Invalid Login credentials", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void showUserTypeDialog() {
        //options to display in dialog
        String[] options = {"Admin", "Student"};
        //dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //add items to dialog
        builder.setTitle("Select your profile type");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //handle item clicks
                if (which==0){
                    //Edit is clicked
                    role = "Admin";
                    loginAdmin();
                }
                else if(which==1){
                    //delete is clicked
                    role = "Student";
                    loginStudent();

                }
            }
        });
        //show dialog
        builder.create().show();
    }

    private void loginStudent() {
        if(usernameEt.getText().toString().matches("") || passwordEt.getText().toString().matches("")){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else{
            if(dbHelper.loginUser(usernameEt.getText().toString(),passwordEt.getText().toString(), role)){
                Intent intent = new Intent(this, StudentSitSearch.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Login Failed! Invalid Login credentials", Toast.LENGTH_LONG).show();
            }
        }
    }
}