package main.java.com.taxIncome.domain;

import java.math.BigDecimal;

public class Adult {
    private BigDecimal income = BigDecimal.ZERO;


    public Adult() {
    }

    public Adult(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getIncome() {
        return income;
    }
}
