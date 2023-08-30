package training.cice;

import java.math.BigDecimal;
import java.util.List;

public class CiceService {
	private static final BigDecimal CICE_PERCENT = BigDecimal.valueOf(0.06);
	private static final BigDecimal MINIMUM_WAGE = BigDecimal.valueOf(1014);
	private static final BigDecimal MAX_INCOME_THRESHOLD = MINIMUM_WAGE.multiply(BigDecimal.valueOf(2.5));

	public BigDecimal compute(final List<Employee> employees) {

		BigDecimal totalDeclaredIncome = employees.stream()
				.filter(employee -> !Boolean.TRUE.equals(employee.getIntern()))
				.flatMap(employee -> employee.getEarnings().stream())
				.filter(earning -> isDeclaredEarning(earning.getType()))
				.map(Earning::getAmount)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		if (totalDeclaredIncome.compareTo(MAX_INCOME_THRESHOLD) <= 0) {
			return totalDeclaredIncome.multiply(CICE_PERCENT).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			return BigDecimal.ZERO;
		}
	}

	private boolean isDeclaredEarning(EarningType type) {
		return type == EarningType.SALARY || type == EarningType.OVERTIME ||
				type == EarningType.BONUS || type == EarningType.VACATION_PAY;
	}

}
