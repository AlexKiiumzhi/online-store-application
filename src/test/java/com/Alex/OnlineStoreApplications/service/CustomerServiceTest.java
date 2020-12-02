package com.Alex.OnlineStoreApplications.service;

import com.Alex.OnlineStoreApplications.entity.Category;
import com.Alex.OnlineStoreApplications.entity.Customer;
import com.Alex.OnlineStoreApplications.entity.Product;
import com.Alex.OnlineStoreApplications.repository.CustomerRepository;
import com.Alex.OnlineStoreApplications.repository.ProductRepository;
import com.Alex.OnlineStoreApplications.service.Impl.CustomerService;
import com.Alex.OnlineStoreApplications.service.dto.BuyingDto;
import com.Alex.OnlineStoreApplications.service.dto.RegistrationDto;
import com.Alex.OnlineStoreApplications.service.dto.UpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerCustomer_shall_register_a_customer() {
        // given
        LocalDateTime dateTime1 = LocalDateTime.of(2020, Month.AUGUST, 24, 18, 47, 52);

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirst_name("Josh");
        registrationDto.setLast_name("Clark");
        registrationDto.setEmail("Josh.C@gmail.com");
        registrationDto.setPhone_number("(541) 754-3010");
        registrationDto.setPassword("josh-josh");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(registrationDto.getFirstName());
        customer.setLastName(registrationDto.getLastName());
        customer.setEmail(registrationDto.getEmail());
        customer.setPhoneNumber(registrationDto.getPhoneNumber());
        customer.setPassword(registrationDto.getPassword());
        customer.setCreatedAt(dateTime1);
        customer.setSignedIn(Boolean.TRUE);

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.save(any())).thenReturn(customer);

        // when
        Long actualId = customerService.registerCustomer(registrationDto);

        // then
        assertEquals(actualId, customer.getId());
    }

    @Test
    public void updateCustomer_shall_update_a_customer() {
        // given
        LocalDateTime dateTime1 = LocalDateTime.of(2020, Month.AUGUST, 24, 18, 47, 52);

        UpdateDto expectedDto = new UpdateDto();
        expectedDto.setId(1L);
        expectedDto.setFirst_name("Josh");
        expectedDto.setLast_name("Clark");
        expectedDto.setEmail("Josh.C@gmail.com");
        expectedDto.setPhone_number("(541) 754-3010");
        expectedDto.setPassword("josh-josh");

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("alex");
        customer.setLastName("kiiumzhi");
        customer.setEmail("alex.kii@gmail.com");
        customer.setPhoneNumber("(581) 757-3010");
        customer.setPassword("alex.alex");
        customer.setCreatedAt(dateTime1);
        customer.setSignedIn(Boolean.TRUE);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        // when
        customerService.updateCustomer(expectedDto);
        Customer actualCustomer = customerRepository.findById(customer.getId()).get();

        // then
        assertCustomer(actualCustomer, expectedDto);
    }

    @Test
    public void deleteCustomer_shall_delete_a_customer() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setEmail("alex.kii@gmail.com");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        // when
        customerService.deleteCustomer(customer.getId());
        Optional<Customer> actualCustomer  = customerRepository.findByEmail(customer.getEmail());

        // then
        assertFalse(actualCustomer.isPresent());
    }

    @Test
    public void buy() {
        // given

        Product product1 = new Product();
        product1.setId(1L);
        product1.setPrice(new BigDecimal("59.99"));
        Product product2 = new Product();
        product2.setId(2L);
        product2.setPrice(new BigDecimal("5999.99"));

        List<Long> productsIds = new ArrayList<>();
        productsIds.add(product1.getId());
        productsIds.add(product2.getId());

        BuyingDto buyingDto = new BuyingDto();
        buyingDto.setId(1L);
        buyingDto.setProductIds(productsIds);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setSignedIn(Boolean.TRUE);


        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        // when
        BigDecimal expectedPrice = customerService.buy(buyingDto);
        BigDecimal actualPrice = product1.getPrice().add(product2.getPrice());

        // then
        assertEquals(actualPrice , expectedPrice);
    }

    private void assertCustomer(Customer actualCustomer, UpdateDto expectedDto){
        assertEquals(actualCustomer.getId(), expectedDto.getId());
        assertEquals(actualCustomer.getFirstName(), expectedDto.getFirstName());
        assertEquals(actualCustomer.getLastName(), expectedDto.getLastName());
        assertEquals(actualCustomer.getEmail(), expectedDto.getEmail());
        assertEquals(actualCustomer.getPhoneNumber(), expectedDto.getPhoneNumber());
        assertEquals(actualCustomer.getPassword(), expectedDto.getPassword());
    }
}
