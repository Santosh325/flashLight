package com.example.flashlight;

import androidx.annotation.AttrRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView b1;
    boolean ison;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ison = false;
        b1 = findViewById(R.id.flashlight);
        final String myString = "010101010101010101010";
        final long blinkDelay = 50;
        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                for(int i = 0; i < myString.length(); i++) {
                    if(ison) {
                        b1.setImageResource(R.drawable.on);
                        flashlightoff();
                        ison = false;
                    } else {
                        b1.setImageResource(R.drawable.off);
                        flashlighton();
                        ison = true;
                    } try {
                        Thread.sleep(blinkDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    void flashlighton() {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            String CamaraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(CamaraId, true);
        } catch (Exception e) {

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    void flashlightoff() {
        CameraManager cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
        try {
            String CamaraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(CamaraId, false);
        } catch (Exception e) {

        }

    }
}
