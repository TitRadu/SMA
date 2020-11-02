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

    @Query("UPDATE user  SET firstName = :firstName, lastName = :lastName, email = :email, age = :age WHERE userName = :userName")
    void updateFirstName(String userName,String firstName, String lastName, String email, int age);


    @Insert
    void insertAllUsers(User... users);

    @Delete
    void deleteUser(User... user);

}
