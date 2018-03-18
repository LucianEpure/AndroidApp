package com.example.android.memoryapp.activities;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;

public class ShowMemory extends AppCompatActivity {


    ImageView imageViewTest; //is used just for testing the retrival of image from database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_memory);
        imageViewTest = findViewById(R.id.imageViewTest);
        showData();
    }

    //Show all data from table

    public void showData(){

        Cursor data = DataBaseHelper.getInstance(ShowMemory.this).getAllData("Memory");
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
            Bitmap bitmap = BitmapFactory.decodeByteArray(data.getBlob(4), 0, data.getBlob(4).length);
            imageViewTest.setImageBitmap(bitmap);
        }
        showMessage("Data",buffer.toString());
    }
    //Printing message with data from row
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowMemory.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
