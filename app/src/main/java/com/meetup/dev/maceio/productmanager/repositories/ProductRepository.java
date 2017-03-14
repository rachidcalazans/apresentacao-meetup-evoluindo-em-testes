package com.meetup.dev.maceio.productmanager.repositories;

import com.meetup.dev.maceio.productmanager.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rachidcalazans on 13/03/17.
 */

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public Product save(Product product) {
        if (isNotValid(product)) return null;

        products.add(product);

        return product;
    }

    public Product find(String title) {
        for (Product product : products) {
            if (product.getTitle().equals(title))
                return product;
        }
        return null;
    }

    public boolean destroy(String title) {
        Product found = null;
        for (Product product : products) {
            if (product.getTitle().equals(title)) {
                found = product;
            }
        }

        if (found == null) return false;

        products.remove(found);

        return true;
    }

    public List<Product> all() {
        return products;
    }

    private boolean isNotValid(Product product) {
        return product == null || product.getTitle() == null || product.getTitle().equals("");
    }

}
