package com.example.android.memoryapp.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.memoryapp.R;
import com.example.android.memoryapp.database.DataBaseHelper;
import com.example.android.memoryapp.model.Memory;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;



public class OneMemoryDisplay extends AppCompatActivity {

    private TextView titleTextView;
    private TextView dateTextView;
    private ImageView imageView;
    private EditText descriptionTextView;
    private Bitmap bitmap;
    private int id=0;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_one_memory_display);


        titleTextView= (TextView)findViewById(R.id.titleOneMem);
        descriptionTextView= (EditText) findViewById(R.id.descriptionOneMem);
        dateTextView = (TextView) findViewById(R.id.dateTv);
        imageView =(ImageView) findViewById(R.id.imageOneMem);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("idMemory")) {
            id = myIntent.getIntExtra("idMemory", 0);
        }

        Memory memory = DataBaseHelper.getInstance(OneMemoryDisplay.this).getMemoryById(id);
        titleTextView.setText(memory.getTitle());
        descriptionTextView.setText(memory.getDescription());
        dateTextView.setText(memory.getDate());
        bitmap = BitmapFactory.decodeByteArray(memory.getImage(), 0, memory.getImage().length);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_share) {

            // init callback
            callbackManager = CallbackManager.Factory.create();
            shareDialog = new ShareDialog(OneMemoryDisplay.this);

            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                @Override
                public void onSuccess(Sharer.Result result) {
                    Toast.makeText(OneMemoryDisplay.this, "Share successful", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancel() {
                    Toast.makeText(OneMemoryDisplay.this, "Share canceled", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(OneMemoryDisplay.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            if (bitmap!=null) shareImage();
        }
        else if(itemThatWasClickedId == R.id.update){
            finish();
            updateData();
        }
        else if(itemThatWasClickedId == R.id.delete){
            dialog();//deletes the data from the db and refreshes the list
        }
        return true;
    }

    private void shareImage() {
        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        if (shareDialog.canShow(SharePhotoContent.class)) {
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(sharePhoto)
                    .build();
            shareDialog.show(content);
        }
    }

    protected void updateData(){
        Intent intent = new Intent(OneMemoryDisplay.this, EditMoment.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("title",titleTextView.getText().toString());
        bundle.putString("date",dateTextView.getText().toString());
        bundle.putString("desc",descriptionTextView.getText().toString());
        intent.putExtra("editData",bundle);
        startActivity(intent);
    }
    protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(OneMemoryDisplay.this);
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Integer deletedRows = DataBaseHelper.getInstance(OneMemoryDisplay.this).deleteMemory(id);
                if(deletedRows>0)
                    Toast.makeText(OneMemoryDisplay.this,"Data deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(OneMemoryDisplay.this,"Data not deleted",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(OneMemoryDisplay.this, ListMemories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });

        builder.show();
    }
    @Override
    protected void onDestroy() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.onDestroy();
        if(bitmap!=null)
        {
            bitmap.recycle();
            bitmap=null;
        }

    }

}
