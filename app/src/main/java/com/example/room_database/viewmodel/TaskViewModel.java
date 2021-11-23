package com.example.room_database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.room_database.model.Task;
import com.example.room_database.repository.TaskRepository;

import java.util.List;

/**
 * The VieModel connects the Repository to the Activity.
 * This enables the Activity to access the data from the database.
 */
public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    /**
     * This is where you can pass the application down to the database.
     * The constructor also initializes the Repository and the List of all tasks.
     * @param application is the application.
     */
    public TaskViewModel(@NonNull Application application) {

        super(application);
        repository =  new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task) {

        repository.insert(task);
    }

    public void update(Task task) {

        repository.delete(task);
    }

    public void delete(Task task) {

        repository.delete(task);
    }

    public LiveData<List<Task>> getAllTasks() {

        return allTasks;
    }
}
