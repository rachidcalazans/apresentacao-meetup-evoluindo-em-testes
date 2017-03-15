package com.meetup.dev.maceio.productmanager.repositories.productrepository;

import com.meetup.dev.maceio.productmanager.models.Product;
import com.meetup.dev.maceio.productmanager.repositories.ProductRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * Created by rachidcalazans on 13/03/17.
 */

/**
 * Unit tests for the implementation of {@link ProductRepository}
 */
@RunWith(Enclosed.class)
public class FindActionTest {

    public abstract static class Describe_Finding_a_product {
        private ProductRepository repository;

        String title = "Some Title";

        Product product;
        Product foundProduct;

        @Before
        public void before(){
            repository = givenRepository();
            createProduct();

            foundProduct = repository.find(givenTitle());
        }

        protected abstract String givenTitle();

        private void createProduct() {
            product = repository.save(new Product(title));
        }

        private ProductRepository givenRepository() {
            return new ProductRepository();
        }
    }

    public static class Context_When_pass_existent_title extends Describe_Finding_a_product {
        @Override
        protected String givenTitle() {
            return title;
        }

        @Test
        public void It_should_save_successfully() {
            Assert.assertEquals(product, foundProduct);
        }

    }

    public static class Context_When_pass_inexistent_title extends Describe_Finding_a_product {
        @Override
        protected String givenTitle() {
            return "non-existent-title";
        }

        @Test
        public void It_should_return_null() {
            Assert.assertNull(foundProduct);
        }

    }

    public static class Context_When_pass_null extends Context_When_pass_inexistent_title {
        @Override
        protected String givenTitle() {
            return null;
        }
    }
}
