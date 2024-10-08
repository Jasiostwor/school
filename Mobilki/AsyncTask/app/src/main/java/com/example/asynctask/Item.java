package com.example.asynctask;

public class Item {

    private int id;
    private double price;
    private String name,description;

    public Item(int id, String name, double price, String description){
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public double getPrice(){
        return this.price;
    }
    public String getDescription(){
        return this.description;
    }
}
