package com.example.example1;

// Import your custom Person class
import com.example.example1.model.Person;

// Android core classes
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;



// Main activity class (entry point of your app screen)
public class MainActivity extends AppCompatActivity {

    // 🔹 List to store actual Person objects (data model)
    private ArrayList<Person> persons = new ArrayList<>();

    // 🔹 Lists used by AutoCompleteTextView (search source)
    // Each list stores combined strings for different search types
    private ArrayList<String> firstList = new ArrayList<>();
    private ArrayList<String> lastList = new ArrayList<>();
    private ArrayList<String> phoneList = new ArrayList<>();

    // 🔹 Adapters connects listss to UI AutoCompleteTextView
    private ArrayAdapter<String> firstAdapter, lastAdapter, phoneAdapter;

    // 🔹 Search fields
    private AutoCompleteTextView searchFirst, searchLast, searchPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // CREATE RUNTIME UI


        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        // Layout parameters for all views
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );


        // 🔹 INPUTS
        // First name input
        EditText editFirst = new EditText(this);
        editFirst.setHint("First Name"); // placeholder text
        layout.addView(editFirst, params);

        // Last name input
        EditText editLast = new EditText(this);
        editLast.setHint("Last Name");
        layout.addView(editLast, params);

        // Phone input
        EditText editPhone = new EditText(this);
        editPhone.setHint("Phone Number");
        layout.addView(editPhone, params);

        // Button to add a person
        Button buttonAdd = new Button(this);
        buttonAdd.setText("Add Person");
        layout.addView(buttonAdd, params);


        // 🔹 SEARCH FIELDS

        // Search by first name
        searchFirst = new AutoCompleteTextView(this);
        searchFirst.setHint("Search by First Name");
        layout.addView(searchFirst, params);

        // Search by last name
        searchLast = new AutoCompleteTextView(this);
        searchLast.setHint("Search by Last Name");
        layout.addView(searchLast, params);

        // Search by phone
        searchPhone = new AutoCompleteTextView(this);
        searchPhone.setHint("Search by Phone");
        layout.addView(searchPhone, params);


        // 🔹 RESULT DISPLAY
        TextView textResult = new TextView(this);
        textResult.setTextSize(18); // bigger font
        layout.addView(textResult, params);

        // Set this layout as the screen
        setContentView(layout);


        // 🔹 ADAPTERS

        // Adapter connects list → dropdown suggestions
        firstAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                firstList
        );

        lastAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                lastList
        );

        phoneAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                phoneList
        );

        // Attach adapters to search fields
        searchFirst.setAdapter(firstAdapter);
        searchLast.setAdapter(lastAdapter);
        searchPhone.setAdapter(phoneAdapter);

        // Start showing suggestions after 1 character
        searchFirst.setThreshold(1);
        searchLast.setThreshold(1);
        searchPhone.setThreshold(1);


        // 🔹 ADD PERSON LOGIC

        buttonAdd.setOnClickListener(v -> {

            // Get user input
            String f = editFirst.getText().toString().trim();
            String l = editLast.getText().toString().trim();
            String p = editPhone.getText().toString().trim();

            // Validation
            if (f.isEmpty() || l.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create Person object and store it
            Person person = new Person(f, l, p);
            persons.add(person);

            // Convert to lowercase for search consistency
            String fLower = f.toLowerCase();
            String lLower = l.toLowerCase();



            // First name search
            firstList.add(fLower + "_" + l + "_" + p);

            // Last name search
            lastList.add(lLower + "_" + f + "_" + p);

            // Phone search
            phoneList.add(p + "_" + f + "_" + l);

            // Notify adapters → update dropdown
            firstAdapter.notifyDataSetChanged();
            lastAdapter.notifyDataSetChanged();
            phoneAdapter.notifyDataSetChanged();

            // Clear input fields
            editFirst.setText("");
            editLast.setText("");
            editPhone.setText("");
        });

        

        // 🔍 Search by FIRST NAME
        searchFirst.setOnItemClickListener((parent, view, position, id) -> {

            // Get selected item
            String item = (String) parent.getItemAtPosition(position);

            // Split data
            String[] parts = item.split("_");

            if (parts.length < 3) return;

            // Display nicely
            textResult.setText(
                    "First name=" + parts[0] +
                            ", Last name=" + parts[1] +
                            ", Phone=" + parts[2]
            );
        });

        // 🔍 Search by LAST NAME
        searchLast.setOnItemClickListener((parent, view, position, id) -> {

            String item = (String) parent.getItemAtPosition(position);
            String[] parts = item.split("_");

            if (parts.length < 3) return;

            textResult.setText(
                    "First name=" + parts[1] +
                            ", Last name=" + parts[0] +
                            ", Phone=" + parts[2]
            );
        });

        // 🔍 Search by PHONE
        searchPhone.setOnItemClickListener((parent, view, position, id) -> {

            String item = (String) parent.getItemAtPosition(position);
            String[] parts = item.split("_");

            if (parts.length < 3) return;

            textResult.setText(
                    "First name=" + parts[1] +
                            ", Last name=" + parts[2] +
                            ", Phone=" + parts[0]
            );
        });
    }
}