package com.example.tema.PieceDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PieceDAO {
    @Query("SELECT * FROM piece")
    List<Piece> getAll();

    @Query("SELECT * FROM piece WHERE id IN (:piecesIds)")
    List<Piece> loadAllByIds(int[] piecesIds);

    @Query("SELECT * FROM piece WHERE pieceName = :name")
    List<Piece> findByPieceName(String name);

    @Insert
    void insertAllPieces(Piece... pieces);

    @Delete
    void deletePiece(Piece... piece);

}
