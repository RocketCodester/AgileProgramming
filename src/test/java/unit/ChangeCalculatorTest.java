package unit;

import apt.ChangeCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeCalculatorTest {
	@Test
	public void paymentShouldBeIncomplete() {
		ChangeCalculator calculator = new ChangeCalculator(5);
		
		boolean isComplete = calculator.makePayment(3);
		
		assertFalse(isComplete);
		assertEquals(2, calculator.getOutstanding(), 0.001);
		assertEquals(0, calculator.getChange(), 0.001);
	}
	
	@Test
	public void shouldCalculateCorrectChange()
	{
		ChangeCalculator calculator = new ChangeCalculator(3);
		
		boolean isComplete = calculator.makePayment(5);
		
		assertTrue(isComplete);
		assertEquals(0, calculator.getOutstanding(), 0.001);
		assertEquals(2, calculator.getChange(), 0.001);
	}

	@Test
	public void shouldCalculateNoChange()
	{
		ChangeCalculator calculator = new ChangeCalculator(5);

		boolean isComplete = calculator.makePayment(5);

		assertTrue(isComplete);
		assertEquals(0, calculator.getOutstanding(), 0.001);
		assertEquals(0, calculator.getChange(), 0.001);
	}
}
