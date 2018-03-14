package com.example.android.memoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.android.memoryapp.recyclePackage.ListItemClickListener;
import com.example.android.memoryapp.recyclePackage.GreenAdapter;


public class  ListMemories extends AppCompatActivity
        implements ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;

    private GreenAdapter myAdapter;
    private RecyclerView myNumbersList;

    private Toast myToast; // for quick clicks
    private Intent displayMemIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_memories);
        myNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myNumbersList.setLayoutManager(layoutManager);

        myAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
        myNumbersList.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_refresh:
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                myAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
                myNumbersList.setAdapter(myAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        displayMemIntent = new Intent(ListMemories.this, OneMemoryDisplay.class);
        displayMemIntent.putExtra("mesage", "In the database you should retrieve object "+ clickedItemIndex);
        startActivity(displayMemIntent);
    }
}
