package apt;

public class Customer {
	private String firstName;
	private String emailAddress;
	private boolean optInNewsletter;
	private double openingBalance;
	private double currentPurchases;
	private double currentPayments;
	
	public Customer(String firstName, String emailAddress, boolean optInNewsletter, 
			double openingBalance, double currentPurchases, double currentPayments) {
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.optInNewsletter = optInNewsletter;
		this.openingBalance = openingBalance;
		this.currentPurchases = currentPurchases;
		this.currentPayments = currentPayments;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	
	public boolean getOptInNewsletter() {
		return optInNewsletter;
	}
	
	public double getOpeningBalance() {
		return openingBalance;
	}
	
	public double getCurrentPurchases() {
		return currentPurchases;
	}
	
	public double getCurrentPayments() {
		return currentPayments;
	}
}
