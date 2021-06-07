package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SitAllocationFailed extends AppCompatActivity {

    Button backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_allocation_failed);

        backToHome = findViewById(R.id.backToHome);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SitAllocationFailed.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}