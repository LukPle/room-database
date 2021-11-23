package com.example.room_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room_database.model.Task;

import java.util.List;

/**
 * The TaskDAO manages all operations that can be used to access or modify a task entity.
 * The entity itself is been defined in the Model class.
 * There are the three basic operations insert, update and delete.
 * It is also possible to have custom queries.
 */
@Dao
public interface TaskDAO {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table ORDER BY `Task_ID`")
    LiveData<List<Task>> getAllTasks();
}
