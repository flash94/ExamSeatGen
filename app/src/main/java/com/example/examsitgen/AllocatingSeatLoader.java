package com.example.examsitgen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.examsitgen.database.DbHelper;
import com.example.examsitgen.models.AllocatedSitModel;
import com.example.examsitgen.models.StudentDetailsModel;
import com.example.kloadingspin.KLoadingSpin;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;

public class AllocatingSeatLoader extends AppCompatActivity {

    String departmentLevel, departmentName, hallCapacity, hallName;
    private DbHelper dbHelper;
    private AllocatedSitModel allocatedSitModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_allocating_seat_loader);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        departmentLevel = intent.getStringExtra("DEPARTMENT_LEVEL");
        departmentName= intent.getStringExtra("DEPARTMENT");
        hallCapacity = intent.getStringExtra("HALL_CAPACITY");
        hallName = intent.getStringExtra("HALL_NAME");
        // For showing
        KLoadingSpin a = findViewById(R.id.KLoadingSpin);
        a.startAnimation();
        a.setIsVisible(true);

        initObjects();

        loadDepartmentStudents();
        // For hiding
//        KLoadingSpin a = findViewById(R.id.KLoadingSpin);
//        a.stopAnimation()
    }

    private  void initObjects(){
        dbHelper = new DbHelper(this);
        allocatedSitModel= new AllocatedSitModel();
    }

    private void loadDepartmentStudents() {
        ArrayList<StudentDetailsModel> x = dbHelper.getDepartmentStudents(departmentName,departmentLevel, hallCapacity);
        if(x.equals(null)){
            Toast.makeText(this, "Sit Allocation failed, Insufficient sits", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AllocatingSeatLoader.this, SitAllocationFailed.class);
            startActivity(intent);
        }
        else if (!x.equals(null)){
            Toast.makeText(this, "Instantiating Formular", Toast.LENGTH_LONG).show();
            int noOfStudents = x.size();
            int hallCap = Integer.parseInt(hallCapacity);

            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> sitNoGenerated = new ArrayList<Integer>();
            for (int i=1; i<= hallCap; i++) {
                list.add(new Integer(i));
            }
            Collections.shuffle(list);
            for (int i=0; i<noOfStudents; i++) {
                System.out.println(list.get(i));
                sitNoGenerated.add(new Integer(list.get(i)));
            }
            int index = 0;
            for (StudentDetailsModel c : x){

                String timeStamp = ""+System.currentTimeMillis();
                allocatedSitModel.setStudentName(c.getStudentName());
                allocatedSitModel.setStudentId(c.getStudentId());
//                studentDetailsModel.setStudentCourse(studentCourseEt.getText().toString().trim());
                allocatedSitModel.setStudentLevel(c.getStudentLevel());
                allocatedSitModel.setStudentDepartment(c.getStudentDepartment());
                allocatedSitModel.setHallName(hallName);
                int u = sitNoGenerated.get(index);
                allocatedSitModel.setSitNumber(String.valueOf(u));
                allocatedSitModel.setAddedTime(timeStamp);
                allocatedSitModel.setUpdatedTime(timeStamp);
                index++;

                if (!dbHelper.checkStudentHaveHall(c.getStudentId())) {
                    dbHelper.insertAllocatedSits(allocatedSitModel);
                }
                else{
                    Toast.makeText(this, "Student already assigned to hall", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(this, "Sit Allocation Completed,", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AllocatingSeatLoader.this, SitAllocationSuccess.class);
                startActivity(intent);

            }
        }

    }


}