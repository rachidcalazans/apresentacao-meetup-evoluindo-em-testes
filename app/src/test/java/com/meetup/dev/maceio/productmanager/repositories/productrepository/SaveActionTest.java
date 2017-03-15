package com.meetup.dev.maceio.productmanager.repositories.productrepository;

import com.meetup.dev.maceio.productmanager.models.Product;
import com.meetup.dev.maceio.productmanager.repositories.ProductRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by rachidcalazans on 13/03/17.
 */
/**
 * Unit tests for the implementation of {@link ProductRepository}
 */
@RunWith(Enclosed.class)
public class SaveActionTest {

    public abstract static class Describe_Saving_a_product {
        private ProductRepository repository;

        Product product;
        Product savedProduct;

        @Before
        public void before(){
            repository = givenRepository();
            product    = givenProduct();

            savedProduct = repository.save(product);
        }

        protected List<Product> all() {
            return repository.all();
        }

        protected abstract Product givenProduct();

        private ProductRepository givenRepository() {
            return new ProductRepository();
        }
    }

    public static class Context_When_pass_a_valid_product extends Describe_Saving_a_product {
        private String someTitle = "Some title";

        @Override
        protected Product givenProduct() {
            return new Product(someTitle);
        }

        @Test
        public void It_should_save_successfully() {
            Assert.assertEquals(someTitle, savedProduct.getTitle());
            Assert.assertEquals(1, all().size());
        }

    }

    public static class Context_When_pass_an_invalid_product extends Describe_Saving_a_product {
        @Override
        protected Product givenProduct() {
            return new Product("");
        }

        @Test
        public void It_should_not_be_saved() {
            Assert.assertNull(savedProduct);
            Assert.assertEquals(0, all().size());
        }

    }

    public static class Context_When_pass_null extends Context_When_pass_an_invalid_product {
        @Override
        protected Product givenProduct() {
            return null;
        }

    }
}
