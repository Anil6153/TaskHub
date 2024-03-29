package com.example.to_do;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    TextView titleTextView, dateTextView, timeTextView, priorityTextView, categoryTextView, descriptionTextView,taskStatusTextView;
    Spinner taskStatusSpinner;
    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTaskTextView);
        dateTextView = itemView.findViewById(R.id.dateTaskTextView);
        timeTextView = itemView.findViewById(R.id.timeTaskTextView);
        priorityTextView = itemView.findViewById(R.id.priorityTaskTextView);
        categoryTextView = itemView.findViewById(R.id.categoryTaskTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTaskTextView);
        checkBox = itemView.findViewById(R.id.checkBox);
//        taskStatusTextView = itemView.findViewById(R.id.taskStatusTextView);
    }

    public void bind(Task task) {
        titleTextView.setText(task.getTitle());
        dateTextView.setText(task.getDate().toString());
        timeTextView.setText(task.getTime().toString());
        priorityTextView.setText("priority : " + task.getPriority());
        categoryTextView.setText("category : " + task.getCategory());
        descriptionTextView.setText(task.getDescription());
//        taskStatusTextView.setText(task.getTaskStatus());
    }


}
