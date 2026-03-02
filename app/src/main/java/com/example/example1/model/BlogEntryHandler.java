package com.example.example1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlogEntryHandler {

    private List<BlogEntry> entries = new ArrayList<>();

    public void addEntry(BlogEntry entry) {
        entries.add(0, entry); // newest first
    }

    public List<BlogEntry> getAllEntries() {
        return entries;
    }

    public List<BlogEntry> searchByText(String text) {
        List<BlogEntry> result = new ArrayList<>();
        for (BlogEntry entry : entries) {
            if (entry.searchByText(text)) {
                result.add(entry);
            }
        }
        return result;
    }

    public List<BlogEntry> searchByDate(String date) {
        List<BlogEntry> result = new ArrayList<>();
        for (BlogEntry entry : entries) {
            if (entry.searchByDate(date)) {
                result.add(entry);
            }
        }
        return result;
    }
}