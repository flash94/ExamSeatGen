package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.examsitgen.adapters.DepartmentsAdapter;
import com.example.examsitgen.adapters.HallsAdapter;
import com.example.examsitgen.database.Constants;
import com.example.examsitgen.database.DbHelper;

public class AllHalls extends AppCompatActivity {

    private RecyclerView hallsRv;

    //db helper
    private DbHelper dbHelper;

    //action bar
    ActionBar actionBar;

    String   departmentId, departmentName, departmentTotalNo;

    //sort options
    String orderByNewest = Constants.D_ADDED_TIMESTAMP + " DESC";
//    String orderByOldest = Constants.C_ADDED_TIMESTAMP + " ASC";
//    String orderByTitleAsc = Constants.C_ITEM_NAME + " ASC";
//    String orderByTitleDesc = Constants.C_ITEM_NAME + " DESC";

    //for refreshing items, refresh with last choosen sort option
    String currentOrderByStatus = orderByNewest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_halls);

        Intent intent = getIntent();
        departmentId = intent.getStringExtra("DEPARTMENT_ID");
        departmentName= intent.getStringExtra("DEPARTMENT_NAME");
        departmentTotalNo = intent.getStringExtra("DEPARTMENT_STUDENT_NO");

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("All Halls");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        hallsRv = findViewById(R.id.hallsRv);

        //init db helper class
        dbHelper = new DbHelper(this);

        //load records (default newest first)
        loadHalls(orderByNewest);
    }

    private void loadHalls(String orderByNewest) {

        currentOrderByStatus = orderByNewest;
        HallsAdapter adapterItem = new HallsAdapter(AllHalls.this,
                dbHelper.getAllHalls(orderByNewest));
        hallsRv.setAdapter(adapterItem);
    }

    @Override
    public void onResume(){
        super.onResume();
        loadHalls(currentOrderByStatus); // refresh Item list
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onNavigateUp();
    }
}