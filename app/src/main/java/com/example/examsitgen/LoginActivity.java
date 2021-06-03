package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

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
                loginUser();
                break;
            case R.id.signUp:
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
        }

    }

    private void loginUser() {
        if(usernameEt.getText().toString().matches("") || passwordEt.getText().toString().matches("")){
            Toast.makeText(this, "Please fill all fields to proceed", Toast.LENGTH_LONG).show();
        }
        else{
            if(dbHelper.loginUser(usernameEt.getText().toString(),passwordEt.getText().toString())){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Login Failed! Invalid Login credentials", Toast.LENGTH_LONG).show();
            }
        }

    }
}