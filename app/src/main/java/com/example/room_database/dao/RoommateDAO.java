package com.example.room_database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.room_database.model.Roommate;

import java.util.List;

/**
 * The RoommateDAO manages all operations that can be used to access or modify a roommate entity.
 * The entity itself is been defined in the Model class.
 * There are the three basic operations insert, update and delete.
 * It is also possible to have custom queries.
 */
@Dao
public interface RoommateDAO {

    @Insert
    void insert(Roommate roommate);

    @Update
    void update(Roommate roommate);

    @Delete
    void delete(Roommate roommate);

    @Query("DELETE FROM roommate_table")
    void deleteAllRoomMates();

    @Query("SELECT `Roommate_Name` FROM roommate_table ORDER BY `Roommate_ID`")
    List<String> getAllRoommateNames();

    @Query("SELECT `Roommate_Name` FROM `roommate_table` WHERE `Roommate_Name` =(:roommateName)")
    String getSpecificRoommateName(String roommateName);

    @Query("SELECT `Roommate_ID` FROM `roommate_table` WHERE `Roommate_Name` =(:roommateName)")
    int getRoommateID(String roommateName);

    @Query("SELECT `Roommate_Name` FROM `roommate_table` WHERE `Roommate_ID` =(:roommateID)")
    String getRoommateName(int roommateID);

    @Query("SELECT * FROM `roommate_table` WHERE `Roommate_Name` =(:roommateName)")
    Roommate getRoommate(String roommateName);
}
