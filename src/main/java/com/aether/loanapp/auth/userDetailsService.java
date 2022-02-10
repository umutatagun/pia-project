package com.aether.loanapp.auth;

import com.aether.loanapp.dto.CustomerDto;
import com.aether.loanapp.dto.CustomerDtoLogin;
import com.aether.loanapp.requests.LoginRequest;
import com.aether.loanapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class userDetailsService implements UserDetailsService {
    private Map<String,String> users = new HashMap<>();

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void init(){
        LoginRequest loginRequest = new LoginRequest();
        users.put("umut",passwordEncoder.encode("123"));
        customerService.getAllCustomer().forEach(customer ->
                users.put(customer.getEmail(),passwordEncoder.encode(customer.getPassword()))
        );
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(users.containsKey(username)){
            return new User(username , users.get(username),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
