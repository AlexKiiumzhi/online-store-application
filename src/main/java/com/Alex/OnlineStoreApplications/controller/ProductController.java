package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok()
                .body(productService.findAll());
    }


    @GetMapping("/byCategory")
    public ResponseEntity<List<Product>> findAllByCategory() {
        return ResponseEntity.ok()
                .body(productService.findAllByCategory());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Product> list = productService.getAllProducts(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
