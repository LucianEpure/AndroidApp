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
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MomentSetup extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    Button saveBtn;
    Button cancelBtn;
    Button pickDateBtn;
    TextView showDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_setup);
        showDate = findViewById(R.id.showDateTv);
        pickDateBtn = findViewById(R.id.pickDateBtn);
        pickDateBtn.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                datePicker(view);
            }
        });
        saveBtn = findViewById(R.id.saveBtn);
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
