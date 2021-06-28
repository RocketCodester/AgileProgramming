package apt;

import java.sql.SQLException;
import java.util.List;

public class SendNewsletterController {
	private IDbLayer db;
	private ICustomerService customerService;
	private final ISmtpService smtpService;
	private IMailoutContentService contentService;

	public SendNewsletterController(ISmtpService smtpService) {
		this.smtpService = smtpService;
	}

	public List<String> getMailouts() throws SQLException, ClassNotFoundException {
		return getContentService().getMailoutNames();
	}

	public void sendNewsletter(String mailoutName) throws SQLException, ClassNotFoundException {
		List<Customer> recipients = getCustomerService().getCustomersToReceiveMailout();
		Mailout mailout = getContentService().getMailout(mailoutName);
		for (Customer customer: recipients) {
			smtpService.sendMail(customer.getEmailAddress(), mailout.getSubject(), 
					getBody(mailout, customer));
		}
	}
	
	protected ICustomerService getCustomerService() throws ClassNotFoundException {
		if (customerService == null) {
			customerService = new CustomerService(getDb());
		}
		return customerService;
	}

	private IDbLayer getDb() throws ClassNotFoundException {
		if (db == null) {
			db = new DbLayer();
		}
		return db;
	}

	private String getBody(Mailout mailout, Customer customer) {
		String body = mailout.getBodyFormat();
		return body.replaceAll("\\{firstName}", customer.getFirstName());
	}

	protected IMailoutContentService getContentService() throws ClassNotFoundException {
		if (contentService == null) {
			contentService = new MailoutContentService(getDb());
		}
		return contentService;
	}

	public void close() {
		customerService.close();
		contentService.close();
	}
}
