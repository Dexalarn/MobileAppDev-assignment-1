package com.example.example1;

import android.os.Bundle;
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
        setContentView(R.layout.activity_main);

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editAmount = findViewById(R.id.editAmount);
        textSummary = findViewById(R.id.textSummary);

        Button btnSubmit = findViewById(R.id.buttonSubmit);
        Button btnClear = findViewById(R.id.buttonClear);

        // Restore data after rotation
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

        btnSubmit.setOnClickListener(v -> {

            String id = editId.getText().toString();
            String name = editName.getText().toString();

            if (id.isEmpty() || name.isEmpty()
                    || editPrice.getText().toString().isEmpty()
                    || editAmount.getText().toString().isEmpty()) {

                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(editPrice.getText().toString());
            int amount = Integer.parseInt(editAmount.getText().toString());

            products.add(new Product(id, name, price, amount));

            updateSummary();
        });

        btnClear.setOnClickListener(v -> {
            editId.setText("");
            editName.setText("");
            editPrice.setText("");
            editAmount.setText("");
        });
    }

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