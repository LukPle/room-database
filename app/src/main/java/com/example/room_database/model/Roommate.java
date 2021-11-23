package com.example.room_database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The Model class creates a table in the Room Database.
 * It symbolizes a specific entity, in this case a roommate.
 *
 * The table has columns for different attributes.
 * The constructor works as the setter for the columns.
 * Every column also needs a getter method.
 */
@Entity(tableName = "roommate_table")
public class Roommate {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Roommate_ID")
    private int roommateNumber;

    @ColumnInfo(name = "Roommate_Name")
    private String roommateName;

    public Roommate(int roommateNumber, String roommateName) {

        this.roommateNumber = roommateNumber;
        this.roommateName = roommateName;
    }

    public int getRoommateNumber() {

        return roommateNumber;
    }

    public String getRoommateName() {

        return roommateName;
    }
}
