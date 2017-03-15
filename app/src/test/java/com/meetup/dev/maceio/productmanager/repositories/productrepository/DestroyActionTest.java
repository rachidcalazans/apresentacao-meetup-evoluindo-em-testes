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
public class DestroyActionTest {

    public abstract static class Describe_Destroying_a_product {
        private ProductRepository repository;

        String title = "Some Title";

        Product product;
        boolean result;

        @Before
        public void before(){
            repository = givenRepository();
            createProduct();

            result = repository.destroy(givenTitle());
        }

        protected List<Product> all() {
            return repository.all();
        }

        protected abstract String givenTitle();

        private void createProduct() {
            product = repository.save(new Product(title));
        }

        private ProductRepository givenRepository() {
            return new ProductRepository();
        }
    }

    public static class Context_When_pass_existent_title extends Describe_Destroying_a_product {
        @Override
        protected String givenTitle() {
            return title;
        }

        @Test
        public void It_should_save_successfully() {
            Assert.assertTrue("Shoud be destroyed", result);
            Assert.assertEquals(0, all().size());
        }

    }

    public static class Context_When_pass_inexistent_title extends Describe_Destroying_a_product {
        @Override
        protected String givenTitle() {
            return "non-existent-title";
        }

        @Test
        public void It_should_return_null() {
            Assert.assertFalse("Shoud not be destroyed", result);
            Assert.assertEquals(1, all().size());
        }

    }

    public static class Context_When_pass_null extends Context_When_pass_inexistent_title {
        @Override
        protected String givenTitle() {
            return null;
        }
    }
}
