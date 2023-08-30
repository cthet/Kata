package training.taxIncome;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static training.taxIncome.IndividualType.ADULT;
import static training.taxIncome.IndividualType.CHILD;


class TaxIncomeApplicationTests {

	private final Service service = new Service();

	@Test
	public void computeForSingle() {
		final Household household = new Household();
		household.add(new Individual(ADULT, BigDecimal.valueOf(32000)));
		assertEquals(0, BigDecimal.valueOf(3893.26).compareTo(this.service.compute(household)));
	}

	@Test
	public void computeForCoupleAndTwoChildren() {
		final Household household = new Household();
		household.add(new Individual(ADULT, BigDecimal.valueOf(32000)));
		household.add(new Individual(ADULT, BigDecimal.valueOf(23950)));
		household.add(new Individual(CHILD, null));
		household.add(new Individual(CHILD, null));
		assertEquals(0, BigDecimal.valueOf(3714.06).compareTo(this.service.compute(household)));
	}

}
