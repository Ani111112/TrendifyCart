package com.example.springmart.service.impl;

import com.example.springmart.dto.requestDto.CustomerRequestDto;
import com.example.springmart.dto.responseDto.CustomerResponseDto;
import com.example.springmart.model.Cart;
import com.example.springmart.model.Customer;
import com.example.springmart.repository.CustomerRepository;
import com.example.springmart.service.CustomerService;
import com.example.springmart.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        // convert dto to entity,, Add a customer
        /*
        Customer customer = new Customer();
        customer.setName(addCustomerRequestDto.getName());
        customer.setGender(addCustomerRequestDto.getGender());
        customer.setEmailId(addCustomerRequestDto.getEmailId());
        customer.setMobNo(addCustomerRequestDto.getMobNo()); */

        // Builder - to make the object
        // instead of using new keyword to make object and set parameter, we can use builder
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        // add the cart to newly added customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        // save the cart to customer as well -> bi-directional mapping
        customer.setCart(cart);

        // save the customer to repo -> it will save both cart and customer, as customer is parent
        Customer savedCustomer = customerRepository.save(customer);

        // prepare the response
        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

        return customerResponseDto;
    }
}
