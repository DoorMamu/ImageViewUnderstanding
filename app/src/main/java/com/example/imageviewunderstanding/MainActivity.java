package com.example.imageviewunderstanding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
ImageButton ma_ib;
ImageView ma_iv;
Button ma_swbtn;
Intent intent;
final static int clickcode=100;
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ma_ib=(ImageButton)findViewById(R.id.ma_iB);
        ma_iv=(ImageView)findViewById(R.id.ma_iV);
        ma_swbtn=(Button)findViewById(R.id.ma_swbtn);
        @SuppressLint("ResourceType") InputStream iS=getResources().openRawResource(R.drawable.jellyfish);
        bitmap= BitmapFactory.decodeStream(iS);

        //After Camera Image Button is clicked Start the Camera Service
        ma_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,clickcode);
            }
        });


        ma_swbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getApplicationContext().setWallpaper(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Get the data(raw image) after the camera capture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            Bundle bundle_mk=data.getExtras();
            bitmap= (Bitmap) bundle_mk.get("data");
            ma_iv.setImageBitmap(bitmap);
            Toast.makeText(this, "Wallpaper Set", Toast.LENGTH_SHORT).show();
        }
    }

}
