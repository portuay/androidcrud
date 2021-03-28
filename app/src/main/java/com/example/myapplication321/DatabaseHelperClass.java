package com.example.myapplication321;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="employee_database";
    private static final String TABLE_NAME="EMPLOYEE";
    public static final String ID="id";
    public static final String NAME = "name";
    public static final String EMAIL="email";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE="create table "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NAME+"TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL);";


    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addEmployee(EmployeeModelClass employeeModelClass){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME,employeeModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL,employeeModelClass.getEmail());
        sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME,null,contentValues);
    }
}
