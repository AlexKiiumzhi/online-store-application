package com.Alex.OnlineStoreApplications.service;

import com.Alex.OnlineStoreApplications.entity.Category;
import com.Alex.OnlineStoreApplications.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    List<Product> findAllByCategory(Long categoryId);
    List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
}
