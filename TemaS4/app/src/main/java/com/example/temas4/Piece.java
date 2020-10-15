package com.example.temas4;

public class Piece {
    private String name;
    private String producer;
    private float price;

    public Piece(String name, String producer, float price) {
        this.name = name;
        this.producer = producer;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
