package com.Alex.OnlineStoreApplications.service.Impl;

import com.Alex.OnlineStoreApplications.Exceptions.CustomerIsNotSignedIn;
import com.Alex.OnlineStoreApplications.Exceptions.CustomerNotFoundException;
import com.Alex.OnlineStoreApplications.entity.User;
import com.Alex.OnlineStoreApplications.entity.enums.Role;
import com.Alex.OnlineStoreApplications.repository.UserRepository;
import com.Alex.OnlineStoreApplications.repository.ProductRepository;
import com.Alex.OnlineStoreApplications.service.UserService;
import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Long registerUser(RegistrationDto registrationDto) {
        if (!userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            User newCustomer = userRepository.save(mapRegistrationDtoToCustomer(registrationDto));
            return newCustomer.getId();
        } else {
            throw new RuntimeException("This email has already been registered");
        }
    }

    public void updateUser(UpdateDto updateDto) {
        Optional<User> optionalUser = userRepository.findById(updateDto.getId());
        if (optionalUser.isPresent()) {
            userRepository.save(mapUpdateDtoToCustomer(updateDto, optionalUser.get()));
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public BigDecimal buy(BuyingDto buyingDto) {
        Optional<User> optionalCustomer = userRepository.findById(buyingDto.getId());
        if (optionalCustomer.isPresent()) {
            User customer = optionalCustomer.get();
            if (customer.getBlocked()) {
                return buyingDto.getProductIds().stream()
                        .map(id -> productRepository.findById(id))
                        .filter(Optional::isPresent)
                        .map(optionalProduct -> optionalProduct.get().getPrice())
                        .reduce(new BigDecimal(0), BigDecimal::add);
            } else {
                throw new CustomerIsNotSignedIn();
            }
        }else {
            throw new CustomerNotFoundException();
        }
    }

    private User mapRegistrationDtoToCustomer(RegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPhoneNumber(registrationDto.getPhoneNumber());
        user.setPassword(registrationDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.ROLE_USER);
        user.setBlocked(Boolean.FALSE);
        return user;
    }

    private User mapUpdateDtoToCustomer(UpdateDto updateDto, User userInDB) {
        userInDB.setFirstName(updateDto.getFirstName());
        userInDB.setLastName(updateDto.getLastName());
        userInDB.setEmail(updateDto.getEmail());
        userInDB.setPhoneNumber(updateDto.getPhoneNumber());
        userInDB.setPassword(updateDto.getPassword());
        return userInDB;
    }
}
