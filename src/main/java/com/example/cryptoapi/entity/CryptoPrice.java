package com.example.cryptoapi.entity;

public class CryptoPrice {
    private String name;
    private double price;

    public CryptoPrice() {
    }

    public CryptoPrice(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
