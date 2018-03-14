package com.example.android.memoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button saveMemoryBtn;
    Button showMemory;
    static DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(MainActivity.this);


        saveMemoryBtn = findViewById(R.id.saveMemory);
        saveMemoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selPhoto = new Intent(MainActivity.this, PhotoSel.class);
                startActivity(selPhoto);
            }
        });
        showMemory = findViewById(R.id.showMemory);
        showMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showMemory = new Intent(MainActivity.this,ShowMemory.class);
                startActivity(showMemory);

            }
        });
    }
}
