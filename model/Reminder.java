package com.jdbc.model;

public class Reminder {

    private String title;
    private String description;
    private String date;
    private String status;
    private String priority;   // HIGH / MEDIUM / LOW
    private String category;   // Work, Personal, Health, etc.

    // Original constructor (still works — defaults applied for new fields)
    public Reminder(String title, String description, String date, String status) {
        this.title       = title;
        this.description = description;
        this.date        = date;
        this.status      = status;
        this.priority    = "MEDIUM";
        this.category    = "General";
    }

    // New full constructor
    public Reminder(String title, String description, String date,
                    String status, String priority, String category) {
        this.title       = title;
        this.description = description;
        this.date        = date;
        this.status      = status;
        this.priority    = priority;
        this.category    = category;
    }

    // Original getters
    public String getTitle()       { return title; }
    public String getDescription() { return description; }
    public String getDate()        { return date; }
    public String getStatus()      { return status; }

    // New getters
    public String getPriority() { return priority; }
    public String getCategory() { return category; }

    // New setter
    public void setStatus(String status) { this.status = status; }
}