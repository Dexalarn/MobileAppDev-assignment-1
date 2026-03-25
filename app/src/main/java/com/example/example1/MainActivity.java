package com.example.example1;

import com.example.example1.model.Person;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> persons = new ArrayList<>();

    private ArrayList<String> firstList = new ArrayList<>();
    private ArrayList<String> lastList = new ArrayList<>();
    private ArrayList<String> phoneList = new ArrayList<>();

    private ArrayAdapter<String> firstAdapter, lastAdapter, phoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editFirst = findViewById(R.id.editFirst);
        EditText editLast = findViewById(R.id.editLast);
        EditText editPhone = findViewById(R.id.editPhone);

        AutoCompleteTextView searchFirst = findViewById(R.id.searchFirst);
        AutoCompleteTextView searchLast = findViewById(R.id.searchLast);
        AutoCompleteTextView searchPhone = findViewById(R.id.searchPhone);

        TextView textResult = findViewById(R.id.textResult);

        Button buttonAdd = findViewById(R.id.buttonAdd);

        // Adapters
        firstAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, firstList);
        lastAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lastList);
        phoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, phoneList);

        searchFirst.setAdapter(firstAdapter);
        searchLast.setAdapter(lastAdapter);
        searchPhone.setAdapter(phoneAdapter);

        // ADD PERSON
        buttonAdd.setOnClickListener(v -> {

            String f = editFirst.getText().toString();
            String l = editLast.getText().toString();
            String p = editPhone.getText().toString();

            if (f.isEmpty() || l.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Person person = new Person(f, l, p);
            persons.add(person);

            // IMPORTANT: no spaces
            firstList.add(f + "_" + l + "_" + p);
            lastList.add(l + "_" + f + "_" + p);
            phoneList.add(p + "_" + f + "_" + l);

            firstAdapter.notifyDataSetChanged();
            lastAdapter.notifyDataSetChanged();
            phoneAdapter.notifyDataSetChanged();
        });

        // CLICK HANDLER
        AdapterView.OnItemClickListener listener = (parent, view, position, id) -> {

            String item = (String) parent.getItemAtPosition(position);
            String[] parts = item.split("_");

            if (parts.length < 3) return; // safety

            String first = "";
            String last = "";
            String phone = "";

            if (parent.getId() == R.id.searchFirst) {
                first = parts[0];
                last = parts[1];
                phone = parts[2];

            } else if (parent.getId() == R.id.searchLast) {
                first = parts[1];
                last = parts[0];
                phone = parts[2];

            } else if (parent.getId() == R.id.searchPhone) {
                first = parts[1];
                last = parts[2];
                phone = parts[0];
            }

            textResult.setText(
                    "First name=" + first + ", Last name=" + last + ", Phone=" + phone
            );
        };

        searchFirst.setOnItemClickListener(listener);
        searchLast.setOnItemClickListener(listener);
        searchPhone.setOnItemClickListener(listener);
    }
}