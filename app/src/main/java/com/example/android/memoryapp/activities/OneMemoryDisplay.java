package com.example.android.memoryapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.squareup.picasso.Picasso;



public class OneMemoryDisplay extends AppCompatActivity {

    private TextView titleTextView;
    private ImageView imageView;
    private EditText descriptionTextView;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    com.squareup.picasso.Target target = new com.squareup.picasso.Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
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

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_one_memory_display);


        titleTextView= (TextView)findViewById(R.id.titleOneMem);
        descriptionTextView= (EditText) findViewById(R.id.descriptionOneMem);
        imageView =(ImageView) findViewById(R.id.imageOneMem);

        Intent myIntent = getIntent();
        int id=0;

        if (myIntent.hasExtra("idMemory")) {
            id = myIntent.getIntExtra("idMemory", 0);
        }

        Memory memory = DataBaseHelper.getInstance(OneMemoryDisplay.this).getMemoryById(id);
        titleTextView.setText(memory.getTitle());
        descriptionTextView.setText(memory.getDescription());

        Bitmap bitmap = BitmapFactory.decodeByteArray(memory.getImage(), 0, memory.getImage().length);
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

            Picasso.with(getBaseContext())
                    .load("https://upload.wikimedia.org/wikipedia/en/2/21/Web_of_Spider-Man_Vol_1_129-1.png")
                    .into(target);

        }

        return true;

    }
}
