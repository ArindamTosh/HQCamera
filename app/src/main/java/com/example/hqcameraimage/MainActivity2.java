package com.example.hqcameraimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    ImageView imageView;
    String extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=findViewById(R.id.imageView);

        //-- getting intent extras from activity 1 display image method

        extras= getIntent().getStringExtra("image_path");


        //-- decoding bitmap

        Bitmap bitmap= BitmapFactory.decodeFile(extras);

        //-- Showing bitmap

        imageView.setImageBitmap(bitmap);


    }
}