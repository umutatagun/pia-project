package com.aether.loanapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable{
    private Long TcId;
    private String name;
    private String surname;
    private String email;
}
