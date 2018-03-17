package com.example.android.memoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OneMemoryDisplay extends AppCompatActivity {

    private TextView myDisplayText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_memory_display);
        myDisplayText = (TextView)findViewById(R.id.mesageText);

        Intent intentThatCreatedThisMem = getIntent();

        if (intentThatCreatedThisMem.hasExtra("mesage")){
            String mesage = intentThatCreatedThisMem.getStringExtra("mesage");
            myDisplayText.setText(mesage);
        }

    }
}
