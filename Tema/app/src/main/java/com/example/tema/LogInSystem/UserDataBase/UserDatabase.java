package com.example.tema.LogInSystem.UserDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static  final String DB_NAME = "USER_DB";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return instance;

    }

    public abstract UserDAO userDao();

}
