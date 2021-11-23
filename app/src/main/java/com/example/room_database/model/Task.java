package com.example.room_database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The Model class creates a table in the Room Database.
 * It symbolizes a specific entity, in this case a task.
 *
 * The table has columns for different attributes.
 * The constructor works as the setter for the columns.
 * Every column also needs a getter method.
 */
@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "Task_ID")
    private int taskNumber;

    @ColumnInfo(name = "Task_Description")
    private String taskDescription;

    @ColumnInfo(name = "Roommate")
    private int roommate;

    public Task(int taskNumber, String taskDescription, int roommate) {

        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.roommate = roommate;
    }

    public int getTaskNumber() {

        return taskNumber;
    }

    public String getTaskDescription() {

        return taskDescription;
    }

    public int getRoommate() {

        return roommate;
    }
}
