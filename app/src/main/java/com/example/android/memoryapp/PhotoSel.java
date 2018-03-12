package com.example.android.memoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhotoSel extends AppCompatActivity {

    Button momentBtn, friendBtn;

    int SELECT_FILE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_sel);
        dialog();
        momentBtn = findViewById(R.id.button4);
        momentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PhotoSel.this, MomentSetup.class);
                startActivity(myIntent);
            }
        });
        friendBtn=findViewById(R.id.button5);
        friendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PhotoSel.this, FriendSetup.class);
                startActivity(myIntent);
            }
        });

    }

    protected void dialog(){
            final String[] options = {"Take Photo", "Gallery", "Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(PhotoSel.this);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if (options[which].equals("Take Photo")) {

                    } else if (options[which].equals("Gallery")) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(
                                Intent.createChooser(intent, "Gallery"),
                                SELECT_FILE);
                    } else if (options[which].equals("Cancel")) {
                        dialogInterface.dismiss();
                        finish();
                    }
                }
            });
            builder.show();
    }
}
