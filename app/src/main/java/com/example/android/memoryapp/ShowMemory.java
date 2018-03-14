package com.example.android.memoryapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ShowMemory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_memory);
        showData();
    }
    public void showData(){
        Cursor data = MainActivity.myDb.getAllData();
        if (data.getCount() == 0)
        {
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext()){
            buffer.append("Id:"+data.getString(0)+"\n");
            buffer.append("Title:"+data.getString(1)+"\n");
            buffer.append("Date:"+data.getString(2)+"\n");
            buffer.append("Desc:"+data.getString(3)+"\n");
            buffer.append("Image:"+data.getString(4)+"\n\n");

        }
        showMessage("Data",buffer.toString());
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowMemory.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
