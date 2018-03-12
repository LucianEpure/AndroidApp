package com.example.android.memoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PhotoSel extends AppCompatActivity {

    int SELECT_FILE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_sel);
        dialog();
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
