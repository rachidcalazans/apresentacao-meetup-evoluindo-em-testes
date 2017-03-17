package com.meetup.dev.maceio.productmanager.usecase;

import com.meetup.dev.maceio.productmanager.models.Product;
import com.meetup.dev.maceio.productmanager.repositories.ProductRepository;

/**
 * Created by rachidcalazans on 15/03/17.
 */

public class AddProduct {

    private final ProductRepository repository;

    public AddProduct(ProductRepository repository) {
        this.repository = repository;
    }

    public Product add(String title) {
        return repository.save(new Product(title));
    }
}
