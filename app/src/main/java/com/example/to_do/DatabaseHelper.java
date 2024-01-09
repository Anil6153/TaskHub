package com.example.to_do;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_PRIORITY = "priority";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_DESCRIPTION = "description";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DATE + " DATE, " +
                    COLUMN_TIME + " TIME, " +
                    COLUMN_PRIORITY + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Add a new task to the database
    public long addTaskToDatabase(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTitle());
        values.put(COLUMN_DATE, (task.getDate()));
        values.put(COLUMN_TIME, (task.getTime()));
        values.put(COLUMN_PRIORITY, task.getPriority());
        values.put(COLUMN_CATEGORY, task.getCategory());
        values.put(COLUMN_DESCRIPTION, task.getDescription());

        long result = db.insert(TABLE_NAME, null, values);
        if(result != -1){
            Log.d("add task to db", "task added to db succesfully!" + result);
        }
        db.close();
        return result;
    }

    // Get all tasks from the database
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                task.setDate((cursor.getString(cursor.getColumnIndex(COLUMN_DATE))));
                task.setTime((cursor.getString(cursor.getColumnIndex(COLUMN_TIME))));
                task.setPriority(cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY)));
                task.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));

                taskList.add(task);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

    // Helper methods for formatting Date and Time
    private String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    private String formatTimeToString(Time time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(time);
    }

    private Date parseStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Time parseStringToTime(String timeString) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = timeFormat.parse(timeString);
            return new Time(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
