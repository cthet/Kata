package training;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CiceServiceTests {

	private final CiceService service = new CiceService();

	@Test
	public void compute() {
		final List<Employee> employees = new ArrayList<>();
		final Employee intern = new Employee();
		intern.setIntern(true);
		intern.setEarnings(new ArrayList<>());
		intern.getEarnings().add(new Earning("500", EarningType.BONUS));
		employees.add(intern);
		final Employee employee = new Employee();
		employee.setIntern(false);
		employee.setEarnings(new ArrayList<>());
		employee.getEarnings().add(new Earning("1000", EarningType.SALARY));
		employee.getEarnings().add(new Earning("100", EarningType.BONUS));
		employees.add(employee);
		final Employee manager = new Employee();
		manager.setIntern(false);
		manager.setEarnings(new ArrayList<>());
		manager.getEarnings().add(new Earning("1000", EarningType.SALARY));
		employees.add(manager);
		assertEquals(BigDecimal.valueOf(126.00).setScale(2, RoundingMode.HALF_UP), this.service.compute(employees));
	}

}
