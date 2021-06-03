package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import com.example.examsitgen.adapters.DepartmentsAdapter;
import com.example.examsitgen.database.Constants;
import com.example.examsitgen.database.DbHelper;

public class AllDepartments extends AppCompatActivity {

    private RecyclerView departmentsRv;

    //db helper
    private DbHelper dbHelper;

    //action bar
    ActionBar actionBar;

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
        setContentView(R.layout.activity_all_departments);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("All Departments");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        departmentsRv = findViewById(R.id.departmentsRv);

        //init db helper class
        dbHelper = new DbHelper(this);

        //load records (default newest first)
        loadDepartments(orderByNewest);
    }

    private void loadDepartments(String orderByNewest) {
        currentOrderByStatus = orderByNewest;
        DepartmentsAdapter adapterItem = new DepartmentsAdapter(AllDepartments.this,
                dbHelper.getAllDepartments(orderByNewest));
        departmentsRv.setAdapter(adapterItem);
    }

    @Override
    public void onResume(){
        super.onResume();
        loadDepartments(currentOrderByStatus); // refresh Item list
    }
}
