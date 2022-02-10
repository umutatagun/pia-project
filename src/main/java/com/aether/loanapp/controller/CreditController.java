package com.aether.loanapp.controller;

import com.aether.loanapp.dto.CreditDto;
import com.aether.loanapp.model.Credit;
import com.aether.loanapp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    CreditService creditService;

    @GetMapping
    public List<CreditDto> getCredits(){
        return creditService.getCredits();
    }

    @GetMapping("/{tcid}")
    public List<CreditDto> getCreditsByTcId(@PathVariable Long tcid){
        return creditService.getCreditsByTcId(tcid);
    }

    @PostMapping
    public ResponseEntity<CreditDto> addCredit(@RequestBody Credit c1){
        return ResponseEntity.ok(creditService.addCredit(c1));
    }
}
