package com.example.tema;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Piece implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String pieceName;
    @ColumnInfo
    public String producer;
    @ColumnInfo
    public float price;

    public Piece(int id, String pieceName, String producer, float price) {
        this.id = id;
        this.pieceName = pieceName;
        this.producer = producer;
        this.price = price;
    }

    @Ignore
    public Piece(String pieceName, String producer, float price) {
        this.pieceName = pieceName;
        this.producer = producer;
        this.price = price;

    }

    @Ignore
    public Piece(String pieceName, String producer) {
        this.pieceName = pieceName;
        this.producer = producer;
        this.price = 0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public float getPrice() { return price; }

    public void setPrice(float price) {
        this.price = price;
    }
}
