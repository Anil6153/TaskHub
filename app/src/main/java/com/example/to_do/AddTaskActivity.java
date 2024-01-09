package com.example.to_do;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText setDateEditText;
    private EditText setTimeEditText;
    private Spinner prioritySpinner;
    private Spinner categorySpinner;
    private EditText descriptionEditText;
    private Button addTaskButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Initialize variables
        titleEditText = findViewById(R.id.titleEditText);
        setDateEditText = findViewById(R.id.setDateEditText);
        setTimeEditText = findViewById(R.id.setTimeEditText);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        categorySpinner = findViewById(R.id.categorySpinner);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        addTaskButton = findViewById(R.id.addTaskButton);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(AddTaskActivity.this);

        // Set up onClickListener for the date EditText
        setDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set up onClickListener for the time EditText
        setTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // Set up the priority spinner
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.priority_levels,
                android.R.layout.simple_spinner_item
        );
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        // Set up the category spinner
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.category_options,
                android.R.layout.simple_spinner_item
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from UI components
                String title = titleEditText.getText().toString();
                String setDate = setDateEditText.getText().toString();
                String setTime = setTimeEditText.getText().toString();
                String priority = prioritySpinner.getSelectedItem().toString();
                String category = categorySpinner.getSelectedItem().toString();
                String description = descriptionEditText.getText().toString();

                if(title.isEmpty()){
                    titleEditText.setError("required");
                    titleEditText.requestFocus();
                    return ;
                }

                if(setDate.isEmpty()){
                    Toast.makeText(AddTaskActivity.this, "date need to be selected!!!", Toast.LENGTH_SHORT).show();
                    setDateEditText.requestFocus();
                    return ;
                }

                if(setTime.isEmpty()){
                    Toast.makeText(AddTaskActivity.this, "time need to be selected!!!", Toast.LENGTH_SHORT).show();
                    setTimeEditText.requestFocus();
                    return ;
                }

                if(priority.isEmpty()){
//                    prioritySpinner.setError("required");
                    Toast.makeText(AddTaskActivity.this, "priority must be selected!!!", Toast.LENGTH_SHORT).show();
                    prioritySpinner.requestFocus();
                    return ;
                }

                if(category.isEmpty()){
//                    categorySpinner.setError("required");
                    Toast.makeText(AddTaskActivity.this, "category must be selected!!!", Toast.LENGTH_SHORT).show();
                    categorySpinner.requestFocus();
                    return ;
                }

//                if(description.isEmpty()){
//                    descriptionEditText.setError("required");
//                    descriptionEditText.requestFocus();
//                    return ;
//                }

                Task task = new Task(title,setDate,setTime,priority,category,description);

//                Log.d("task",
//                        task.getTitle() + task.getDate() + task.getTime() + task.getPriority() + task.getCategory() + task.getDescription());
                databaseHelper.addTaskToDatabase(task);
//                databaseHelper.scheduleNotification(task);

                // Insert data into the database
//                insertData(title, setDate, setTime, priority, category, description);

                // Start MainActivity
                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Update the EditText with the selected date
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;

                        Drawable drawable = getResources().getDrawable(R.drawable.calender);
                        setDateEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.calender, 0);
                        setDateEditText.setText(selectedDate);
                    }
                },
                year, month, day);

        // Show the dialog
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Update the EditText with the selected time
                        setTimeEditText.setText(formatTime(selectedHour, selectedMinute));
                    }
                },
                hour, minute, true); // true for 24-hour format

        // Show the dialog
        timePickerDialog.show();
    }

    private String formatTime(int hour, int minute) {
        // Format time to display in a user-friendly way
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        return DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
    }

}
