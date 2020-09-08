package com.Alex.OnlineStoreApplications.service;

import com.Alex.OnlineStoreApplications.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    List<Product> findAllByCategory();
    List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy);
}
