package com.example.android.memoryapp.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;

public class FriendSetup extends AppCompatActivity {

    Button saveBtn ;
    Button cancelBtn ;
    EditText firstNameEt ;
    EditText lastNameEt ;
    EditText helpInfoEt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_setup);

        firstNameEt = findViewById(R.id.firstNameEt);
        lastNameEt = findViewById(R.id.lastNameEt);
        helpInfoEt = findViewById(R.id.helpInfoEt);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted;
                isInserted = DataBaseHelper.getInstance(FriendSetup.this).insertDataPerson(firstNameEt.getText().toString(),lastNameEt.getText().toString(),helpInfoEt.getText().toString(),"AAA");
                if(isInserted == true)
                    Toast.makeText( FriendSetup.this,"Friend Added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText( FriendSetup.this,"Friend Not Added!",Toast.LENGTH_LONG).show();
            }
        });
        cancelBtn = findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }
    protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(FriendSetup.this);
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        builder.show();
    }
}
