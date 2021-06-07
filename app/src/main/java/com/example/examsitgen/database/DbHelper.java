package com.example.examsitgen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.examsitgen.models.AllocatedSitModel;
import com.example.examsitgen.models.DepartmentModel;
import com.example.examsitgen.models.ExamModel;
import com.example.examsitgen.models.HallDetailsModel;
import com.example.examsitgen.models.StudentDetailsModel;
import com.example.examsitgen.models.UserModel;

import java.util.ArrayList;

import static com.example.examsitgen.database.Constants.ALLOCATED_SITS_TABLE;
import static com.example.examsitgen.database.Constants.A_ID;
import static com.example.examsitgen.database.Constants.A_STUDENT_ID;
import static com.example.examsitgen.database.Constants.A_STUDENT_NAME;
import static com.example.examsitgen.database.Constants.DEPARTMENT_TABLE;
import static com.example.examsitgen.database.Constants.D_DEPT_LEVEL;
import static com.example.examsitgen.database.Constants.D_DEPT_NAME;
import static com.example.examsitgen.database.Constants.D_DEPT_STUDENT_NO;
import static com.example.examsitgen.database.Constants.D_ID;
import static com.example.examsitgen.database.Constants.EXAMS_TABLE;
import static com.example.examsitgen.database.Constants.E_COURSE_CODE;
import static com.example.examsitgen.database.Constants.E_COURSE_DEPARTMENT;
import static com.example.examsitgen.database.Constants.E_COURSE_LEVEL;
import static com.example.examsitgen.database.Constants.E_COURSE_TITLE;
import static com.example.examsitgen.database.Constants.E_ID;
import static com.example.examsitgen.database.Constants.HALLS_TABLE;
import static com.example.examsitgen.database.Constants.H_HALL_CAPACITY;
import static com.example.examsitgen.database.Constants.H_HALL_NAME;
import static com.example.examsitgen.database.Constants.H_ID;
import static com.example.examsitgen.database.Constants.STUDENTS_TABLE;
import static com.example.examsitgen.database.Constants.S_ID;
import static com.example.examsitgen.database.Constants.S_STUDENT_COURSE;
import static com.example.examsitgen.database.Constants.S_STUDENT_DEPT;
import static com.example.examsitgen.database.Constants.S_STUDENT_ID;
import static com.example.examsitgen.database.Constants.S_STUDENT_LEVEL;
import static com.example.examsitgen.database.Constants.S_STUDENT_NAME;
import static com.example.examsitgen.database.Constants.USERS_TABLE;
import static com.example.examsitgen.database.Constants.U_EMAIL;
import static com.example.examsitgen.database.Constants.U_ID;
import static com.example.examsitgen.database.Constants.U_PASSWORD;
import static com.example.examsitgen.database.Constants.U_USERNAME;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table on that db
        db.execSQL(Constants.CREATE_USERS_TABLE);
        db.execSQL(Constants.CREATE_STUDENTS_TABLE);
        db.execSQL(Constants.CREATE_EXAMS_TABLE);
        db.execSQL(Constants.CREATE_HALLS_TABLE);
        db.execSQL(Constants.CREATE_ALLOCATED_SITS_TABLE);
        db.execSQL(Constants.CREATE_DEPARTMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // upgrade database (if there is any structure change, change db version)

        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.STUDENTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.EXAMS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.HALLS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.ALLOCATED_SITS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DEPARTMENT_TABLE);
        //create table again
        onCreate(db);

    }

    //register new user
    public long insertUser(UserModel userModel){
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //id will be inserted and autoincrement authomatically

        //insert data
        values.put(U_EMAIL, userModel.getEmail());
        values.put(U_USERNAME, userModel.getUserName());
        values.put(U_PASSWORD, userModel.getPassword());
        values.put(Constants.U_ROLE, userModel.getRole());
        values.put(Constants.U_ROLEID, userModel.getRoleId());
        values.put(Constants.U_ADDED_TIMESTAMP, userModel.getAddedTimeStamp());

        //insert row to db and return row id of saved record
        long id = db.insert(Constants.USERS_TABLE, null, values);
        //close db connection
        db.close();
        //return id of inserted row
        return id;

    }

    public boolean checkUserExist(String email){
        String[] column = {U_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = U_EMAIL + " = ?";
        String[] selectionArgs= {email,};

        Cursor cursor = db.query(USERS_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    public boolean loginUser(String userName, String password){
        String[] column = {U_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = U_USERNAME + " = ?" + " AND " + U_PASSWORD + " = ?";
        String[] selectionArgs= {userName,password};

        Cursor cursor = db.query(USERS_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    //create new student
    //register new user
    public long insertStudent(StudentDetailsModel studentDetailsModel){
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //id will be inserted and autoincrement authomatically

        //insert data
        values.put(S_STUDENT_NAME, studentDetailsModel.getStudentName());
        values.put(S_STUDENT_ID, studentDetailsModel.getStudentId());
        values.put(S_STUDENT_LEVEL, studentDetailsModel.getStudentLevel());
        values.put(S_STUDENT_DEPT, studentDetailsModel.getStudentDepartment());
        values.put(S_STUDENT_COURSE, studentDetailsModel.getStudentCourse());
        values.put(Constants.S_ADDED_TIMESTAMP, studentDetailsModel.getAddedTime());
        values.put(Constants.S_UPDATED_TIMESTAMP, studentDetailsModel.getUpdatedTime());

        //insert row to db and return row id of saved record
        long id = db.insert(Constants.STUDENTS_TABLE, null, values);
        //close db connection
        db.close();
        //return id of inserted row
        return id;

    }
    public boolean checkStudentExist(String id){
        String[] column = {S_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = S_STUDENT_ID + " = ?";
        String[] selectionArgs= {id,};

        Cursor cursor = db.query(STUDENTS_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    //create new Exam
    //register new exam
    public long insertExam(ExamModel examModel){
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //id will be inserted and autoincrement authomatically

        //insert data
        values.put(E_COURSE_TITLE, examModel.getCourseTitle());
        values.put(E_COURSE_CODE, examModel.getCourseCode());
        values.put(E_COURSE_LEVEL, examModel.getCourseLevel());
        values.put(E_COURSE_DEPARTMENT, examModel.getDepartment());
        values.put(Constants.E_ADDED_TIMESTAMP,examModel.getAddedTime());
        values.put(Constants.E_UPDATED_TIMESTAMP, examModel.getUpdatedTime());

        //insert row to db and return row id of saved record
        long id = db.insert(Constants.EXAMS_TABLE, null, values);
        //close db connection
        db.close();
        //return id of inserted row
        return id;

    }
    public boolean checkExamExist(String courseCode){
        String[] column = {E_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = E_COURSE_CODE+ " = ?";
        String[] selectionArgs= {courseCode,};

        Cursor cursor = db.query(EXAMS_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    //create new Hall
    //register new hall
    public long insertHall(HallDetailsModel hallDetailsModel){
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //id will be inserted and autoincrement authomatically

        //insert data
        values.put(H_HALL_NAME, hallDetailsModel.getHallName());
        values.put(H_HALL_CAPACITY, hallDetailsModel.getHallCapacity());
        values.put(Constants.E_ADDED_TIMESTAMP,hallDetailsModel.getAddedTime());
        values.put(Constants.E_UPDATED_TIMESTAMP, hallDetailsModel.getUpdatedTime());

        //insert row to db and return row id of saved record
        long id = db.insert(Constants.HALLS_TABLE, null, values);
        //close db connection
        db.close();
        //return id of inserted row
        return id;

    }
    public boolean checkHallExist(String hallName){
        String[] column = {H_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = H_HALL_NAME+ " = ?";
        String[] selectionArgs= {hallName,};

        Cursor cursor = db.query(HALLS_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    //create new DEPARTMENT
    //register new hall
    public long insertDepartment(DepartmentModel departmentModel){
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //id will be inserted and autoincrement authomatically

        //insert data
        values.put(D_DEPT_NAME, departmentModel.getDepartment());
        values.put(D_DEPT_LEVEL, departmentModel.getLevel());
        values.put(D_DEPT_STUDENT_NO, departmentModel.getStudentNo());
        values.put(Constants.D_ADDED_TIMESTAMP,departmentModel.getAddedTime());
        values.put(Constants.D_UPDATED_TIMESTAMP, departmentModel.getUpdatedTime());

        //insert row to db and return row id of saved record
        long id = db.insert(Constants.DEPARTMENT_TABLE, null, values);
        //close db connection
        db.close();
        //return id of inserted row
        return id;

    }
    public boolean checkDeptExist(String department){
        String[] column = {D_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = D_DEPT_NAME+ " = ?";
        String[] selectionArgs= {department,};

        Cursor cursor = db.query(DEPARTMENT_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    //get all departments from sqlite database
    public ArrayList<DepartmentModel> getAllDepartments(String orderBy){
        //orderby query will allow to sort data e.g newest/oldest first, name ascending/descending
        //it will return list of items since we have used return type ArrayList<ModelItems>

        ArrayList<DepartmentModel> departmentsList = new ArrayList<>();
        //query to select records
        String selectQuery = "SELECT * FROM " + DEPARTMENT_TABLE + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if(cursor.moveToFirst()){
            do{
                DepartmentModel departmentModel = new DepartmentModel();
                    departmentModel.setId(cursor.getString(cursor.getColumnIndex(D_ID)));
                    departmentModel.setDepartment(cursor.getString(cursor.getColumnIndex(D_DEPT_NAME)));
                    departmentModel.setLevel(cursor.getString(cursor.getColumnIndex(D_DEPT_LEVEL)));
                    departmentModel.setStudentNo(cursor.getString(cursor.getColumnIndex(D_DEPT_STUDENT_NO)));
                    departmentModel.setAddedTime(""+cursor.getString(cursor.getColumnIndex(Constants.D_ADDED_TIMESTAMP)));
                    departmentModel.setUpdatedTime(""+cursor.getString(cursor.getColumnIndex(Constants.D_UPDATED_TIMESTAMP)));

                    //add record to list
                    departmentsList.add(departmentModel);
                }while (cursor.moveToNext());
        }
        //close db connection
        db.close();

        //return the list
        return departmentsList;
    }

    //get all students from sqlite database
    public ArrayList<StudentDetailsModel> getAllStudents(String orderBy){
        //orderby query will allow to sort data e.g newest/oldest first, name ascending/descending
        //it will return list of items since we have used return type ArrayList<ModelItems>

        ArrayList<StudentDetailsModel> studentsList = new ArrayList<>();
        //query to select records
        String selectQuery = "SELECT * FROM " + STUDENTS_TABLE + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if(cursor.moveToFirst()){
            do{
                StudentDetailsModel studentDetailsModel = new StudentDetailsModel();
                studentDetailsModel.setId(cursor.getString(cursor.getColumnIndex(S_ID)));
                studentDetailsModel.setStudentName(cursor.getString(cursor.getColumnIndex(S_STUDENT_NAME)));
                studentDetailsModel.setStudentId(cursor.getString(cursor.getColumnIndex(S_STUDENT_ID)));
                studentDetailsModel.setStudentLevel(cursor.getString(cursor.getColumnIndex(S_STUDENT_LEVEL)));
                studentDetailsModel.setStudentDepartment(cursor.getString(cursor.getColumnIndex(S_STUDENT_DEPT)));
                studentDetailsModel.setStudentCourse(cursor.getString(cursor.getColumnIndex(S_STUDENT_COURSE)));
                studentDetailsModel.setAddedTime(""+cursor.getString(cursor.getColumnIndex(Constants.S_ADDED_TIMESTAMP)));
                studentDetailsModel.setUpdatedTime(""+cursor.getString(cursor.getColumnIndex(Constants.S_UPDATED_TIMESTAMP)));

                //add record to list
                studentsList.add(studentDetailsModel);
            }while (cursor.moveToNext());
        }
        //close db connection
        db.close();

        //return the list
        return studentsList;
    }

    //get all students from sqlite database
    public ArrayList<HallDetailsModel> getAllHalls(String orderBy){
        //orderby query will allow to sort data e.g newest/oldest first, name ascending/descending
        //it will return list of items since we have used return type ArrayList<ModelItems>

        ArrayList<HallDetailsModel> hallsList = new ArrayList<>();
        //query to select records
        String selectQuery = "SELECT * FROM " + HALLS_TABLE + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if(cursor.moveToFirst()){
            do{
                HallDetailsModel hallDetailsModel = new HallDetailsModel();
                hallDetailsModel.setHallName(cursor.getString(cursor.getColumnIndex(H_HALL_NAME)));
                hallDetailsModel.setHallCapacity(cursor.getString(cursor.getColumnIndex(H_HALL_CAPACITY)));
                hallDetailsModel.setId(cursor.getString(cursor.getColumnIndex(H_ID)));
                hallDetailsModel.setAddedTime(""+cursor.getString(cursor.getColumnIndex(Constants.S_ADDED_TIMESTAMP)));
                hallDetailsModel.setUpdatedTime(""+cursor.getString(cursor.getColumnIndex(Constants.S_UPDATED_TIMESTAMP)));

                //add record to list
                hallsList.add(hallDetailsModel);
            }while (cursor.moveToNext());
        }
        //close db connection
        db.close();

        //return the list
        return hallsList;
    }

    //get students department
    public ArrayList<StudentDetailsModel> getDepartmentStudents(String department, String level, String hallCapacity){

        ArrayList<StudentDetailsModel>studentsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //query to select records
        String selectQuery = "SELECT * FROM " + STUDENTS_TABLE + " WHERE " + S_STUDENT_DEPT + " =" + "\""+department+"\"" + " AND " + S_STUDENT_LEVEL + " =" + "\""+level+"\"";

        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if(cursor.moveToFirst()){
            do{
                StudentDetailsModel studentDetailsModel = new StudentDetailsModel();
                studentDetailsModel.setId(cursor.getString(cursor.getColumnIndex(S_ID)));
                studentDetailsModel.setStudentName(cursor.getString(cursor.getColumnIndex(S_STUDENT_NAME)));
                studentDetailsModel.setStudentId(cursor.getString(cursor.getColumnIndex(S_STUDENT_ID)));
                studentDetailsModel.setStudentLevel(cursor.getString(cursor.getColumnIndex(S_STUDENT_LEVEL)));
                studentDetailsModel.setStudentDepartment(cursor.getString(cursor.getColumnIndex(S_STUDENT_DEPT)));
                studentDetailsModel.setStudentCourse(cursor.getString(cursor.getColumnIndex(S_STUDENT_COURSE)));
                studentDetailsModel.setAddedTime(""+cursor.getString(cursor.getColumnIndex(Constants.S_ADDED_TIMESTAMP)));
                studentDetailsModel.setUpdatedTime(""+cursor.getString(cursor.getColumnIndex(Constants.S_UPDATED_TIMESTAMP)));

                //add record to list
                studentsList.add(studentDetailsModel);

            }while (cursor.moveToNext());
        }
        int Cursorcount = cursor.getCount();
        //close db connection
        db.close();

        int hallCap = Integer.parseInt(hallCapacity);
        if (Cursorcount > hallCap){
            return null;
        }

        //return the list
        return studentsList;
    }

    public long insertAllocatedSits(AllocatedSitModel allocatedSitModel){
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //id will be inserted and autoincrement authomatically
        //insert data
        values.put(A_STUDENT_NAME, allocatedSitModel.getStudentName());
        values.put(A_STUDENT_ID, allocatedSitModel.getStudentId());
        values.put(Constants.A_STUDENT_LEVEL, allocatedSitModel.getStudentLevel());
        values.put(Constants.A_STUDENT_DEPT, allocatedSitModel.getStudentDepartment());
        values.put(Constants.A_STUDENT_COURSE, allocatedSitModel.getStudentCourse());
        values.put(Constants.A_HALL_NAME, allocatedSitModel.getHallName());
        values.put(Constants.A_SIT_NUMBER, allocatedSitModel.getSitNumber());
        values.put(Constants.A_ADDED_TIMESTAMP, allocatedSitModel.getAddedTime());
        values.put(Constants.A_UPDATED_TIMESTAMP, allocatedSitModel.getUpdatedTime());

        //insert row to db and return row id of saved record
        long id = db.insert(Constants.ALLOCATED_SITS_TABLE, null, values);
        //close db connection
        db.close();
        //return id of inserted row
        return id;

    }

    public boolean checkStudentHaveHall(String id){
        String[] column = {A_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = A_STUDENT_ID + " = ?";
        String[] selectionArgs= {id,};

        Cursor cursor = db.query(ALLOCATED_SITS_TABLE,column,selection,selectionArgs,null,null,null);
        int Cursorcount = cursor.getCount();
        db.close();
        if(Cursorcount>0){
            return true;
        }
        return false;
    }

    //get all students from sqlite database
    public ArrayList<AllocatedSitModel> getAllAllocatedStudents(String orderBy){
        //orderby query will allow to sort data e.g newest/oldest first, name ascending/descending
        //it will return list of items since we have used return type ArrayList<ModelItems>

        ArrayList<AllocatedSitModel> allocatedStudentsList = new ArrayList<>();
        //query to select records
        String selectQuery = "SELECT * FROM " + ALLOCATED_SITS_TABLE + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if(cursor.moveToFirst()){
            do{
                AllocatedSitModel allocatedSitModel = new AllocatedSitModel();
                allocatedSitModel.setId(cursor.getString(cursor.getColumnIndex(A_ID)));
                allocatedSitModel.setStudentName(cursor.getString(cursor.getColumnIndex(A_STUDENT_NAME)));
                allocatedSitModel.setStudentId(cursor.getString(cursor.getColumnIndex(A_STUDENT_ID)));
                allocatedSitModel.setStudentLevel(cursor.getString(cursor.getColumnIndex(Constants.A_STUDENT_LEVEL)));
                allocatedSitModel.setStudentDepartment(cursor.getString(cursor.getColumnIndex(Constants.A_STUDENT_DEPT)));
                allocatedSitModel.setStudentCourse(cursor.getString(cursor.getColumnIndex(Constants.A_STUDENT_COURSE)));
                allocatedSitModel.setHallName(cursor.getString(cursor.getColumnIndex(Constants.A_HALL_NAME)));
                allocatedSitModel.setSitNumber(cursor.getString(cursor.getColumnIndex(Constants.A_SIT_NUMBER)));
                allocatedSitModel.setAddedTime(""+cursor.getString(cursor.getColumnIndex(Constants.A_ADDED_TIMESTAMP)));
                allocatedSitModel.setUpdatedTime(""+cursor.getString(cursor.getColumnIndex(Constants.A_UPDATED_TIMESTAMP)));

                //add record to list
               allocatedStudentsList.add(allocatedSitModel);
            }while (cursor.moveToNext());
        }
        //close db connection
        db.close();

        //return the list
        return allocatedStudentsList;
    }


}
