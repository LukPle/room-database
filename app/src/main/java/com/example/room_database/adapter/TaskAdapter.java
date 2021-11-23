package com.example.room_database.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_database.R;
import com.example.room_database.dao.RoommateDAO;
import com.example.room_database.database.AppDatabase;
import com.example.room_database.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for handling the RecyclerView in the MainActivity.
 *
 * Layout File for one view: list_element.xml
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> tasks = new ArrayList<>();
    private View view;

    class TaskHolder extends RecyclerView.ViewHolder {

        private TextView textViewTask;
        private TextView textViewRoommate;

        public TaskHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            textViewTask = itemView.findViewById(R.id.text_task);
            textViewRoommate = itemView.findViewById(R.id.text_roommate);
        }
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false);

        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        // Getting the DAO for a custom query
        AppDatabase appDatabase = AppDatabase.getInstance(view.getContext());
        RoommateDAO roommateDAO = appDatabase.roommateDAO();

        Task currentTask = tasks.get(position);
        holder.textViewTask.setText(currentTask.getTaskDescription());

        // The table only contains the ID of the roommate but the RecyclerView should display the name
        holder.textViewRoommate.setText(roommateDAO.getRoommateName(currentTask.getRoommate()));
    }

    @Override
    public int getItemCount() {

        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {

        this.tasks = tasks;
        notifyDataSetChanged();
    }
}
