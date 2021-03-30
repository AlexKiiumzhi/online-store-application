package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.entity.enums.Role;
import com.Alex.OnlineStoreApplications.service.LoginService;
import com.Alex.OnlineStoreApplications.service.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "validationerror";
        }
        loginService.loadUserByUsername(loginDto.getEmail());
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_USER)) {
            return "redirect:/user/home";
        } else if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(Role.ROLE_ADMIN)) {
            return "redirect:/admin/home";
        }
        return "error";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        return "tests";
    }

    @PostMapping("/signout")
    public String logOut() {
        return "login";
    }

}
