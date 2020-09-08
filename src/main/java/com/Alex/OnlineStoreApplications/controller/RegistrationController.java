package com.Alex.OnlineStoreApplications.controller;


import com.Alex.OnlineStoreApplications.service.ICustomerService;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<Long> registerCustomer(@RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok()
                .body(customerService.registerCustomer(registrationDto));
    }
}

