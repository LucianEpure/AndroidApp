package com.example.android.memoryapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_mems_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
       if ( itemThatWasClickedId==R.id.action_clearMem) {
           deleteDialog();
       }
        return true;

    }
    protected void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListMemories.this);
        builder.setMessage("All the data will be erased. Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBaseHelper db = DataBaseHelper.getInstance(ListMemories.this);
                db.deleteAllData("Memory");
                finish();
                startActivity(getIntent());
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
