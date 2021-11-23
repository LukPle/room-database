package com.example.room_database.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.room_database.R;
import com.example.room_database.adapter.TaskAdapter;
import com.example.room_database.model.Task;
import com.example.room_database.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * This app provides a cleaning plan for managing cleaning tasks in an apartment.
 * The Activity shows a RecyclerView list of different tasks.
 * It is possible to add tasks via a FloatingActionButton.
 * There is also MenuItem in the ActionBar which lets the user manage the roommates.
 * Roommates can be added or removed via a specific Activity.
 *
 * Tutorial for checking the current database tables:
 * 1. Install DB Browser for SQLite
 * 2. Go into the Device File Explorer
 * 3. Navigate data > data > name of the app > databases
 * 4. Save all three files
 * 5. Open the file app_database in the DB Browser
 *
 * Layout File: activity_main.xml
 * Layout File for the menu: menu_actionbar.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TaskViewModel taskViewModel;

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private FloatingActionButton floatingActionButton;

    /**
     * This method takes care of the RecyclerView.
     * The Adapter fills the RecyclerView with the content of the database and sets the views below each other.
     * A TaskViewModel is being used for working with the database.
     * @param savedInstanceState is a standard parameter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);

        taskViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance
                (this.getApplication())).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {

            /**
             * This is where the Adapter gets information about the database table of tasks.
             * The Observer triggers an update of the Adapter with every change in the database table.
             * @param tasks is the List with all tasks from the task table.
             */
            @Override
            public void onChanged(@Nullable List<Task> tasks) {

                taskAdapter.setTasks(tasks);
            }
        });

        floatingActionButton = findViewById(R.id.button_addEntry);
        floatingActionButton.setOnClickListener(this);
    }

    /**
     * The ActionBar with all MenuItems gets created.
     * @param menu is the menu of this screen.
     * @return true for completing the process.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    /**
     * Method for handling the click on MenuItems in the ActionBar.
     * @param item is the item that was clicked on.
     * @return true for completing the process.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_roommates) {

            Intent intent = new Intent(MainActivity.this, ManageRoommatesActivity.class);
            startActivity(intent);
            return true;
        }
        else {

            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A click on the Floating ActionButton should open the corresponding AddTaskActivity.
     * @param view is the UI component that was clicked on.
     */
    @Override
    public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }
}