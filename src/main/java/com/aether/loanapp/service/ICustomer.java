package com.aether.loanapp.service;

import com.aether.loanapp.dto.CustomerDto;
import com.aether.loanapp.dto.CustomerDtoLogin;
import com.aether.loanapp.model.Customer;

import java.util.List;

public interface ICustomer {
    List<CustomerDto> getAllCustomers();
    CustomerDto addCustomer(Customer c1);
    CustomerDto getCustomer(Long l1);
    CustomerDto updateCustomer(Long id,Customer c1);
    CustomerDto updateCustomerPassword(Long id,Customer c1);
    CustomerDto deleteCustomer(Long id);
    String credit(Long id);
    CustomerDto customerToCustomerDto(Customer c1);
    Customer customerDtoToCustomer(CustomerDto cdto1);
    Boolean loginCustomer(CustomerDtoLogin customerDtoLogin);
}
