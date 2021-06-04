package com.example.examsitgen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.examsitgen.adapters.DepartmentsAdapter;
import com.example.examsitgen.adapters.StudentsAdapter;
import com.example.examsitgen.database.Constants;
import com.example.examsitgen.database.DbHelper;

public class AllStudents extends AppCompatActivity {


    private RecyclerView studentsRv;

    //db helper
    private DbHelper dbHelper;

    //action bar
    ActionBar actionBar;

    //sort options
    String orderByNewest = Constants.D_ADDED_TIMESTAMP + " DESC";
//    String orderByOldest = Constants.C_ADDED_TIMESTAMP + " ASC";
//    String orderByTitleAsc = Constants.C_ITEM_NAME + " ASC";
//    String orderByTitleDesc = Constants.C_ITEM_NAME + " DESC";

    //for refreshing items, refresh with last choosen sort
    String currentOrderByStatus = orderByNewest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        //init actionbar
        actionBar = getSupportActionBar();
        //actionbar title
        actionBar.setTitle("All Students");
        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        studentsRv = findViewById(R.id.studentsRv);

        //init db helper class
        dbHelper = new DbHelper(this);

        //load records (default newest first)
        loadStudents(orderByNewest);
    }

    private void loadStudents(String orderByNewest) {
        currentOrderByStatus = orderByNewest;
        StudentsAdapter adapterItem = new StudentsAdapter(AllStudents.this,
                dbHelper.getAllStudents(orderByNewest));
        studentsRv.setAdapter(adapterItem);
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed(); // go back by clicking back button on actionbar
        return super.onNavigateUp();
    }
}