package com.example.android.memoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static com.example.android.memoryapp.PhotoSel.getBitmapAsByteArray;

/**
 * Created by Lucian on 13.03.2018.
 */

public class DataBaseHelper  extends SQLiteOpenHelper{
    //Our database name and columns

    public static final String DATABASE_NAME = "Memory.db";
    public static final String TABLE_NAME1 = "Memory";
    public static final String COL1_1= "Id";
    public static final String COL1_2= "Title";
    public static final String COL1_3 = "Date";
    public static final String COL1_4 = "Description";
    public static final String COL1_5 = "Image";
    public static final String TABLE_NAME2 = "Person";
    public static final String COL2_1= "Id";
    public static final String COL2_2= "Name";
    public static final String COL2_3 = "Surname";
    public static final String COL2_4 = "Help";
    public static final String COL2_5 = "Image";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DATE TEXT,DESCRIPTION TEXT,IMAGE BLOB)"); //BLOB)
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,HELP TEXT,IMAGE BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }
//Insert in data base **********************************************************************************
    public boolean insertDataMemory(String title, String date, String description, byte[] image){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_2,title);
        contentValues.put(COL1_3,date);
        contentValues.put(COL1_4,description);
        contentValues.put(COL1_5,image);

       long result = sqLiteDatabase.insert("Memory",null,contentValues);
        if(result == -1) {
            sqLiteDatabase.close();
            return false;
        }
        else {
            sqLiteDatabase.close();
            return true;
        }
    }
    public boolean insertDataPerson(String name, String surname, String help, byte[] image){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_2,name);
        contentValues.put(COL2_3,surname);
        contentValues.put(COL2_4,help);
        contentValues.put(COL2_5,image);
        long result = sqLiteDatabase.insert("Person",null,contentValues);
        if(result == -1) {
            sqLiteDatabase.close();
            return false;
        }
        else {
            sqLiteDatabase.close();
            return true;
        }
    }

    //Retrieve from data base ********************************************

    public Cursor getAllData(String tableName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery(" select * from "+ tableName,null);
        return res;
    }
    public Cursor getDataById(String tableName, String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM "+ tableName + " WHERE Id = " + id,null );
        return res;
    }

    public boolean updateData(String id, String title, String date, String description, byte[] image ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_1,id);
        contentValues.put(COL1_2,title);
        contentValues.put(COL1_3,date);
        contentValues.put(COL1_4,description);
        contentValues.put(COL1_5,image);
        sqLiteDatabase.update(TABLE_NAME1,contentValues,"id = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME1," ID = ? ", new String[]{id});

    }

}
