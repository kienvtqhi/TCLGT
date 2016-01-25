package com.kienvt.tclgt.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kienvt.tclgt.R;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button1;
    Button button2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        imageView = (ImageView) findViewById(R.id.image_view);

        textView.setText(R.string.app_name);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setVisibility(View.INVISIBLE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.VISIBLE);
                textView.setText("Clicked on button 1");
                Toast.makeText(MainActivity.this, "Click on button 1", Toast.LENGTH_SHORT).show();

                goToSecondActivity();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.INVISIBLE);
                imageView.setBackgroundColor(Color.parseColor("#000000"));
                Toast.makeText(MainActivity.this, "Click on button 2", Toast.LENGTH_SHORT).show();

                goToThirdActivity();
            }
        });

        Log.d("MainActivity", "onCreate");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("MainActivity", "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("MainActivity", "onStart");
    }



    public void goToSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void goToThirdActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}