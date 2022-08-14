package com.ussd.ussdcode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new android.os.Handler(Looper.getMainLooper()).postDelayed(() -> {
                    startActivity(new Intent(MainActivity.this, Sample.class));
                    finish();
                },3000);
    }
}