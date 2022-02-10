package com.aether.loanapp.repository;

import com.aether.loanapp.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit,Long> {
    @Query(value = "select * from credit where customer_id = :tcid ",nativeQuery = true)
    List<Credit> findAllByTcId(Long tcid);
}
