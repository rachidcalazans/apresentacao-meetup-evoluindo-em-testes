package com.meetup.dev.maceio.productmanager.usecase.addproduct;

import com.meetup.dev.maceio.productmanager.models.Product;
import com.meetup.dev.maceio.productmanager.repositories.ProductRepository;
import com.meetup.dev.maceio.productmanager.usecase.AddProduct;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by rachidcalazans on 15/03/17.
 */

@RunWith(Enclosed.class)
public class AddActionTest {

    public static class Describe_Adding_new_product {

        @Rule
        public MockitoRule rule = MockitoJUnit.rule();

        private AddProduct mAddProduct;
        private Product result;

        @Mock
        private ProductRepository repository;
        @Mock
        private com.meetup.dev.maceio.productmanager.models.Product product;

        @Before
        public void setup(){
            mAddProduct = givenAddProduct();
            result = mAddProduct.add("title");
        }

        private AddProduct givenAddProduct() {
            return new AddProduct(repository);
        }

        @Test
        public void It_Repository_should_call_save() {
            verify(repository).save(any(Product.class));
        }
    }
}
