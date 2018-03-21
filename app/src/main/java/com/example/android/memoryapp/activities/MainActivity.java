package com.example.android.memoryapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.android.memoryapp.R;
import com.example.android.memoryapp.activities.PhotoSel;
import com.example.android.memoryapp.activities.ShowMemory;
import com.example.android.memoryapp.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    private Button saveMemoryBtn;
    private Button showMemoriesBtn;
    private Button rememberGameBtn;
    private static final int MY_CAMERA_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissions();

        saveMemoryBtn = findViewById(R.id.saveMemoryBtn);
        saveMemoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selPhoto = new Intent(MainActivity.this, PhotoSel.class);
                startActivity(selPhoto);
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

        rememberGameBtn = findViewById(R.id.rememberGame);
        rememberGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, RememberGame.class);
                startActivity(myIntent);
            }
        });

    }

    //Obtain permissions for camera and external storage
    public void getPermissions() {
        if ((ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_CAMERA_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_CAMERA_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to get Camera", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
