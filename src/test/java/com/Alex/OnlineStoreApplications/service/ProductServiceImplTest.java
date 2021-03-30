package com.Alex.OnlineStoreApplications.service;


import com.Alex.OnlineStoreApplications.entity.Category;
import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.repository.CategoryRepository;
import com.Alex.OnlineStoreApplications.repository.ProductRepository;
import com.Alex.OnlineStoreApplications.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll_shall_return_all_products(){
        // given
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("clothes");
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("cars");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("product1");
        product1.setBrandName("adidas");
        product1.setPrice(new BigDecimal("59.99"));
        product1.setCategory(category1);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("product2");
        product2.setBrandName("BMW");
        product2.setPrice(new BigDecimal("5999.99"));
        product2.setCategory(category2);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        when(productRepository.findAll()).thenReturn(expectedProducts);

        // when
        List<Product> actualProducts = productServiceImpl.findAllProducts();

        // then
        int index = 0;
        for (Product actualProduct : actualProducts){
            assertProduct(actualProduct, expectedProducts.get(index++));
        }
    }

    @Test
    public void findAllByCategory_shall_return_all_products_by_category(){
        // given
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("clothes");
        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("cars");

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("product1");
        product1.setBrandName("adidas");
        product1.setPrice(new BigDecimal("59.99"));
        product1.setCategory(category1);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("product2");
        product2.setBrandName("BMW");
        product2.setPrice(new BigDecimal("5999.99"));
        product2.setCategory(category1);
        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("product2");
        product3.setBrandName("BMW");
        product3.setPrice(new BigDecimal("5999.99"));
        product3.setCategory(category2);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product1);
        expectedProducts.add(product2);
        expectedProducts.add(product3);

        when(categoryRepository.getCategoryId(anyLong())).thenReturn(category1.getId());
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // when
        List<Product> actualProducts = productServiceImpl.findAllByCategory(category1.getId());

        // then
        int index = 0;
        assertEquals(actualProducts.size(), 2);
        for (Product actualProduct : actualProducts){
            assertProduct(actualProduct, expectedProducts.get(index++));
        }
    }
    private void assertProduct(Product actualProduct,Product expectedProduct){
        assertEquals(actualProduct.getBrandName(), expectedProduct.getBrandName());
        assertEquals(actualProduct.getId(), expectedProduct.getId());
        assertEquals(actualProduct.getName(), expectedProduct.getName());
        assertEquals(actualProduct.getPrice(), expectedProduct.getPrice());
        assertEquals(actualProduct.getCategory().getId(), expectedProduct.getCategory().getId());
    }

//    @Test
//    public void getAllProducts_shall_return_all_pageable_products(){
//        // given
//        Integer pageNo;
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        Category category1 = new Category();
//        category1.setId(1L);
//        category1.setName("clothes");
//        Category category2 = new Category();
//        category2.setId(2L);
//        category2.setName("cars");
//
//        Product product1 = new Product();
//        product1.setId(1L);
//        product1.setName("product1");
//        product1.setBrandName("adidas");
//        product1.setPrice(new BigDecimal("59.99"));
//        product1.setCategory(category1);
//        Product product2 = new Product();
//        product2.setId(2L);
//        product2.setName("product2");
//        product2.setBrandName("BMW");
//        product2.setPrice(new BigDecimal("5999.99"));
//        product2.setCategory(category2);
//
//        List<Product> expectedProducts = new ArrayList<>();
//        expectedProducts.add(product1);
//        expectedProducts.add(product2);
//
//        when(productRepository.findAll(paging)).thenReturn(expectedProducts);
//
//        // when
//        List<Product> actualProducts = productService.findAll();
//
//        // then
//        int index = 0;
//        for (Product actualProduct : actualProducts){
//            assertProduct(actualProduct, expectedProducts.get(index++));
//        }
//    }
}
