package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.service.ICustomerService;
import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        customerService.deleteCustomer(id);

    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateDto updateDto) {

        customerService.updateCustomer(updateDto);

    }

    @PostMapping("/buy")
        public ResponseEntity<BigDecimal>  buy(@RequestBody BuyingDto buyingDto) {
        return ResponseEntity.ok()
                .body(customerService.buy(buyingDto));
            }
}