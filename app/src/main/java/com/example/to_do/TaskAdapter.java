package com.example.to_do;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private Context context;
    private List<Task> taskList;
    private DatabaseHelper databaseHelper;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bind(task);

        // Attach a click listener to the CheckBox
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCompletionDialog(holder.getAdapterPosition());
            }
        });
    }

    private void showCompletionDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Task Completion");
        builder.setMessage("Have you completed the task?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the task from the database
                deleteTaskFromDatabase(position);

                // Remove the task from the RecyclerView
                taskList.remove(position);
                notifyItemRemoved(position);



            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Unselect the checkbox in the current holder
//                holder.checkBox.setChecked(false);
            }
        });
        builder.create().show();
    }

    private void deleteTaskFromDatabase(int position) {
        // Check if the position is valid
        if (position >= 0 && position < taskList.size()) {
            Task taskToDelete = taskList.get(position);

            // Use the task ID to delete the task from the database
            long taskId = taskToDelete.getId(); // Replace with the actual method to get the task ID

            // Assuming you have a deleteTask method in your DatabaseHelper
            databaseHelper.deleteTask(taskId);

            // Notify the adapter that the data set has changed
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
