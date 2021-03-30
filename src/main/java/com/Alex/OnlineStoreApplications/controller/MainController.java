package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.service.ProductService;
import com.Alex.OnlineStoreApplications.service.UserService;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/home")
        public String home() {
        return "home";
        }

    @GetMapping("/registrationPage")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "validationerror";
        }
        userService.registerUser(registrationDto);
        return "login";
    }

    @GetMapping("/productPage")
    public String productPage(Model model) {
        return "products";
    }

    /*@GetMapping("/test/searchByCategory")
    public String searchBySubject(@RequestParam(value = "searchedCategory") Long categoryId, Model model) {

        List<Category> categories = productService.findAllCategories();

        model.addAttribute("categories", categories);
        List<Product> products = productService.findAllByCategory(categoryId);
        model.addAttribute("products", products);
        return "products";
    }*/

    @GetMapping("/product/all")
    public String getAllTests(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "name") String sortBy, Model model) {
        List<Product> products = productService.getAllProducts(pageNo, pageSize, sortBy);
        model.addAttribute("products", products);
        model.addAttribute("pageNumber", pageNo);

        return "products";
    }
}




