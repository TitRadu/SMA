package com.example.tema.LogInSystem.UserDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:usersIds)")
    List<User> loadAllByIds(int[] usersIds);

    @Query("SELECT * FROM user WHERE userName = :name")
    List<User> findByUserName(String name);

    @Insert
    void insertAllUsers(User... users);

    @Delete
    void deleteUser(User... user);

}
