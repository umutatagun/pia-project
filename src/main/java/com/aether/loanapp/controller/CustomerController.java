package com.aether.loanapp.controller;

import com.aether.loanapp.dto.CustomerDto;
import com.aether.loanapp.dto.CustomerDtoLogin;
import com.aether.loanapp.model.Customer;
import com.aether.loanapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{tckn}")
    public CustomerDto getCustomer(@PathVariable Long tckn){
        return customerService.getCustomer(tckn);
    }


    @PatchMapping("/{tckn}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long tckn , @RequestBody Customer c1){
        return ResponseEntity.ok(customerService.updateCustomer(tckn,c1));
    }

    @PatchMapping("/update-password/{tckn}")
    public ResponseEntity<CustomerDto> updateCustomerPassword(@PathVariable Long tckn , @RequestBody Customer password){
        return ResponseEntity.ok(customerService.updateCustomerPassword(tckn,password));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody Customer c1){
        return ResponseEntity.ok(customerService.addCustomer(c1));
    }

    @DeleteMapping("/{tckn}")
    public ResponseEntity deleteCustomer(@PathVariable Long tckn){
        customerService.deleteCustomer(tckn);
        return ResponseEntity.ok(tckn+" Silindi");
    }

    @GetMapping("/credit/{tckn}")
    public String credit(@PathVariable  Long tckn){
        return customerService.credit(tckn);
    }

    @PostMapping("/login")
    public Boolean loginCustomer(@RequestBody CustomerDtoLogin customerDtoLogin){
        return customerService.loginCustomer(customerDtoLogin);
    }

}
