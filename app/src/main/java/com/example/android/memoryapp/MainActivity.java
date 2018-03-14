package com.example.android.memoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button saveMemoryBtn;
    Button showMemoriesBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveMemoryBtn = findViewById(R.id.saveMemory);
        saveMemoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, PhotoSel.class);
                startActivity(myIntent);
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
