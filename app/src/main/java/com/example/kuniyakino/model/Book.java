package com.example.kuniyakino.model;

import java.io.Serializable;

public class Book implements Serializable {
    int id;
    String title;
    String author;
    String publication;
    String price;
    String description;
    int page;
    int quantity;
    int imageURL;

    public Book() {}

    public Book(int id, String title, String author, String publication, String price, String description, int page, int quantity, int imageURL) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication = publication;
        this.price = price;
        this.description = description;
        this.page = page;
        this.quantity = quantity;
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageURL() {
        return imageURL;
    }
}
