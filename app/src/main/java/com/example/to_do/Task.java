package com.example.to_do;

import java.sql.Time;
import java.util.Date;

public class Task {
    private boolean checked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private String title;

    private long id = 100;
    private String date;
    private String time;
    private String priority;
    private String category;

    private String description;
    public Task() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task(String title, String date, String time, String priority, String category, String description) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.category = category;
        this.description = description;
    }

}
