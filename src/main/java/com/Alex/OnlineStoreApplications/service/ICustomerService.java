package com.Alex.OnlineStoreApplications.service;

import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService {
    Long registerCustomer(RegistrationDto registrationDto);
    void deleteCustomer(Long id);
    void updateCustomer(UpdateDto updateDto);
    BigDecimal buy(BuyingDto buyingDto);
}
