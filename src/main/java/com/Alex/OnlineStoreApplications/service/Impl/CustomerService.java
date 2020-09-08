package com.Alex.OnlineStoreApplications.service.Impl;

import com.Alex.OnlineStoreApplications.Exceptions.CustomerIsNotSignedIn;
import com.Alex.OnlineStoreApplications.Exceptions.CustomerNotFoundException;
import com.Alex.OnlineStoreApplications.entity.Customer;
import com.Alex.OnlineStoreApplications.repository.CustomerRepository;
import com.Alex.OnlineStoreApplications.repository.ProductRepository;
import com.Alex.OnlineStoreApplications.service.ICustomerService;
import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long registerCustomer(RegistrationDto registrationDto) {
        if (!customerRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            Customer newCustomer = customerRepository.save(mapRegistrationDtoToCustomer(registrationDto));
            return newCustomer.getId();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void updateCustomer(UpdateDto updateDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(updateDto.getId());
        if (optionalCustomer.isPresent()) {
            customerRepository.save(mapUpdateDtoToCustomer(updateDto, optionalCustomer.get()));
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public BigDecimal buy(BuyingDto buyingDto) {

        Optional<Customer> optionalCustomer = customerRepository.findById(buyingDto.getId());
        if (optionalCustomer.isPresent()) {
            Customer customer = customerRepository.getOne(buyingDto.getId());
            if (customer.getSignedIn()) {
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

    private Customer mapRegistrationDtoToCustomer(RegistrationDto registrationDto) {
        Customer customer = new Customer();
        customer.setFirstName(registrationDto.getFirstName());
        customer.setLastName(registrationDto.getLastName());
        customer.setEmail(registrationDto.getEmail());
        customer.setPhoneNumber(registrationDto.getPhoneNumber());
        customer.setPassword(registrationDto.getPassword());
        customer.setCreatedAt(LocalDateTime.now());
        return customer;
    }

    private Customer mapUpdateDtoToCustomer(UpdateDto updateDto, Customer customerInDB) {
        customerInDB.setFirstName(updateDto.getFirstName());
        customerInDB.setLastName(updateDto.getLastName());
        customerInDB.setEmail(updateDto.getEmail());
        customerInDB.setPhoneNumber(updateDto.getPhoneNumber());
        customerInDB.setPassword(updateDto.getPassword());
        return customerInDB;
    }
}
