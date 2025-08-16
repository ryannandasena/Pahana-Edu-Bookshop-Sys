package com.pahanaedu.model;

public class Item {
    private int itemId;
    private String itemName;
    private double price;
    private int unitsInStock;

    // Constructors
    public Item() {}

    public Item(int itemId, String itemName, double price,int unitsInStock) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.unitsInStock = unitsInStock;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getUnitsInStock() {
        return unitsInStock;
    }
    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }
}
