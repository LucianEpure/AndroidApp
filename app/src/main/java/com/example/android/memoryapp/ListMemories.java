package com.example.android.memoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.android.memoryapp.recyclePackage.ListItemClickListener;
import com.example.android.memoryapp.recyclePackage.GreenAdapter;

public class  ListMemories extends AppCompatActivity
        implements ListItemClickListener {

    private static DataBaseHelper dbHelper = MainActivity.myDb;

    private GreenAdapter myAdapter;
    private RecyclerView myNumbersList;
    private Intent displayMemIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_memories);
        myNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myNumbersList.setLayoutManager(layoutManager);
        Cursor cursor = dbHelper.getAllData("Memory");

        myAdapter = new GreenAdapter(cursor, this);
        myNumbersList.setAdapter(myAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        displayMemIntent = new Intent(ListMemories.this, OneMemoryDisplay.class);
        displayMemIntent.putExtra("mesage", "In the database you should retrieve object "+ clickedItemIndex);
        startActivity(displayMemIntent);
    }

}
