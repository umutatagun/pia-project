package com.aether.loanapp.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
