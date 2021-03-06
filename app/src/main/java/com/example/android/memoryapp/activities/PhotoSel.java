package com.example.android.memoryapp.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.android.memoryapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class PhotoSel extends AppCompatActivity {


    private Button momentBtn;
    private Button friendBtn;
    private ImageView photoView;
    private Uri imageUri;
    private Uri imageUri2;
    private  byte[] imageByte;
    private static final int PICK_IMAGE = 100;
    private static final int PICTURE_RESULT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_sel);
        dialog();
        photoView = findViewById(R.id.photoView);

        momentBtn = findViewById(R.id.momentBtn);
        momentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PhotoSel.this, MomentSetup.class);
                myIntent.putExtra("image",imageByte);// send the image to the setup to be stored into data base
                startActivity(myIntent);
                finish(); //finishes this activity so when going back it will appear no more
            }
        });
        friendBtn=findViewById(R.id.friendBtn);
        friendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PhotoSel.this, FriendSetup.class);
                myIntent.putExtra("image",imageByte);// send the image to the setup to be stored into data base
                startActivity(myIntent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                photoView.setImageURI(imageUri);
                try {
                    ContentResolver cr = getBaseContext().getContentResolver();  // the imageUri is converted into a byte array
                    InputStream inputStream = cr.openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    imageByte = baos.toByteArray();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if(requestCode == PICTURE_RESULT) {
                try {
                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                            getContentResolver(), imageUri2);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                    imageByte = baos.toByteArray();
                    photoView.setImageBitmap(thumbnail);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //If the select from gallery was chosen
    public void onSelectFromGalleryResult(){
        Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

//dialog asking where to choose photo from
    protected void dialog(){
            final String[] options = {"Take Photo", "Gallery", "Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(PhotoSel.this);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    if (options[which].equals("Take Photo")) {
                        ContentValues values = new ContentValues();
                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                        imageUri2 = getContentResolver().insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri2);
                        startActivityForResult(intent, PICTURE_RESULT);
                    } else if (options[which].equals("Gallery")) {
                        onSelectFromGalleryResult();
                    } else if (options[which].equals("Cancel")) {
                        dialogInterface.dismiss();
                        finish();
                    }
                }
            });
            builder.show();
    }
}
