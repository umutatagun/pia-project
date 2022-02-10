package com.aether.loanapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDtoLogin implements Serializable {
    private String email;
    private String password;
}
