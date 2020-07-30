package com.example.hqcameraimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    String currentImagePath=null;
    private  static int IMAGE_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void captureImage(View view)
    {

        //07- Make intent for image capture
        Intent imageCaptureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //08- check resolve activity

        if (imageCaptureIntent.resolveActivity(getPackageManager())!=null){

            //09- if not null create image File go ahead  getImageFile() method which creates a image file and then return us the file

            File imageFile=null;

            try {
                imageFile=getImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //10- Check if imageFile not null then perform uri
            if(imageFile!=null)
            {
                Uri imageUri= FileProvider.getUriForFile(this,"com.example.android.fileprovider",imageFile);

                //11- attach uri to cameraintent1

                imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

                // 12 - Start Activity for result

                startActivityForResult(imageCaptureIntent,IMAGE_REQUEST);
            }

        }


    }

    public void displayImage(View view)

    {
        //-15- Display Image

        Intent displayImage=new Intent(this,MainActivity2.class);

        //16 Put Extra
        displayImage.putExtra("image_path",currentImagePath);

        startActivity(displayImage);
    }

    private File getImageFile() throws IOException {
        String timeStamp= new SimpleDateFormat("YYYYMMMDD_hhmmss").format(new Date());

        // 01-Unique name for Image

        String imageName= "JPG_"+timeStamp+"_";

        //02 - get storage directory

        File storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        //03 -Create temp file to store in with imageName,to storageDirectory

        File imageFile= File.createTempFile(imageName,".jpg",storageDir);

        //04 to display image we save the image path to display later

        currentImagePath=imageFile.getAbsolutePath();

        //05 return image file created

        return imageFile;
    }
}