package com.example.android.memoryapp.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;
import com.example.android.memoryapp.model.Friend;
import com.example.android.memoryapp.recyclePackage.FriendAdapter;

import java.util.ArrayList;

public class ListFriends extends AppCompatActivity {

    private FriendAdapter myAdapter;
    private RecyclerView myFriendsListRecycleView;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friends);
        myFriendsListRecycleView = (RecyclerView) findViewById(R.id.rv_friends);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myFriendsListRecycleView.setLayoutManager(layoutManager);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("known status")) {
            status = myIntent.getIntExtra("known status", 0);
        }

        myAdapter = new FriendAdapter(ListFriends.this);
        myFriendsListRecycleView.setAdapter(myAdapter);

        ArrayList<Friend> friends= DataBaseHelper.getInstance(ListFriends.this).getAllFriendsWithKnownStatus(status);
        myAdapter.setFriends(friends);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //do nothing, we only care about swiping
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                DataBaseHelper dbHelper= DataBaseHelper.getInstance(ListFriends.this);
                Friend selFriend = dbHelper.getFriendById(id);
                int newStatus = 1 -status;
                selFriend.setKnown(newStatus);

                String val;
                if (newStatus==1) val ="known";
                else val = "unknown";
                Toast.makeText(ListFriends.this, selFriend.getAllName().toUpperCase()+ " marked as "+val , Toast.LENGTH_SHORT).show();

                dbHelper.updateFriend(selFriend);
                recreate();
            }

        }).attachToRecyclerView(myFriendsListRecycleView);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                deleteDialog(id);
            }

        }).attachToRecyclerView(myFriendsListRecycleView);
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(ListFriends.this, RememberGame.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    protected void deleteDialog(final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListFriends.this);
        builder.setMessage("Friend will be deleted. Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBaseHelper dbHelper = DataBaseHelper.getInstance(ListFriends.this);
                dbHelper.deleteFriend(id);
                dialogInterface.dismiss();
                recreate();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                recreate();
            }
        });

        builder.show();
    }
}
