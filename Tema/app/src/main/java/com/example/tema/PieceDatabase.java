package com.example.tema;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Piece.class}, version = 1, exportSchema = false)
public abstract class PieceDatabase extends RoomDatabase {
    private static  final String DB_NAME = "PIECE_DB";
    private static PieceDatabase instance;

    public static synchronized PieceDatabase getInstance(Context context){

        if(instance == null){
             instance = Room.databaseBuilder(context, PieceDatabase.class, DB_NAME)
                     .fallbackToDestructiveMigration()
                     .build();

        }

        return instance;

    }

    public abstract PieceDAO pieceDao();

}
