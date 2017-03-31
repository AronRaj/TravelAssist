package com.app.travelassist.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.app.travelassist.R;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 3 * 1000;
    private Handler mHandler;
    private Runnable mMoveNextRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout();
        checkForPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mHandler && null != mMoveNextRunnable) {
            mHandler.postDelayed(mMoveNextRunnable, SPLASH_SCREEN_TIME_OUT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mHandler && null != mMoveNextRunnable) {
            mHandler.removeCallbacks(mMoveNextRunnable);
        }
    }

    private void addLayout() {
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        mMoveNextRunnable = new MovePageRunnable();
    }

    private class MovePageRunnable implements Runnable {

        @Override
        public void run() {
            overridePendingTransition(R.animator.slide_left_enter,R.animator.slide_left_exit);
            startActivity(new Intent(getApplicationContext(),ShopsListActivity.class));
            finish();
        }
    }

    private void checkForPermission() {
        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            //selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                    //selectImage();
                } else {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
