package com.example.room_database.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.room_database.model.Roommate;
import com.example.room_database.repository.RoommateRepository;

import java.util.List;

/**
 * The VieModel connects the Repository to the Activity.
 * This enables the Activity to access the data from the database.
 */
public class RoommateViewModel extends AndroidViewModel {

    private RoommateRepository repository;
    private List<String> allRoommateNames;

    /**
     * This is where you can pass the application down to the database.
     * The constructor also initializes the Repository and the List of all roommate names.
     * @param application is the application.
     */
    public RoommateViewModel(@NonNull Application application) {

        super(application);
        repository =  new RoommateRepository(application);
        allRoommateNames = repository.getAllRoommateNames();
    }

    public void insert(Roommate roommate) {

        repository.insert(roommate);
    }

    public void update(Roommate roommate) {

        repository.delete(roommate);
    }

    public void delete(Roommate roommate) {

        repository.delete(roommate);
    }

    public List<String> getAllRoommateNames() {

        return allRoommateNames;
    }
}
