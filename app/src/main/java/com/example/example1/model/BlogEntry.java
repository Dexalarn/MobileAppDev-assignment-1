package com.example.example1.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BlogEntry {

    private String userName;
    private String comment;
    private Date date;

    public BlogEntry(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
        this.date = new Date();
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public boolean searchByText(String text) {
        return userName.toLowerCase().contains(text.toLowerCase())
                || comment.toLowerCase().contains(text.toLowerCase());
    }

    public boolean searchByDate(String dateString) {
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String entryDate = sdf.format(date);
        return entryDate.equals(dateString);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault());

        return sdf.format(date)
                + "\nUser: " + userName
                + "\nComment: " + comment;
    }
}