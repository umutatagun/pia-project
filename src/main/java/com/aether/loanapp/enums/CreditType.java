package com.aether.loanapp.enums;

import lombok.Getter;

public enum CreditType {
    MORTGAGE(1.2),
    STUDENT(1.0),
    PERSONAL(1.8),
    SMALL_BUSINESS(1.4),
    LAND(1.6),
    OTHER(2.0);

    CreditType(double v) {
        this.val = v;
    }
    @Getter
    private Double val;
}
