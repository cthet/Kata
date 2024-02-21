package test.java.com.taxIncome;

import main.java.com.taxIncome.domain.Adult;
import main.java.com.taxIncome.domain.Household;
import main.java.com.taxIncome.domain.UseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaxIncomeTest {
    Household household;
    UseCase useCase;

    @BeforeEach
    void setup(){
        household = new Household();
        useCase = new UseCase(household);
    }

    @Test
    void shouldReturnATotalIncomeOf9807If1AdultWithIncomeOf9807(){
        setABasis(9807);
        assertEquals(BigDecimal.valueOf(9807),useCase.getTotalIncome());
    }

    @Test
    void shouldReturnATotalIncomeOf100000With2Adults(){
        Adult adult1 = new Adult(BigDecimal.valueOf(75000));
        Adult adult2 = new Adult(BigDecimal.valueOf(25000));
        List<Adult> adults = new ArrayList<>();
        adults.add(adult1);
        adults.add(adult2);
        household.setAdults(adults);
        assertEquals(BigDecimal.valueOf(100000),useCase.getTotalIncome());
    }

    @Test
    void ShouldReturnFamilyQuotientOf1If1Adult(){
        List<Adult> adults = setUp1Adult();
        household.setAdults(adults);
        assertEquals(1.00,useCase.getFamilyQuotient());
    }

    @Test
    void ShouldReturnFamilyQuotientOf2andhalfIf2AdultAnd1Child(){
        List<Adult> adults = setUp1Adult();
        adults.add(new Adult());
        household.setAdults(adults);
        household.setChildNumber(1);
        assertEquals(2.5,useCase.getFamilyQuotient());
    }

    @Test
    void shouldThrowAnExceptionIfChildNumberIsNegative(){
        assertThrows(IllegalArgumentException.class, ()->household.setChildNumber(-1));
    }


    @Test
    void shouldReturnABasisOf9807If1AdultWithIncomeOf9807(){
        setABasis(9807);
        assertEquals(BigDecimal.valueOf(9807).setScale(2),useCase.getBasis());
    }


    @Test
    void shouldReturnABasisOf25000IfTotalIncomeIs100000AndFamilyQuotientIs4(){
        Adult adult1 = new Adult(BigDecimal.valueOf(50000));
        Adult adult2 = new Adult(BigDecimal.valueOf(50000));
        List<Adult> adults = new ArrayList<>();
        adults.add(adult1);
        adults.add(adult2);
        household.setAdults(adults);
        household.setChildNumber(4);
        BigDecimal expected = BigDecimal.valueOf(25000).setScale(2);
        assertEquals(expected,useCase.getBasis());
    }

    @Test
    void shouldReturnAGrossTaxIncomeOf0IfBasisIs9807(){
        setABasis(9807);
        assertEquals(BigDecimal.ZERO,useCase.getGrossTaxIncome());
    }

    @Test
    void shouldReturnAGrossTaxIncomeOf2419AND6IfGrossTaxIncomeIs2419AND6ANDFamilyQuotientIs1(){
        setABasis(27086);
        assertEquals(BigDecimal.valueOf(2419.06),useCase.getTaxIncome());
    }

    @Test
    void shouldReturnAGrossTaxIncomeOf16078AND36IfBasisIs72617(){
        setABasis(72617);
        assertEquals(BigDecimal.valueOf(16078.36),useCase.getGrossTaxIncome());
    }

    @Test
    void shouldReturnAGrossTaxIncomeOf49356And42IfBasisIs153783(){
        setABasis(153783);
        assertEquals(BigDecimal.valueOf(49356.42),useCase.getGrossTaxIncome());
    }

    @Test
    void shouldReturnAGrossTaxIncomeOf49356And42IfBasisIs200000(){
        setABasis(200000);
        assertEquals(BigDecimal.valueOf(70154.07),useCase.getGrossTaxIncome());
    }

    @Test
    void shouldReturnATaxIncomeOfGrossTaxIncomeOf49356And42IfBasisIs200000(){
        setABasis(200000);
        assertEquals(BigDecimal.valueOf(70154.07),useCase.getGrossTaxIncome());
    }

    private static List<Adult> setUp1Adult() {
        List<Adult> adults = new ArrayList<>();
        adults.add(new Adult());
        return adults;
    }

    private void setABasis(int val) {
        Adult adult = new Adult(BigDecimal.valueOf(val));
        List<Adult> adults = new ArrayList<>();
        adults.add(adult);
        household.setAdults(adults);
    }

}
