package com.kienvt.tclgt.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kienvt.tclgt.R;

/**
 * Created by kienvithanh on 1/16/16.
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d("StartActivity", "onCreate");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("StartActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("StartActivity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("StartActivity", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("StartActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("StartActivity", "onResume");
    }
}
