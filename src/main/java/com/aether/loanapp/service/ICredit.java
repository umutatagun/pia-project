package com.aether.loanapp.service;

import com.aether.loanapp.dto.CreditDto;
import com.aether.loanapp.model.Credit;

import java.util.List;

public interface ICredit {
    List<CreditDto> getCredits();
    CreditDto addCredit(Credit c1);
    List<CreditDto> getCreditsByTcId(Long id);
    Credit creditDtoToCredit(CreditDto creditDto);
    CreditDto creditToCreditDto(Credit credit);
}
