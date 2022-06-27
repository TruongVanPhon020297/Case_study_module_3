package com.codegym.model;

public class Cart {

    private int id;
    private int productId;
    private String title;
    private int price;
    private int quantity;
    private double totalPrice;

    public Cart() {
    }

    public Cart(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(int productId, String title, int price, int quantity, double totalPrice) {
        this.productId = productId;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Cart(int id, int productId, String title, int price, int quantity) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
