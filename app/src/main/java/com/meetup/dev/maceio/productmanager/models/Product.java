package com.meetup.dev.maceio.productmanager.models;

/**
 * Created by rachidcalazans on 13/03/17.
 */
public class Product {

    private String title;

    public Product() {
    }

    public Product(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
