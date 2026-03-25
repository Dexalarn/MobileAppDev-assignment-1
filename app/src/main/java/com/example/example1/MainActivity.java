package com.example.example1;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create layout programmatically
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,40,40,40);

        // Create TextView
        TextView label = new TextView(this);
        label.setText("Type your favourite number");
        label.setTextSize(20);

        // Create EditText
        EditText input = new EditText(this);
        input.setHint("Enter number");
        input.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        input.setImeOptions(EditorInfo.IME_ACTION_GO);

        // Detect Enter / Go key
        input.setOnEditorActionListener((v, actionId, event) -> {

            if (actionId == EditorInfo.IME_ACTION_GO ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                String userText = input.getText().toString();

                if (!userText.isEmpty()) {

                    int userNumber = Integer.parseInt(userText);

                    Random random = new Random();
                    int randomNumber = random.nextInt(10); // 0–9

                    if (userNumber == randomNumber) {
                        Toast.makeText(this,
                                "Match! Random number was " + randomNumber,
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this,
                                "Not the same. Random number was " + randomNumber,
                                Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            }
            return false;
        });

        // Add views to layout
        layout.addView(label);
        layout.addView(input);

        // Set layout as content
        setContentView(layout);
    }
}