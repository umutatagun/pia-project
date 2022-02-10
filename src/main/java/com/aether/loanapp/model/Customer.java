package com.aether.loanapp.model;

import com.aether.loanapp.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity
@Table
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Customer implements Serializable {
    @Id
    private Long tcId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column
    private Integer salary;

    @Column(unique = true)
    private String phone;

    @Column
    private Gender gender;

    @Column
    @JsonFormat(pattern = "DD/MM/YYYY")
    private Date birthDate;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private Integer creditNote;

    @Column
    @OneToMany(targetEntity = Credit.class,fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonIgnore
    private List<Credit> credits;

    public Customer(Long tcId, String name, String surname, Integer salary, String phone, Gender gender, Date birthDate, String email, String password) {
        this.tcId = tcId;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.phone = phone;
        this.gender = gender;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    public Customer(String password) {
        this.password = password;
    }
}
