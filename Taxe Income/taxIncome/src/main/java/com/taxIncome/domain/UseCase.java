package main.java.com.taxIncome.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UseCase {
    private Household household;

    public UseCase(Household household) {
        this.household = household;
    }

    public BigDecimal getTotalIncome() {
        return household.getAdults().stream()
                .map(Adult::getIncome)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public double getFamilyQuotient() {
        return household.getAdults().size() + 0.5 * household.getChildNumber();
    }


    public BigDecimal getBasis() {
        return getTotalIncome().divide(BigDecimal.valueOf(getFamilyQuotient()), 2, RoundingMode.HALF_DOWN);
    }


    public BigDecimal getGrossTaxIncome() {
        BigDecimal grossTaxIncome = BigDecimal.ZERO;
        BigDecimal basis = getBasis();

        if(basis.compareTo(BigDecimal.valueOf(9807))<=0){
            return BigDecimal.ZERO;
        }

        if(basis.compareTo(BigDecimal.valueOf(153783))>0 ){
            BigDecimal basis_45 = basis.subtract(BigDecimal.valueOf(153783));
            grossTaxIncome = grossTaxIncome.add(basis_45.multiply(BigDecimal.valueOf(0.45)));
            basis = basis.subtract(basis_45);
        }

        if(basis.compareTo(BigDecimal.valueOf(72617))>0 ){
            BigDecimal basis_41 = basis.min(BigDecimal.valueOf(153783)).subtract(BigDecimal.valueOf(72617));
            grossTaxIncome = grossTaxIncome.add(basis_41.multiply(BigDecimal.valueOf(0.41)));
            basis = basis.subtract(basis_41);
        }

        if(basis.compareTo(BigDecimal.valueOf(27086))>0 ){
            BigDecimal basis_30 = basis.min(BigDecimal.valueOf(72617)).subtract(BigDecimal.valueOf(27086));
            grossTaxIncome = grossTaxIncome.add(basis_30.multiply(BigDecimal.valueOf(0.30)));
            basis = basis.subtract(basis_30);
        }

        if(basis.compareTo(BigDecimal.valueOf(9807))>0){
            BigDecimal basis_14 = basis.min(BigDecimal.valueOf(27086)).subtract(BigDecimal.valueOf(9807));
            grossTaxIncome = grossTaxIncome.add(basis_14.multiply(BigDecimal.valueOf(0.14)));
        }

        return grossTaxIncome.setScale(2, BigDecimal.ROUND_HALF_UP);

    }

    public BigDecimal getTaxIncome() {
        return getGrossTaxIncome().multiply(BigDecimal.valueOf(getFamilyQuotient())).setScale(2);
    }
}
