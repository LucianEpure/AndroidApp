package com.example.android.memoryapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;
import com.example.android.memoryapp.model.Memory;
import com.example.android.memoryapp.recyclePackage.MemoryAdapter;
import com.example.android.memoryapp.recyclePackage.MemoryClickListener;

import java.util.ArrayList;

public class  ListMemories extends AppCompatActivity
        implements MemoryClickListener {


    private MemoryAdapter myAdapter;
    private RecyclerView myMemList;
    private Intent displayMemIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_memories);
        DataBaseHelper dbHelper = DataBaseHelper.getInstance(ListMemories.this);
        myMemList = (RecyclerView) findViewById(R.id.rv_memories);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myMemList.setLayoutManager(layoutManager);
        Cursor cursor = dbHelper.getAllData("Memory");

        myAdapter = new MemoryAdapter(this);
        myMemList.setAdapter(myAdapter);

        ArrayList<Memory> memories= DataBaseHelper.getInstance(ListMemories.this).getAllMemories();
        myAdapter.setMemories(memories);
    }


    @Override
    public void onListItemClick(Memory memory) {
        displayMemIntent = new Intent(ListMemories.this, OneMemoryDisplay.class);
        displayMemIntent.putExtra("idMemory", memory.getId());
        startActivity(displayMemIntent);
    }
}
