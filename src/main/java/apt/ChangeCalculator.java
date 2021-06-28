package apt;

public class ChangeCalculator {
	private double total;
	private double payment;
	private double change;
	private double outstanding;
	
	public ChangeCalculator(double total) {
		this.total = total;
		payment = change = 0;
		outstanding = total;
	}
	
	public double getOutstanding() {
		return outstanding;
	}
	
	public double getChange() {
		return change;
	}
	
	public boolean makePayment(double amount) {
		payment += amount;
		if (payment >= total) {
			outstanding = 0;
			change = payment - total;
			return true;
		} else {
			outstanding = total - payment;
			return false;
		}
	}
}
