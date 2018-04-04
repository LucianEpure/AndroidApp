package com.example.android.memoryapp.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;
import com.example.android.memoryapp.model.Memory;
import com.example.android.memoryapp.model.builder.MemoryBuilder;
import com.example.android.memoryapp.database.DateConversions;
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


public class EditMoment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private EditText titleEditText;
    private TextView dateTextView;
    private EditText descEditText;
    private Button saveBtn;
    private Button cancelBtn;
    private Button pickDateBtn;
    private int id;
    private String title;
    private String date;
    private String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_moment);

        titleEditText = findViewById(R.id.titleEt);
        dateTextView  = findViewById(R.id.showDateTv);
        descEditText = findViewById(R.id.descriptionEt);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        pickDateBtn = findViewById(R.id.pickDateBtn);
       //Data is extracted from the bundle and the fields are set with it.
        Bundle editData = new Bundle();
        Intent intent = getIntent();
        if(intent.hasExtra("editData")){
            editData = intent.getBundleExtra("editData");
        }
         id = editData.getInt("id");
         title = editData.getString("title");
         date = editData.getString("date");
         desc = editData.getString("desc");

        titleEditText.setText(title);
        descEditText.setText(desc);
        dateTextView.setText(date);
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(view);
            }
        });


        //SAVE BUTTON
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                finish();
                Intent intent = new Intent(EditMoment.this, ListMemories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //CANCEL BUTTON
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //Update the memory with the data from the editTexts
    protected void updateData(){
        DataBaseHelper db = DataBaseHelper.getInstance(EditMoment.this);
        Memory memory = db.getMemoryById(id);
        DateConversions dateConversions = new DateConversions();
        String dateSQL = dateConversions.getSQLFormattedDate(dateTextView.getText().toString());

        Memory updateMemory = new MemoryBuilder()
                .setId(id)
                .setTitle(titleEditText.getText().toString())
                .setDate(dateSQL)
                .setDescription(descEditText.getText().toString())
                .setImage(memory.getImage())
                .build();
        Memory updatedMemory = db.updateMemory(updateMemory);
        if(updatedMemory==null)
            Toast.makeText(EditMoment.this,"Moment Not Updated",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(EditMoment.this," Moment Updated",Toast.LENGTH_LONG).show();
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
        dateTextView.setText(dateFormat.format(calendar.getTime()));
    }
}
