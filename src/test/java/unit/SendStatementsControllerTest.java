package unit;

import apt.Customer;
import apt.CustomerService;
import apt.ICustomerService;
import apt.SendStatementsController;
import org.junit.jupiter.api.Test;
import testsupport.CustomerServiceStub;
import testsupport.MailoutContentServiceStub;
import testsupport.SmtpServiceSpy;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendStatementsControllerTest {
	@Test
	public void noBalancesShouldSendNoStatements() throws SQLException {
		CustomerServiceStub customerService = new CustomerServiceStub();
		customerService.addCustomer(new Customer("", "", false, 0, 0, 0));
		SmtpServiceSpy smtpService = new SmtpServiceSpy();
		MailoutContentServiceStub contentService = new MailoutContentServiceStub();
		SendStatementsController controller = new SendStatementsController(customerService, smtpService, contentService);
		
		controller.sendStatements();
		
		assertEquals(0, smtpService.getNumberOfEmailsSent());
	}
	
	@Test
	public void newPurchasesShouldSendStatement() throws SQLException {
		CustomerServiceStub customerService = new CustomerServiceStub();
		customerService.addCustomer(new Customer("John", "john@doe.com", false, 0, 5, 3));
		SmtpServiceSpy smtpService = new SmtpServiceSpy();
		MailoutContentServiceStub contentService = new MailoutContentServiceStub();
		SendStatementsController controller = new SendStatementsController(customerService, smtpService, contentService);
		
		controller.sendStatements();
		
		assertEquals(1, smtpService.getNumberOfEmailsSent());
		assertEquals("john@doe.com", smtpService.getLastRecipient());
		assertEquals("Your statement", smtpService.getLastSubject());
		assertEquals("Hello John.  Opening balance $0.00.  Purchases this month $5.00.  Payments this month $3.00.  Closing balance $2.00.  Payment is due on the 20th of the next month.", 
				smtpService.getLastBody());
	}
	
	@Test
	public void overdueShouldSendWarning() throws SQLException {
		CustomerServiceStub customerService = new CustomerServiceStub();
		customerService.addCustomer(new Customer("John", "john@doe.com", false, 5, 0, 3));
		SmtpServiceSpy smtpService = new SmtpServiceSpy();
		MailoutContentServiceStub contentService = new MailoutContentServiceStub();
		SendStatementsController controller = new SendStatementsController(customerService, smtpService, contentService);
		
		controller.sendStatements();
		
		assertEquals(1, smtpService.getNumberOfEmailsSent());
		assertEquals("john@doe.com", smtpService.getLastRecipient());
		assertEquals("Your account is overdue", smtpService.getLastSubject());
		assertEquals("Hello John.  Opening balance $5.00.  Purchases this month $0.00.  Payments this month $3.00.  Closing balance $2.00.  The overdue amount of $2.00 is due now.", 
				smtpService.getLastBody());
	}
	
	@Test
	public void balancePaidShouldSendStatement() throws SQLException {
		CustomerServiceStub customerService = new CustomerServiceStub();
		customerService.addCustomer(new Customer("John", "john@doe.com", false, 3, 5, 7));
		SmtpServiceSpy smtpService = new SmtpServiceSpy();
		MailoutContentServiceStub contentService = new MailoutContentServiceStub();
		SendStatementsController controller = new SendStatementsController(customerService, smtpService, contentService);
		
		controller.sendStatements();
		
		assertEquals(1, smtpService.getNumberOfEmailsSent());
		assertEquals("john@doe.com", smtpService.getLastRecipient());
		assertEquals("Your statement", smtpService.getLastSubject());
		assertEquals("Hello John.  Opening balance $3.00.  Purchases this month $5.00.  Payments this month $7.00.  Closing balance $1.00.  Payment is due on the 20th of the next month.", 
				smtpService.getLastBody());
	}
}
