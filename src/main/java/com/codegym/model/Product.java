package com.codegym.model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String title;
    private String urlImage;
    private BigDecimal price;
    private int quantity;
    private String created_at;
    private String updated_at;
    private int stopSelling;
    private int categoryId;

    public Product(int id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Product(int id, String title, String urlImage, BigDecimal price, int quantity, String created_at, String updated_at, int stopSelling, int categoryId) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
        this.price = price;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.stopSelling = stopSelling;
        this.categoryId = categoryId;
    }

    public Product(String title, String urlImage, BigDecimal price, int quantity, int categoryId) {
        this.title = title;
        this.urlImage = urlImage;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public Product(int id, String title, BigDecimal price, int quantity, int categoryId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public Product(int id, String title, String urlImage, BigDecimal price, int quantity, int categoryId) {
        this.id = id;
        this.title = title;
        this.urlImage = urlImage;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getStopSelling() {
        return stopSelling;
    }

    public void setStopSelling(int stopSelling) {
        this.stopSelling = stopSelling;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", stopSelling=" + stopSelling +
                ", idCategory=" + categoryId +
                '}';
    }
}
