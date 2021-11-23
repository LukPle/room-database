package com.example.room_database.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.room_database.R;
import com.example.room_database.dao.RoommateDAO;
import com.example.room_database.database.AppDatabase;
import com.example.room_database.model.Task;
import com.example.room_database.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity lets the user add a task to the task table in the database.
 * The task description gets its value trough the EditText field.
 * The responsible roommate gets chosen via a spinner that is connected to the roommate table.
 * If both values are available, the task can be added to its table.
 *
 * Layout File: activity_manage_roommates.xml
 */
public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private RoommateDAO roommateDAO;

    private EditText editDescription;
    private Spinner spinnerRoommate;
    private Button buttonAddTask;

    /**
     * The method initializes the RoommateDAO for custom queries inside the Activity.
     * It also references the EditText, Spinner and Buttons of the UI.
     * @param savedInstanceState is a standard parameter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        roommateDAO = appDatabase.roommateDAO();

        editDescription = findViewById(R.id.editDescription);

        spinnerRoommate = findViewById(R.id.spinnerRoommate);
        fillSpinner();

        buttonAddTask = findViewById(R.id.button);
        buttonAddTask.setOnClickListener(this);
    }

    /**
     * Method for filling the Spinner with the correct values from the database.
     * The Spinner contains the names of all roommates in the roommate table.
     * This process is being done by an ArrayAdapter and a custom query.
     */
    private void fillSpinner() {

        List<String> allRoommateNames = roommateDAO.getAllRoommateNames();

        ArrayAdapter<String> roommateAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        roommateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoommate.setAdapter(roommateAdapter);

        roommateAdapter.addAll(allRoommateNames);
        roommateAdapter.notifyDataSetChanged();
    }

    /**
     * Method for handling the click on the Button.
     * All input from the user should be checked to its correctness.
     * If everything is fine, the task gets added to the task table in the database.
     * A TaskViewModel is being used for working with the database.
     * @param view is the UI component that was clicked on.
     */
    @Override
    public void onClick(View view) {

        TaskViewModel taskViewModel;
        taskViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance
                (this.getApplication())).get(TaskViewModel.class);

        if (editDescription.getText().toString().trim().isEmpty()) {

            Toast.makeText(AddTaskActivity.this, "Please insert a description", Toast.LENGTH_LONG).show();
        }
        else {

            // It is possible that the user tries to adds a task while having no roommates in the table
            try {

                String taskDescription = editDescription.getText().toString().trim();
                String taskRoommate = spinnerRoommate.getSelectedItem().toString();
                int roommateID = roommateDAO.getRoommateID(taskRoommate);

                // New task object with 0 as a dummy value, the task description and the ID of the roommate
                Task task = new Task(0, taskDescription, roommateID);
                taskViewModel.insert(task);
            }
            catch (Exception e) {

                Toast.makeText(AddTaskActivity.this, "No Roommate in the apartment", Toast.LENGTH_LONG).show();
            }
        }

        Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
        startActivity(intent);
    }
}