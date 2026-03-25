package com.example.example1;

import android.os.Bundle;
import android.text.InputType;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> products = new ArrayList<>();

    private EditText editId, editName, editPrice, editAmount;
    private TextView textSummary;

    private static final String KEY_DATA = "products_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ROOT ScrollView
        ScrollView scrollView = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 30, 30, 30);

        scrollView.addView(layout);

        // CREATE INPUT FIELDS
        editId = new EditText(this);
        editId.setHint("Product ID");

        editName = new EditText(this);
        editName.setHint("Product Name");

        editPrice = new EditText(this);
        editPrice.setHint("Unit Price");
        editPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        editAmount = new EditText(this);
        editAmount.setHint("Amount");
        editAmount.setInputType(InputType.TYPE_CLASS_NUMBER);

        // BUTTONS
        Button btnSubmit = new Button(this);
        btnSubmit.setText("Submit");

        Button btnClear = new Button(this);
        btnClear.setText("Clear");

        // SUMMARY
        textSummary = new TextView(this);
        textSummary.setTextSize(18);

        // ADD TO LAYOUT
        layout.addView(editId);
        layout.addView(editName);
        layout.addView(editPrice);
        layout.addView(editAmount);
        layout.addView(btnSubmit);
        layout.addView(btnClear);
        layout.addView(textSummary);

        setContentView(scrollView);

        // 🔁 RESTORE DATA AFTER ROTATION
        if (savedInstanceState != null) {
            ArrayList<String> saved = savedInstanceState.getStringArrayList(KEY_DATA);

            if (saved != null) {
                for (String s : saved) {
                    String[] parts = s.split(";");
                    products.add(new Product(
                            parts[0],
                            parts[1],
                            Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3])
                    ));
                }
                updateSummary();
            }
        }

        // SUBMIT BUTTON
        btnSubmit.setOnClickListener(v -> {

            String id = editId.getText().toString();
            String name = editName.getText().toString();
            String priceStr = editPrice.getText().toString();
            String amountStr = editAmount.getText().toString();

            if (id.isEmpty() || name.isEmpty() || priceStr.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);
            int amount = Integer.parseInt(amountStr);

            products.add(new Product(id, name, price, amount));

            updateSummary();
        });

        // CLEAR BUTTON
        btnClear.setOnClickListener(v -> {
            editId.setText("");
            editName.setText("");
            editPrice.setText("");
            editAmount.setText("");
        });
    }

    // UPDATE UI
    private void updateSummary() {
        StringBuilder sb = new StringBuilder();

        for (Product p : products) {
            sb.append(p.toString()).append("\n");
        }

        textSummary.setText(sb.toString());
    }

    // SAVE DATA
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<String> data = new ArrayList<>();

        for (Product p : products) {
            data.add(p.getId() + ";" + p.getName() + ";" + p.getPrice() + ";" + p.getAmount());
        }

        outState.putStringArrayList(KEY_DATA, data);
    }
}