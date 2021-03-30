package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.service.ProductService;
import com.Alex.OnlineStoreApplications.service.UserService;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String home() {
        return "adminhome";
    }

    @PostMapping("/user/edit")
    public String editUser(@Valid @ModelAttribute("userEditDto") UpdateDto updateDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "validationerror";
        }
        userService.updateUser(updateDto);
        return "adminhome";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam(value = "id1", required = false) Long id, Model model) {

        userService.deleteUser(id);
        model.addAttribute("id", id);
        return "admintestpage";
    }

    @GetMapping("/productPage")
    public String productPage() {
        return "adminproducts";
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


}
