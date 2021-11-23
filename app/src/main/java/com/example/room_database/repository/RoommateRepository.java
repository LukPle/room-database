package com.example.room_database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.room_database.dao.RoommateDAO;
import com.example.room_database.database.AppDatabase;
import com.example.room_database.model.Roommate;

import java.util.List;

/**
 * A Repository is an abstraction layer between data sources and the other parts of the app.
 * The Repository gets data from the database and transforms it into tasks that can be called by a ViewModel.
 */
public class RoommateRepository {

    private RoommateDAO roommateDAO;
    private List<String> allRoommateNames;

    /**
     * Constructor for initializing the DAO and the List.
     * @param application is the global application state.
     */
    public RoommateRepository(Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        roommateDAO = database.roommateDAO();
        allRoommateNames = roommateDAO.getAllRoommateNames();
    }

    public void insert(Roommate roommate) {

        new RoommateRepository.InsertRoommateAsyncTask(roommateDAO).execute(roommate);
    }

    public void update(Roommate roommate) {

        new RoommateRepository.UpdateRoommateAsyncTask(roommateDAO).execute(roommate);
    }

    public void delete(Roommate roommate) {

        new RoommateRepository.DeleteRoommateAsyncTask(roommateDAO).execute(roommate);
    }

    public List<String> getAllRoommateNames() {

        return allRoommateNames;
    }

    private static class InsertRoommateAsyncTask extends AsyncTask<Roommate, Void, Void> {

        private RoommateDAO roommateDAO;

        private InsertRoommateAsyncTask(RoommateDAO roommateDAO) {

            this.roommateDAO = roommateDAO;
        }

        @Override
        protected Void doInBackground(Roommate... roommate) {

            roommateDAO.insert(roommate[0]);
            return null;
        }
    }

    private static class UpdateRoommateAsyncTask extends AsyncTask<Roommate, Void, Void> {

        private RoommateDAO roommateDAO;

        private UpdateRoommateAsyncTask(RoommateDAO roommateDAO) {

            this.roommateDAO = roommateDAO;
        }

        @Override
        protected Void doInBackground(Roommate... roommate) {

            roommateDAO.update(roommate[0]);
            return null;
        }
    }

    private static class DeleteRoommateAsyncTask extends AsyncTask<Roommate, Void, Void> {

        private RoommateDAO roommateDAO;

        private DeleteRoommateAsyncTask(RoommateDAO roommateDAO) {

            this.roommateDAO = roommateDAO;
        }

        @Override
        protected Void doInBackground(Roommate... roommate) {

            roommateDAO.delete(roommate[0]);
            return null;
        }
    }
}
