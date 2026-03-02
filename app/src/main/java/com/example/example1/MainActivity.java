package com.example.example1;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example1.model.BlogEntry;
import com.example.example1.model.BlogEntryHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editName, editComment, editSearchText, editSearchDate;
    private TextView textEntries;
    private BlogEntryHandler handler = new BlogEntryHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editComment = findViewById(R.id.editComment);
        editSearchText = findViewById(R.id.editSearchText);
        editSearchDate = findViewById(R.id.editSearchDate);
        textEntries = findViewById(R.id.textEntries);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        Button buttonSearch = findViewById(R.id.buttonSearch);

        buttonSubmit.setOnClickListener(v -> submitEntry());
        buttonSearch.setOnClickListener(v -> searchEntries());

        addFieldResetListener(editName);
        addFieldResetListener(editComment);
    }

    private void submitEntry() {
        String name = editName.getText().toString().trim();
        String comment = editComment.getText().toString().trim();

        boolean valid = true;

        if (name.isEmpty()) {
            editName.setBackgroundColor(Color.RED);
            valid = false;
        }

        if (comment.isEmpty()) {
            editComment.setBackgroundColor(Color.RED);
            valid = false;
        }

        if (!valid) return;

        BlogEntry entry = new BlogEntry(name, comment);
        handler.addEntry(entry);

        displayEntries(handler.getAllEntries());

        editName.setText("");
        editComment.setText("");
    }

    private void searchEntries() {
        String text = editSearchText.getText().toString().trim();
        String date = editSearchDate.getText().toString().trim();

        List<BlogEntry> result;

        if (!text.isEmpty()) {
            result = handler.searchByText(text);
        } else if (!date.isEmpty()) {
            result = handler.searchByDate(date);
        } else {
            result = handler.getAllEntries();
        }

        displayEntries(result);
    }

    private void displayEntries(List<BlogEntry> entries) {
        StringBuilder sb = new StringBuilder();

        int count = entries.size();
        for (BlogEntry entry : entries) {
            sb.append("Entry #").append(count--).append("\n");
            sb.append(entry.toString()).append("\n\n");
        }

        textEntries.setText(sb.toString());
    }

    private void addFieldResetListener(EditText field) {
        field.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                field.setBackgroundColor(Color.TRANSPARENT);
            }
            public void afterTextChanged(Editable s) {}
        });
    }
}