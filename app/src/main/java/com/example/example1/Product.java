package com.example.example1;

public class Product {

    private String id;
    private String name;
    private double price;
    private int amount;

    public Product(String id, String name, double price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAmount() { return amount; }
    public double getTotal() {
        return price * amount;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | Total: " + getTotal();
    }
}