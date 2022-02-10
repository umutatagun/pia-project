package com.aether.loanapp.service;

import com.aether.loanapp.dto.CustomerDto;
import com.aether.loanapp.dto.CustomerDtoLogin;
import com.aether.loanapp.model.Customer;
import com.aether.loanapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService implements ICustomer{
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers(){
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers.stream().map(customer -> customerToCustomerDto(customer)).collect(Collectors.toList());
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public CustomerDto addCustomer(Customer c1){
        Optional<Customer> exists = customerRepository.findById(c1.getTcId());
        if(exists.isEmpty()){
            c1.setCreditNote(0);
            return customerToCustomerDto(customerRepository.save(c1));
        }else{
            throw new EntityExistsException("Customer already exists");
        }
    }

    public CustomerDto getCustomer(Long tckn) {
        if(Objects.nonNull(customerRepository.getById(tckn))){
            return customerToCustomerDto(customerRepository.getById(tckn));
        }else{
            throw new EntityNotFoundException("Customer not found");
        }
    }

    @Transactional
    public CustomerDto updateCustomer(Long tckn , Customer c1){
        Customer c2 = customerRepository.getById(tckn);
        if(Objects.nonNull(c2)){
            c2.setName(c1.getName());
            c2.setSurname(c1.getSurname());
            c2.setSalary(c1.getSalary());
            c2.setBirthDate(c1.getBirthDate());
            c2.setGender(c1.getGender());
            c2.setPhone(c1.getPhone());
            c2.setEmail(c1.getEmail());
        }else{
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.saveAndFlush(c2);
        return customerToCustomerDto(c2);
    }

    public CustomerDto updateCustomerPassword(Long tckn , Customer customer){
        Customer c2 = customerRepository.getById(tckn);
        if(Objects.nonNull(c2)){
            c2.setPassword(customer.getPassword());
        }else{
            throw new EntityNotFoundException("Customer not found");
        }
        return customerToCustomerDto(customerRepository.saveAndFlush(c2));
    }

    public CustomerDto deleteCustomer(Long tckn) {
        Customer existsCustomer = customerRepository.getById(tckn);
        if(Objects.nonNull(existsCustomer)){
            customerRepository.deleteById(tckn);
        }
        throw new EntityNotFoundException("Customer not found");
    }

    public String credit(Long tckn){
        Customer c1 = customerRepository.getById(tckn);
        if(Objects.nonNull(c1)) {
            if (c1.getCreditNote() > 999) {
                int val = c1.getSalary() * 4;
                return "Tutar : " + val;
            } else if ((c1.getCreditNote() > 499 && c1.getCreditNote() < 1000) && (c1.getSalary() > 5000)) {
                return "20000 Onay";
            } else if ((c1.getCreditNote() > 499 && c1.getCreditNote() < 1000) && (c1.getSalary()) < 5000) {
                return "10000 Onay";
            } else {
                return "Red";
            }
        }else{
            throw new EntityNotFoundException("Customer not found");
        }
    }


    public CustomerDto customerToCustomerDto(Customer c1){
        CustomerDto cdto = new CustomerDto();
        cdto.setName(c1.getName());
        cdto.setSurname(c1.getSurname());
        cdto.setEmail(c1.getEmail());
        cdto.setTcId(c1.getTcId());
        return cdto;
    }

    public Customer customerDtoToCustomer(CustomerDto cdto){
        Customer c1 = new Customer();
        c1.setName(cdto.getName());
        c1.setSurname(cdto.getSurname());
        c1.setEmail(cdto.getEmail());
        c1.setTcId(cdto.getTcId());
        return c1;
    }

    public CustomerDtoLogin customerToCustomerLogin(Customer c1){
        CustomerDtoLogin customerDtoLogin = new CustomerDtoLogin();
        customerDtoLogin.setPassword(c1.getPassword());
        customerDtoLogin.setEmail(c1.getEmail());
        return customerDtoLogin;
    }

    @Override
    public Boolean loginCustomer(CustomerDtoLogin customerDtoLogin) {
        Customer c1 = customerRepository.getByEmail(customerDtoLogin.getEmail());
        if(Objects.nonNull(c1)){
            if((c1.getPassword().equals(customerDtoLogin.getPassword())) && (c1.getEmail().equals(customerDtoLogin.getEmail()))){
                return true;
            }else{
                return false;
            }
        }else{
            throw new EntityNotFoundException("Customer does not exists");
        }
    }

    public List<CustomerDtoLogin> getLoginInfo(){
        return customerRepository.findAll().stream().map(customer ->{
            return customerToCustomerLogin(customer);
        }).collect(Collectors.toList());
    }
}
