package training.taxIncome;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    // Utilisation d'un tableau pour stocker les différentes tranches fiscales
    private static final List<TaxBracket> TAX_BRACKETS = Arrays.asList(
            new TaxBracket(BigDecimal.valueOf(153783), BigDecimal.valueOf(0.45)),
            new TaxBracket(BigDecimal.valueOf(72617), BigDecimal.valueOf(0.41)),
            new TaxBracket(BigDecimal.valueOf(27086), BigDecimal.valueOf(0.30)),
            new TaxBracket(BigDecimal.valueOf(9807), BigDecimal.valueOf(0.14))
    );

    public BigDecimal compute(final Household household) {
        BigDecimal totalPayment = BigDecimal.ZERO;

        // Filtrer les individus adultes dans le ménage
        List<Individual> adults = household.getIndividuals()
                .stream()
                .filter(individual -> individual.getType() == IndividualType.ADULT)
                .collect(Collectors.toList());

        // Filtrer les individus enfants dans le ménage
        List<Individual> children = household.getIndividuals()
                .stream()
                .filter(individual -> individual.getType() == IndividualType.CHILD)
                .collect(Collectors.toList());

        // Calculer le total des revenus des adultes
        BigDecimal totalAdultRevenue = adults.stream()
                .map(Individual::getRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculer le quotient familial
        BigDecimal numAdults = BigDecimal.valueOf(adults.size());
        BigDecimal numChildren = BigDecimal.valueOf(children.size());
        BigDecimal half = new BigDecimal("0.5");

        BigDecimal familyQuotient = numAdults.add(half.multiply(numChildren));

        // Calculer l'impôt sur le revenu par parts fiscal
        BigDecimal basis = totalAdultRevenue.divide(familyQuotient);
        BigDecimal grossTaxIncome = computeGrossTaxIncome(basis);
        // Calculer la somme à payer pour le foyer fiscal
        BigDecimal houseHoldPayment = grossTaxIncome.multiply(familyQuotient);

        return houseHoldPayment;
    }


    private BigDecimal computeGrossTaxIncome(BigDecimal basis) {
        BigDecimal grossTaxIncome = BigDecimal.ZERO;

        for (TaxBracket bracket : TAX_BRACKETS) {
            if (basis.compareTo(bracket.getLimit()) > 0) {
                BigDecimal diff = basis.subtract(bracket.getLimit());
                BigDecimal taxAmount = diff.multiply(bracket.getRate());
                grossTaxIncome = grossTaxIncome.add(taxAmount);
                basis = basis.subtract(diff);
            }
        }
        return grossTaxIncome;
    }

}
