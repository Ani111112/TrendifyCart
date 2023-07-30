package com.example.springmart.transformer;

import com.example.springmart.dto.requestDto.CustomerRequestDto;
import com.example.springmart.dto.responseDto.CustomerResponseDto;
import com.example.springmart.model.Customer;
import lombok.experimental.UtilityClass;


@UtilityClass // this is optional,
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
       return Customer.builder()
                .name(customerRequestDto.getName())
                .gender(customerRequestDto.getGender())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
       return CustomerResponseDto.builder()
                .name(customer.getName())
                .gender(customer.getGender())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }
}
