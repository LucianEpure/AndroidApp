package com.example.android.memoryapp.activities;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;
import com.example.android.memoryapp.model.Friend;
import java.util.ArrayList;
import java.util.Random;

public class RememberGame extends AppCompatActivity {

    private ArrayList<Friend> friends;
    private TextView titleView;
    private Random rand;
    private ImageView imageView;
    private EditText giveNameView;
    private Button seeBtn;
    private Button checkBtn;
    private Button infoBtn;
    private Bitmap bitmap;
    private Friend currentFriend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_game);
        titleView= (TextView) findViewById(R.id.whosthere);
        friends = DataBaseHelper.getInstance(RememberGame.this).getAllFriendsWithKnownStatus(0);
        if (friends==null){
            Toast.makeText(RememberGame.this, "No friends saved", Toast.LENGTH_LONG).show();
        }else {
            imageView = (ImageView) findViewById(R.id.imageFriend);
            giveNameView = (EditText) findViewById(R.id.giveName);
            rand = new Random();
            setRandomFriend();
            setRound();

        checkBtn = findViewById(R.id.check);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedName = giveNameView.getText().toString().toLowerCase();
                String savedName = currentFriend.getAllName().toLowerCase();

                if (typedName.equals(savedName)){
                    Toast.makeText(RememberGame.this, "Good job!", Toast.LENGTH_SHORT).show();
                    setRandomFriend();
                    setRound();
                }else{
                    Toast.makeText(RememberGame.this,"That's not right, buddy!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seeBtn = findViewById(R.id.see);
        seeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String savedName= currentFriend.getAllName();
                giveNameView.setText(savedName.toUpperCase());
            }
        });

        infoBtn = findViewById(R.id.info);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info =currentFriend.getHelpInfo();
                if (info.equals("")) info = "no help info given";
                Toast.makeText(RememberGame.this, info, Toast.LENGTH_LONG).show();
            }
        });
        }
    }

    private void setRandomFriend(){
        Friend prev = currentFriend;

        int index = rand.nextInt(friends.size());
        Friend tryFriend = friends.get(index);

        if (friends.size()>1) {
            while (tryFriend.equals(prev)) {
                index = rand.nextInt(friends.size());
                tryFriend = friends.get(index);
            }
        }
        currentFriend = tryFriend;
    }


    private void setRound(){
        bitmap = BitmapFactory.decodeByteArray(currentFriend.getImage(), 0, currentFriend.getImage().length);
        imageView.setImageBitmap(bitmap);
        giveNameView.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memory_game_menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        Intent myIntent;
        switch(itemThatWasClickedId) {
            case (R.id.action_mark):
                currentFriend.setKnown(1);
                friends.remove(currentFriend);
                DataBaseHelper.getInstance(RememberGame.this).updateFriend(currentFriend);
                if (friends.size()==0){
                    Toast.makeText(RememberGame.this, "No friends saved", Toast.LENGTH_LONG).show();
                }
                break;
            case (R.id.action_showKnown):
                myIntent = new Intent(RememberGame.this, ListFriends.class);
                myIntent.putExtra("known status", 1);
                startActivity(myIntent);
                break;
            case (R.id.action_showUnknown):
                myIntent = new Intent(RememberGame.this, ListFriends.class);
                myIntent.putExtra("known status", 0);
                startActivity(myIntent);
                break;
        }
        return true;
    }
}
