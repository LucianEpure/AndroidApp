package com.example.android.memoryapp;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MomentSetup extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button saveBtn;
    Button cancelBtn;
    Button pickDateBtn;
    TextView showDate;
    EditText title;
    EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_setup);
        showDate = findViewById(R.id.showDateTv);
        pickDateBtn = findViewById(R.id.pickDateBtn);
        title = findViewById(R.id.titleEt);
        description = findViewById(R.id.descriptionEt);
        pickDateBtn.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                datePicker(view);
            }
        });
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( MomentSetup.this,"Save pressed",Toast.LENGTH_LONG);
                boolean isInserted;
                isInserted = MainActivity.myDb.insertData(title.getText().toString() , showDate.getText().toString(),description.getText().toString(),"aaaa");
                if(isInserted == true)
                    Toast.makeText( MomentSetup.this,"data inserted!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText( MomentSetup.this,"data not inserted!",Toast.LENGTH_LONG).show();
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



    //CANCEL button
    protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MomentSetup.this);
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


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

    //DATE SELECTION CALENDAR
    public void datePicker(View view){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getFragmentManager(),"date");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year,month,day);
        setDate(cal);
    }

    public void setDate(final Calendar calendar)
    {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        showDate.setText(dateFormat.format(calendar.getTime()));
    }

}
