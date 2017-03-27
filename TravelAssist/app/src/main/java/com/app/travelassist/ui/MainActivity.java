package com.app.travelassist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.app.travelassist.R;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 3 * 1000;
    private Handler mHandler;
    private Runnable mMoveNextRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout();
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
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }
    }
}
