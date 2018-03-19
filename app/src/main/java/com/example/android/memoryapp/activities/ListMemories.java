package com.example.android.memoryapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
import com.example.android.memoryapp.recyclePackage.ListItemClickListener;
import com.example.android.memoryapp.recyclePackage.GreenAdapter;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;

public class  ListMemories extends AppCompatActivity
        implements ListItemClickListener {


    private GreenAdapter myAdapter;
    private RecyclerView myNumbersList;
    private Intent displayMemIntent;

    CallbackManager callbackManager;
    ShareDialog sortNewDialog, sortOldDialog, clearDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_memories);
        DataBaseHelper dbHelper = DataBaseHelper.getInstance(ListMemories.this);
        myNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myNumbersList.setLayoutManager(layoutManager);
        Cursor cursor = dbHelper.getAllData("Memory");

        myAdapter = new GreenAdapter(this);
        myNumbersList.setAdapter(myAdapter);

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
        switch(itemThatWasClickedId) {
            case (R.id.action_sortNew):
                // init callback
                callbackManager = CallbackManager.Factory.create();
                sortNewDialog = new ShareDialog(ListMemories.this);
                sortNewDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(ListMemories.this, "Sort new succesful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(ListMemories.this, "Sort canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(ListMemories.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

                //insert behavior
                break;
            case (R.id.action_sortOld):
                // init callback
                callbackManager = CallbackManager.Factory.create();
                sortOldDialog= new ShareDialog(ListMemories.this);

                sortOldDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(ListMemories.this, "Sort old successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(ListMemories.this, "Sort canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(ListMemories.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

                //insert behavior
                break;
            case (R.id.action_clearMem):
                // init callback
                callbackManager = CallbackManager.Factory.create();
                clearDialog = new ShareDialog(ListMemories.this);

                clearDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(ListMemories.this, "Clear memories successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(ListMemories.this, "Clear canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(ListMemories.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                //insert behaviors
                break;

        }

        return true;

    }
}
