package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.service.Impl.FileService;
import com.Alex.OnlineStoreApplications.service.ProductService;
import com.Alex.OnlineStoreApplications.service.UserService;
import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.UploadingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    private FileService fileService;


    @GetMapping("/home")
    public String home() {
        return "userhome";
    }

    @GetMapping("/productPage")
    public String productPage() {
        return "userproducts";
    }


    @GetMapping("/product/all")
    public String getAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy, Model model) {
        List<Product> products = productService.getAllProducts(pageNo, pageSize, sortBy);
        model.addAttribute("products", products);
        model.addAttribute("pageNumber", pageNo);

        return "products";
    }

    @PostMapping("/buy")
    public ResponseEntity<BigDecimal>  buy(@Valid @RequestBody BuyingDto buyingDto)  {
        return ResponseEntity.ok()
                .body(userService.buy(buyingDto));
    }
    @PostMapping("/uploadFile")
    public void uploadFile(@Valid @ModelAttribute UploadingDto uploadingDto) {
        fileService.uploadFile(uploadingDto);
    }

    @GetMapping("/downloadFile/{customerId}")
    public ResponseEntity<List<ByteArrayResource>> downloadFiles(@PathVariable Long customerId) {
        return ResponseEntity.ok()
                .body(fileService.downloadFiles(customerId).stream()
                        .map(file -> new ByteArrayResource(file.getData()))
                        .collect(Collectors.toList()));
    }
}