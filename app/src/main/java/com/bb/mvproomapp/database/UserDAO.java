package com.bb.mvproomapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Insert
    void addNewUser(UserEntity newUser);
    // to add multiple users use the following:
    //void addNewUser(UserEntity... newUser)

    @Query("SELECT * FROM Users WHERE userName = :userName AND userPassword = :userPassword")
    UserEntity loginSelect(String userName, String userPassword);

    @Update
    void updateValue(UserEntity userEntity);

    @Delete
    void deleteUser(UserEntity deleteUser);
}
