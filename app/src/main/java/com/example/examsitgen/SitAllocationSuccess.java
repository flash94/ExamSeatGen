package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SitAllocationSuccess extends AppCompatActivity {

    Button allocatedSits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sit_allocation_success);

        allocatedSits = findViewById(R.id.backToHome);

        allocatedSits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SitAllocationSuccess.this, AllAllocatedStudents.class);
                startActivity(intent);
                finish();
            }
        });
    }
}