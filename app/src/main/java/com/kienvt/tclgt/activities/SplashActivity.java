package com.kienvt.tclgt.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kienvt.tclgt.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        CustomCountDownTimer customCountDownTimer = new CustomCountDownTimer(1500, 500);
        customCountDownTimer.start();
    }

    public class CustomCountDownTimer extends CountDownTimer {


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CustomCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            goToHomeActivity();
        }
    }

    public void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        finish();
    }
}
