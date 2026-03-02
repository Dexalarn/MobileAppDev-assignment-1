package com.example.example1;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textRandom;
    private TextView textPrevious;

    private Handler handler = new Handler();
    private Runnable runnable;

    private Random random = new Random();
    private int currentNumber = 0;

    private static final String KEY_TIME = "saved_time";
    private static final String KEY_PREVIOUS = "saved_previous";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textRandom = findViewById(R.id.textRandom);
        textPrevious = findViewById(R.id.textPrevious);

        // Restore saved data after rotation
        if (savedInstanceState != null) {
            String savedTime = savedInstanceState.getString(KEY_TIME);
            int previousNumber = savedInstanceState.getInt(KEY_PREVIOUS);

            textPrevious.setText(String.valueOf(previousNumber));

            Toast.makeText(this,
                    "Orientation changed at: " + savedTime,
                    Toast.LENGTH_LONG).show();
        }

        startRandomGenerator();
    }

    private void startRandomGenerator() {
        runnable = new Runnable() {
            @Override
            public void run() {
                currentNumber = random.nextInt(1000); // 0–999
                textRandom.setText(String.valueOf(currentNumber));

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(runnable);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save current date & time
        String currentTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.getDefault()).format(new Date());

        outState.putString(KEY_TIME, currentTime);

        // Save last random number before rotation
        outState.putInt(KEY_PREVIOUS, currentNumber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}