package calculatechange;

import apt.ChangeCalculator;

public class App {
	public static void main(String[] args) {
		double total = Double.parseDouble(System.console().readLine("What is the total to be paid? $"));
		ChangeCalculator calculator = new ChangeCalculator(total);
		double amount;
		do {
			System.out.format("The outstanding amount is $%.2f\n", calculator.getOutstanding());
			amount = Double.parseDouble(System.console().readLine("How much is the payment? $"));
		}
		while (!calculator.makePayment(amount));
		System.out.format("The change due is $%.2f\n", calculator.getChange());
		System.out.println("All done");
	}
}
