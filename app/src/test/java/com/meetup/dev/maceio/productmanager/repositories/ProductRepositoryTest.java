package com.meetup.dev.maceio.productmanager.repositories;

import com.meetup.dev.maceio.productmanager.models.Product;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by rachidcalazans on 13/03/17.
 */

public class ProductRepositoryTest {

    ProductRepository repository = new ProductRepository();

    @Test
    public void saveWithValidProduct() {
        String title = "Some Title";
        Product savedProduct = createProduct(title);

        Assert.assertEquals(title, savedProduct.getTitle());
        Assert.assertFalse("Should exist one product", repository.all().isEmpty());
    }

    @Test
    public void saveWithInvalidProduct() {
        Product savedProduct = createProduct("");

        Assert.assertNull(savedProduct);
        Assert.assertTrue("Should be empty", repository.all().isEmpty());
    }

    @Test
    public void saveWithNull() {
        Product savedProduct = repository.save(null);

        Assert.assertNull(savedProduct);
        Assert.assertTrue("Should be empty", repository.all().isEmpty());
    }

    @Test
    public void findWithExistentTitle() {
        String title = "Some Title";
        Product product = createProduct(title);


        Product found = repository.find(title);
        Assert.assertEquals(product, found);
    }

    @Test
    public void findWithInexistentTitle() {
        String title = "Some Title";
        createProduct(title);


        Product found = repository.find("Some new");
        Assert.assertNull(found);
    }

    @Test
    public void findWithNull() {
        Product found = repository.find(null);
        Assert.assertNull(found);
    }

    @Test
    public void destroyWithExistentTitle() {
        String title = "Some Title";
        createProduct(title);

        boolean result = repository.destroy(title);
        Assert.assertTrue("Shoud be destroyed", result);
        Assert.assertTrue("Should be empty", repository.all().isEmpty());
    }

    @Test
    public void destroyWithInexistentTitle() {
        String title = "Some Title";
        createProduct(title);

        boolean result = repository.destroy("Some new");
        Assert.assertFalse("Should not be destroyed", result);
    }

    @Test
    public void destroyWithNull() {
        boolean result = repository.destroy(null);
        Assert.assertFalse("Should not be destroyed", result);

    }

    private Product createProduct(String title) {
        Product product = new Product(title);
        return repository.save(product);
    }

}
