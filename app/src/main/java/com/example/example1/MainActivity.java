package com.example.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    private double value1 = 0;
    private String operation = "";
    private boolean newNumber = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        if (savedInstanceState != null) {
            display.setText(savedInstanceState.getString("display"));
            value1 = savedInstanceState.getDouble("value1");
            operation = savedInstanceState.getString("operation");
            newNumber = savedInstanceState.getBoolean("newNumber");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("display", display.getText().toString());
        outState.putDouble("value1", value1);
        outState.putString("operation", operation);
        outState.putBoolean("newNumber", newNumber);

        super.onSaveInstanceState(outState);
    }

    public void numberClick(View view) {

        Button button = (Button) view;
        String number = button.getText().toString();

        if (newNumber) {
            display.setText(number);
            newNumber = false;
        } else {
            display.append(number);
        }
    }

    public void decimalClick(View view) {

        String value = display.getText().toString();

        if (newNumber) {
            display.setText("0.");
            newNumber = false;
        } else if (!value.contains(".")) {
            display.append(".");
        }
    }

    public void operationClick(View view) {

        Button button = (Button) view;

        value1 = Double.parseDouble(display.getText().toString());
        operation = button.getText().toString();
        newNumber = true;
    }

    public void equalsClick(View view) {

        double value2 = Double.parseDouble(display.getText().toString());
        double result = 0;

        switch (operation) {
            case "+":
                result = value1 + value2;
                break;

            case "-":
                result = value1 - value2;
                break;

            case "×":
                result = value1 * value2;
                break;

            case "÷":
                result = value1 / value2;
                break;
        }

        display.setText(String.valueOf(result));
        newNumber = true;
    }

    public void percentClick(View view) {

        double value = Double.parseDouble(display.getText().toString());
        value = value / 100;
        display.setText(String.valueOf(value));
        newNumber = true;
    }

    public void deleteClick(View view) {

        String value = display.getText().toString();

        if (value.length() > 1) {
            value = value.substring(0, value.length() - 1);
        } else {
            value = "0";
            newNumber = true;
        }

        display.setText(value);
    }

    public void clearClick(View view) {

        display.setText("0");
        value1 = 0;
        operation = "";
        newNumber = true;
    }
}