package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.examsitgen.adapters.AllocatedSitsAdapter;
import com.example.examsitgen.adapters.DepartmentsAdapter;
import com.example.examsitgen.database.Constants;
import com.example.examsitgen.database.DbHelper;

public class AllAllocatedStudents extends AppCompatActivity {

    private RecyclerView allocatedStudentsRv;

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
        setContentView(R.layout.activity_all_allocated_students);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("All Allocated Students");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        allocatedStudentsRv = findViewById(R.id.allocatedStudentsRv);

        //init db helper class
        dbHelper = new DbHelper(this);

        //load records (default newest first)
        loadAllocatedStudents(orderByNewest);
    }

    private void loadAllocatedStudents(String orderByNewest) {
        currentOrderByStatus = orderByNewest;
        AllocatedSitsAdapter adapterItem = new AllocatedSitsAdapter(AllAllocatedStudents.this,
                dbHelper.getAllAllocatedStudents(orderByNewest));
        allocatedStudentsRv.setAdapter(adapterItem);

    }

    @Override
    public void onResume(){
        super.onResume();
        loadAllocatedStudents(currentOrderByStatus); // refresh Item list
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onNavigateUp();
    }
}