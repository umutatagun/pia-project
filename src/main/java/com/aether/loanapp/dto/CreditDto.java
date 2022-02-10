package com.aether.loanapp.dto;

import com.aether.loanapp.enums.CreditType;
import lombok.Data;

@Data
public class CreditDto {
    private Long amount;
    private Integer installment;
    private CreditType  creditType;
    private Integer activeinstallment;

    public CreditDto() {
    }

    public CreditDto(Long amount, Integer installment,CreditType creditType) {
        this.amount = amount;
        this.installment = installment;
        this.creditType = creditType;
        this.activeinstallment = 0;
    }

    public CreditDto(Long amount, Integer installment, Integer activeinstallment,CreditType creditType) {
        this.amount = amount;
        this.installment = installment;
        this.creditType = creditType;
        this.activeinstallment = activeinstallment;
    }
}
