package com.example.room_database.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_database.R;
import com.example.room_database.dao.RoommateDAO;
import com.example.room_database.database.AppDatabase;
import com.example.room_database.model.Roommate;
import com.example.room_database.viewmodel.RoommateViewModel;

/**
 * This activity enables the user to add or remove roommates in the apartment.
 * These actions affect the entries of the roommate table in the database.
 *
 * Layout File: activity_manage_roommates.xml
 */
public class ManageRoommatesActivity extends AppCompatActivity implements View.OnClickListener {

    private RoommateViewModel roommateViewModel;

    private EditText editName;
    private Button buttonAdd, buttonRemove;

    /**
     * The method initializes a RoommateViewModel for working with the database.
     * It also references the EditText field and the Buttons.
     * @param savedInstanceState is a standard parameter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_roommates);

        roommateViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance
                (this.getApplication())).get(RoommateViewModel.class);

        editName = findViewById(R.id.editName);

        buttonAdd = findViewById(R.id.addRoommate_button);
        buttonAdd.setOnClickListener(this);

        buttonRemove = findViewById(R.id.removeRoommate_button);
        buttonRemove.setOnClickListener(this);
    }

    /**
     * Method for checking whether the EditText field for the name is empty or not.
     * @return true if the field is empty and false if it contains text.
     */
    private boolean isEditNameEmpty() {

        return editName.getText().toString().trim().isEmpty();
    }

    /**
     * Method for handling Button clicks.
     * @param view is the UI component that was clicked on.
     */
    @Override
    public void onClick(View view) {

        if (isEditNameEmpty()) {

            Toast.makeText(ManageRoommatesActivity.this, "Please insert a name", Toast.LENGTH_LONG).show();
        }
        else {

            switch (view.getId()) {

                case R.id.addRoommate_button:
                    addRoommate();
                    break;

                case R.id.removeRoommate_button:
                    removeRoommate();
                    break;
            }
        }
    }

    /**
     * Method for adding a roommate to the roommate table.
     */
    private void addRoommate() {

        String name = editName.getText().toString();

        // New roommate object with 0 as a dummy value and the name from the EditText field
        Roommate roommate = new Roommate(0, name);
        roommateViewModel.insert(roommate);

        Toast.makeText(ManageRoommatesActivity.this, name + " was added to your apartment", Toast.LENGTH_LONG).show();
    }

    /**
     * Method for removing a roommate from the roommate table.
     * It initializes the RoommateDAO for custom queries.
     */
    private void removeRoommate() {

        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        RoommateDAO roommateDAO = appDatabase.roommateDAO();

        String name = editName.getText().toString();

        // Check via query if the name from EditText exists in the database
        if (roommateDAO.getSpecificRoommateName(name) == null) {

            Toast.makeText(ManageRoommatesActivity.this, "Not able to find " + name, Toast.LENGTH_LONG).show();
        }
        else {

            // Query for getting the specific roommate object
            Roommate roommate = roommateDAO.getRoommate(name);

            roommateViewModel.delete(roommate);

            Toast.makeText(ManageRoommatesActivity.this, name + " was successfully removed", Toast.LENGTH_LONG).show();
        }
    }
}