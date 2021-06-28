package apt;

import java.sql.SQLException;
import java.util.List;

public class SendStatementsController {
	private ICustomerService customerService;
	private ISmtpService smtpService;
	private IMailoutContentService contentService;
	
	public SendStatementsController(ICustomerService customerService, 
			ISmtpService smtpService, IMailoutContentService contentService) {
		this.customerService = customerService;
		this.smtpService = smtpService;
		this.contentService = contentService;
	}

	public void sendStatements() throws SQLException {
		List<Customer> customers = customerService.getAllCustomers();
		for (Customer customer: customers) {
			if (shouldSendStatmentFor(customer)) {
				Mailout mailout = getMailoutFor(customer);
				smtpService.sendMail(customer.getEmailAddress(), mailout.getSubject(), 
						getBody(mailout, customer));
			}
		}
	}
	
	private boolean shouldSendStatmentFor(Customer customer) {
		return customer.getOpeningBalance() > 0 || customer.getCurrentPurchases() > 0 
				|| customer.getCurrentPayments() > 0; 
	}
	
	private Mailout getMailoutFor(Customer customer) throws SQLException {
		double overdueAmount = customer.getOpeningBalance() - customer.getCurrentPayments();
		if (overdueAmount >= 0) return contentService.getOverdue();
		else return contentService.getStatement();
	}
	
	private String getBody(Mailout mailout, Customer customer) {
		double closingBalance = customer.getOpeningBalance() + customer.getCurrentPurchases() - customer.getCurrentPayments();
		double overdueAmount = customer.getOpeningBalance() - customer.getCurrentPayments();
		String body = mailout.getBodyFormat();
		body = body.replaceAll("\\{firstName\\}", customer.getFirstName())
				.replaceAll("\\{openingBalance\\}", String.format("%.2f", customer.getOpeningBalance()))
				.replaceAll("\\{purchasesThisMonth\\}", String.format("%.2f", customer.getCurrentPurchases()))
				.replaceAll("\\{paymentsThisMonth\\}", String.format("%.2f", customer.getCurrentPayments()))
				.replaceAll("\\{closingBalance\\}", String.format("%.2f", closingBalance))
				.replaceAll("\\{overdueAmount\\}", String.format("%.2f", overdueAmount));
		return body;
	}

	public void close() {
		customerService.close();
		contentService.close();
	}
}
