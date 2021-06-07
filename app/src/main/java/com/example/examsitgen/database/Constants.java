package com.example.examsitgen.database;

public class Constants {

    public static final String DEPARTMENT_SELECTED = "";
    public static final String DEPARTMENT_LEVEL= "";
    //db name
    public static final String DB_NAME = "EXAMSITGEN_DB";
    //db version
    public static final int DB_VERSION = 1;
    //table name
    public static final String USERS_TABLE = "USER_TABLE";
    public static final String EXAMS_TABLE = "EXAM_TABLE";
    public static final String HALLS_TABLE = "HALL_TABLE";
    public static final String STUDENTS_TABLE = "STUDENT_TABLE";
    public static final String ALLOCATED_SITS_TABLE = "ALLOCATED_SIT_TABLE";
    public static final String DEPARTMENT_TABLE = "DEPARTMENT_TABLE";

    //columns/fields of USERS TABLE
    public static final  String U_ID = "ID";
    public static final  String U_USERNAME = "USER_NAME";
    public static final  String U_EMAIL= "EMAIL";
    public static final  String U_PASSWORD = "PASSWORD";
    public static final  String U_ROLE = "ROLE";
    public static final  String U_ROLEID = "ROLE_ID";
    public static final  String U_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";


    //columns/fields of STUDENTS TABLE
    public static final  String S_ID = "ID";
    public static final  String S_STUDENT_NAME = "STUDENT_NAME";
    public static final  String S_STUDENT_ID= "STUDENT_ID";
    public static final  String S_STUDENT_LEVEL= "STUDENT_LEVEL";
    public static final  String S_STUDENT_DEPT = "STUDENT_DEPARTMENT";
    public static final  String S_STUDENT_COURSE = "STUDENT_COURSE";
    public static final  String S_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final  String S_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";


    //columns/fields of HALLS TABLE
    public static final  String H_ID = "ID";
    public static final  String H_HALL_NAME = "HALL_NAME";
    public static final  String H_HALL_CAPACITY= "HALL_CAPACITY";
    public static final  String H_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final  String H_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";


    //columns/fields of DEPT TABLE
    public static final  String D_ID = "ID";
    public static final  String D_DEPT_NAME = "DEPARTMENT_NAME";
    public static final  String D_DEPT_LEVEL = "DEPARTMENT_LEVEL";
    public static final  String D_DEPT_STUDENT_NO = "DEPARTMENT_STUDENT_NO";
    public static final  String D_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final  String D_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";


    //columns/fields of EXAMS TABLE
    public static final  String E_ID = "ID";
    public static final  String E_COURSE_TITLE = "COURSE_TITLE";
    public static final  String E_COURSE_CODE= "COURSE_CODE";
    public static final  String E_COURSE_LEVEL= "COURSE_LEVEL";
    public static final  String E_COURSE_DEPARTMENT = "COURSE_DEPARTMENT";
    public static final  String E_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final  String E_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";


    //columns/fields of ALLOCATED SITS TABLE
    public static final  String A_ID = "ID";
    public static final  String A_STUDENT_NAME = "STUDENT_NAME";
    public static final  String A_STUDENT_ID= "STUDENT_ID";
    public static final  String A_STUDENT_LEVEL= "STUDENT_LEVEL";
    public static final  String A_STUDENT_DEPT = "STUDENT_DEPARTMENT";
    public static final  String A_STUDENT_COURSE = "STUDENT_COURSE";
    public static final  String A_HALL_NAME = "HALL_NAME";
    public static final  String A_SIT_NUMBER = "SIT_NUMBER";
    public static final  String A_COURSE_TITLE = "COURSE_TITLE";
    public static final  String A_COURSE_CODE = "COURSE_CODE";
    public static final  String A_ADDED_TIMESTAMP = "ADDED_TIME_STAMP";
    public static final  String A_UPDATED_TIMESTAMP = "UPDATED_TIME_STAMP";


    //Create USERS table query
    public static final  String CREATE_USERS_TABLE = "CREATE TABLE " + USERS_TABLE + "("
            + U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + U_USERNAME + " TEXT,"
            + U_EMAIL + " TEXT,"
            + U_PASSWORD + " TEXT,"
            + U_ROLE + " TEXT,"
            + U_ROLEID + " INTEGER,"
            + U_ADDED_TIMESTAMP + " TEXT"
            + ")";


    //Create STUDENTS table query
    public static final  String CREATE_STUDENTS_TABLE = "CREATE TABLE " + STUDENTS_TABLE + "("
            + S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + S_STUDENT_NAME + " TEXT,"
            + S_STUDENT_ID + " TEXT,"
            + S_STUDENT_LEVEL + " TEXT,"
            + S_STUDENT_DEPT + " TEXT,"
            + S_STUDENT_COURSE + " TEXT,"
            + S_ADDED_TIMESTAMP + " TEXT,"
            + S_UPDATED_TIMESTAMP + " TEXT"
            + ")";

    //Create HALLS table query
    public static final  String CREATE_HALLS_TABLE = "CREATE TABLE " + HALLS_TABLE + "("
            + H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + H_HALL_NAME + " TEXT,"
            + H_HALL_CAPACITY + " TEXT,"
            + H_ADDED_TIMESTAMP + " TEXT,"
            + H_UPDATED_TIMESTAMP + " TEXT"
            + ")";


    //Create DEPARTMENT table query
    public static final  String CREATE_DEPARTMENT_TABLE = "CREATE TABLE " + DEPARTMENT_TABLE + "("
            + D_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + D_DEPT_NAME + " TEXT,"
            + D_DEPT_LEVEL + " TEXT,"
            + D_DEPT_STUDENT_NO + " TEXT,"
            + D_ADDED_TIMESTAMP + " TEXT,"
            + D_UPDATED_TIMESTAMP + " TEXT"
            + ")";


    //Create EXAMS table query
    public static final  String CREATE_EXAMS_TABLE = "CREATE TABLE " + EXAMS_TABLE + "("
            + E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + E_COURSE_TITLE + " TEXT,"
            + E_COURSE_CODE + " TEXT,"
            + E_COURSE_LEVEL + " TEXT,"
            + E_COURSE_DEPARTMENT + " TEXT,"
            + E_ADDED_TIMESTAMP + " TEXT,"
            + E_UPDATED_TIMESTAMP + " TEXT"
            + ")";


    //Create STUDENTS table query
    public static final  String CREATE_ALLOCATED_SITS_TABLE = "CREATE TABLE " + ALLOCATED_SITS_TABLE + "("
            + A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + A_STUDENT_NAME + " TEXT,"
            + A_STUDENT_ID + " TEXT,"
            + A_STUDENT_LEVEL + " TEXT,"
            + A_STUDENT_DEPT + " TEXT,"
            + A_STUDENT_COURSE + " TEXT,"
            + A_HALL_NAME + " TEXT,"
            + A_SIT_NUMBER + " TEXT,"
            + A_COURSE_TITLE + " TEXT,"
            + A_COURSE_CODE + " TEXT,"
            + A_ADDED_TIMESTAMP + " TEXT,"
            + A_UPDATED_TIMESTAMP + " TEXT"
            + ")";

}
