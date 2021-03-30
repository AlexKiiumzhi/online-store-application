package com.Alex.OnlineStoreApplications.service;

import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    Long registerUser(RegistrationDto registrationDto);
    void deleteUser(Long id);
    void updateUser(UpdateDto updateDto);
    BigDecimal buy(BuyingDto buyingDto);
}
