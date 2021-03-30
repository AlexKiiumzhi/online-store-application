package com.Alex.OnlineStoreApplications.service.Impl;

import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.repository.CategoryRepository;
import com.Alex.OnlineStoreApplications.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements com.Alex.OnlineStoreApplications.service.ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(Long categoryId) {
        Long categoryId1 = categoryRepository.getCategoryId(categoryId);
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().getId().equals(categoryId1))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> products = productRepository.findAll(paging);

        if(products.hasContent()) {
            return products.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
