package com.example.room_database.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.room_database.dao.TaskDAO;
import com.example.room_database.dao.RoommateDAO;
import com.example.room_database.model.Task;
import com.example.room_database.model.Roommate;

/**
 * This is the Room Database itself.
 * It connects all tables and DAOs.
 */
@Database(entities = {Roommate.class, Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Variable for the database
    private static AppDatabase instance;

    // These methods are used for accessing the DAOs
    public abstract RoommateDAO roommateDAO();
    public abstract TaskDAO taskDAO();

    /**
     * This is where the database gets created.
     * The AppDatabase can only have one instance.
     * The creation can only take place if there is not an existing instance of the database in the app.
     * @param context contains information about the environment of the application.
     * @return the instance of the database.
     */
    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {

            instance = androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    /**
     * Method for accessing Lifecycle methods of the database.
     * It is possible to overwrite the onCreate method of the database.
     */
    private static RoomDatabase.Callback roomCallback =  new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    /**
     * Method for populating the database with data right from the creation.
     * This example does not add any data in the time of the database creation.
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private RoommateDAO roommateDAO;
        private TaskDAO taskDAO;

        private PopulateDbAsyncTask(AppDatabase db) {

            roommateDAO = db.roommateDAO(); taskDAO = db.taskDAO();
        }


        @Override
        protected Void doInBackground(Void... voids) {

            // You can add entries of the database right here

            return null;
        }
    }
}
