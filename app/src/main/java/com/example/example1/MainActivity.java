package com.example.example1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String tag="EVH_Demo: ";
    long lastTime = 0; // time stamp of previous event
    //form variables
    EditText Form_name, Form_email , Form_phone, Form_feedback;
    Button btnSubmit, btnReset;

    private void longWithTime(String methodName){
        long currentTime = System.currentTimeMillis();
        if (lastTime != 0) {
            long elapsed = currentTime - lastTime;
            Log.d(tag, methodName + " - elapsed time: " + elapsed + " ms");
        } else {
            Log.d(tag, methodName);
        }
        lastTime = currentTime;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longWithTime("onCreate()");

        Form_name = findViewById(R.id.editTextName);
        Form_email = findViewById(R.id.editTextEmail);
        Form_phone = findViewById(R.id.editTextPhone);
        Form_feedback = findViewById(R.id.editFeedback);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnReset = findViewById(R.id.buttonReset);

        btnSubmit.setOnClickListener(v -> {

        });
        btnReset.setOnClickListener(v -> {
            Form_name.setText("");
            Form_email.setText("");
            Form_phone.setText("");
            Form_feedback.setText("");
        });
        btnSubmit.setOnClickListener(v -> {
            Log.d("FORM", "Name: " + Form_name.getText());
            Log.d("FORM", "E-mail: " + Form_email.getText());
            Log.d("FORM", "Phone: " + Form_phone.getText());
            Log.d("FORM", "Feedback: " + Form_feedback.getText());
        });


    }
    protected void onStart() {
        super.onStart();
        longWithTime("onStart()");
    }
    protected void onRestart() {
        super.onRestart();
        longWithTime("onRestart()");
    }
    protected void onResume() {
        super.onResume();
        longWithTime("onResume()");
    }
    protected void onPause() {
        super.onPause();
        longWithTime("onStop()");
    }
    protected void onStop() {
        super.onStop();
        longWithTime("onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        longWithTime("onDestroy()");
    }
}