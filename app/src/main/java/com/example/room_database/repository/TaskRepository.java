package com.example.room_database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.room_database.dao.TaskDAO;
import com.example.room_database.database.AppDatabase;
import com.example.room_database.model.Task;

import java.util.List;

/**
 * A Repository is an abstraction layer between data sources and the other parts of the app.
 * The Repository gets data from the database and transforms it into tasks that can be called by a ViewModel.
 */
public class TaskRepository {

    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;

    /**
     * Constructor for initializing the DAO and the List.
     * @param application is the global application state.
     */
    public TaskRepository(Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        taskDAO = database.taskDAO();
        allTasks = taskDAO.getAllTasks();
    }

    public void insert(Task task) {

        new InsertTaskAsyncTask(taskDAO).execute(task);
    }

    public void update(Task task) {

        new UpdateTaskAsyncTask(taskDAO).execute(task);
    }

    public void delete(Task task) {

        new DeleteTaskAsyncTask(taskDAO).execute(task);
    }

    public LiveData<List<Task>> getAllTasks() {

        return allTasks;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDAO taskDAO;

        private InsertTaskAsyncTask(TaskDAO taskDAO) {

            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... task) {

            taskDAO.insert(task[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDAO taskDAO;

        private UpdateTaskAsyncTask(TaskDAO taskDAO) {

            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... task) {

            taskDAO.update(task[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDAO taskDAO;

        private DeleteTaskAsyncTask(TaskDAO taskDAO) {

            this.taskDAO = taskDAO;
        }

        @Override
        protected Void doInBackground(Task... task) {

            taskDAO.delete(task[0]);
            return null;
        }
    }
}
