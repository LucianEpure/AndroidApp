package com.example.android.memoryapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    Button saveMemoryBtn;
    Button showMemoriesBtn;
    Button databaseTestBtn;
    //static DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper myDbHelper = DataBaseHelper.getInstance(MainActivity.this);


        saveMemoryBtn = findViewById(R.id.saveMemory);
        saveMemoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selPhoto = new Intent(MainActivity.this, PhotoSel.class);
                startActivity(selPhoto);
            }
        });
        databaseTestBtn = findViewById(R.id.databaseTest);
        databaseTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showMemory = new Intent(MainActivity.this,ShowMemory.class);
                startActivity(showMemory);

            }
        });

        showMemoriesBtn = findViewById(R.id.showMemories);
        showMemoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ListMemories.class);
                startActivity(myIntent);
            }
        });


    }
}
