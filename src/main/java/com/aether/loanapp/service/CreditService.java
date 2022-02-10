package com.aether.loanapp.service;

import com.aether.loanapp.dto.CreditDto;
import com.aether.loanapp.model.Credit;
import com.aether.loanapp.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CreditService implements ICredit {
    @Autowired
    CreditRepository creditRepository;

    public List<CreditDto> getCredits(){
        return creditRepository.findAll().stream().map(user -> creditToCreditDto(user)).collect(Collectors.toList());
    }

    public CreditDto addCredit(Credit c1){
        Optional<Credit> exists = creditRepository.findById(c1.getCreditId());
        if(exists.isEmpty()){
            creditRepository.save(c1);
            return creditToCreditDto(c1);
        }else{
            throw new EntityExistsException("Credit already exists");
        }
    }

    public List<CreditDto> getCreditsByTcId(Long tcid){
        return creditRepository.findAllByTcId(tcid).
                stream().map(credit -> creditToCreditDto(credit)).collect(Collectors.toList());
    }

    @Override
    public Credit creditDtoToCredit(CreditDto creditDto){
        double monthlyAmount=0;
        double amountWithR = 0.0;
        try{
            monthlyAmount=creditDto.getAmount()/creditDto.getInstallment();
        }catch (Exception ex){
            System.out.println(ex);
        }
        try {
            amountWithR = creditDto.getAmount()+(creditDto.getAmount()*creditDto.getCreditType().getVal());
        }catch (Exception ex){
            System.out.println(ex);
        }
        Credit c = new Credit();
        c.setInstallment(creditDto.getInstallment());
        c.setCreditType(creditDto.getCreditType());
        c.setActiveInstallment(creditDto.getActiveinstallment());
        c.setAmount(creditDto.getAmount());
        c.setCreditRate(creditDto.getCreditType().getVal());
        c.setMonthlyInstallment(monthlyAmount);
        c.setAmountWithRate(amountWithR);
        c.setStatus(false);
        return c;
    }

    @Override
    public CreditDto creditToCreditDto(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setAmount(credit.getAmount());
        creditDto.setCreditType(credit.getCreditType());
        creditDto.setInstallment(credit.getInstallment());
        creditDto.setActiveinstallment(credit.getActiveInstallment());
        return  creditDto;
    }

}
