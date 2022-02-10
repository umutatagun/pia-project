package com.aether.loanapp.model;

import com.aether.loanapp.enums.CreditType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Credit implements Serializable {
    @Id
    @SequenceGenerator(sequenceName = "creditIdGenerator",name = "creditIdGenerator",allocationSize = 1,initialValue = 1)
    @GeneratedValue(generator = "creditIdGenerator",strategy = GenerationType.AUTO)
    private Long creditId;

    @Column(nullable = false)
    private Long amount;

    private Double monthlyInstallment;

    @Column(nullable = false)
    private Integer installment;

    private Integer activeInstallment;

    private CreditType creditType;
    private Double creditRate;
    private Double amountWithRate;
    private boolean status;
    private Long tcId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",referencedColumnName = "tcId")
    private Customer customer;

    public Credit() {
    }

    public Credit(Long tcId, Long amount, Integer installment, CreditType creditType) {
        this.tcId = tcId;
        this.amount = amount;
        this.installment = installment;
        this.creditType = creditType;
        this.activeInstallment = 0;
        this.monthlyInstallment = Double.valueOf(amount/installment);
    }

    public Credit(Long amount, Integer installment, Customer customer,CreditType creditType,Long tcId) {
        this.amount = amount;
        this.installment = installment;
        this.creditType = creditType;
        this.tcId = tcId;
        this.customer = customer;
        this.monthlyInstallment = Double.valueOf(amount/installment);
    }

    public Credit(Long amount, Integer installment, Integer activeInstallment, CreditType creditType,Long tcid) {
        this.amount = amount;
        this.creditType = creditType;
        this.tcId = tcid;
        this.installment = installment;
        this.activeInstallment = activeInstallment;
        this.monthlyInstallment = Double.valueOf(amount/installment);
    }

}
