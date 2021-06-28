package testsupport;

import java.util.Arrays;
import java.util.List;
import apt.IMailoutContentService;
import apt.Mailout;

public class MailoutContentServiceStub implements IMailoutContentService {
	@Override
	public List<String> getMailoutNames() {
		return Arrays.asList("m1", "m2");
	}
	
	@Override
	public Mailout getMailout(String mailoutName) {
		return new Mailout("", "s", "Hello {firstName}");
	}
	
	@Override
	public Mailout getStatement() {
		return new Mailout("", "Your statement", "Hello {firstName}.  Opening balance ${openingBalance}.  Purchases this month ${purchasesThisMonth}.  Payments this month ${paymentsThisMonth}.  Closing balance ${closingBalance}.  Payment is due on the 20th of the next month.");
	}
	
	@Override
	public Mailout getOverdue() {
		return new Mailout("", "Your account is overdue", "Hello {firstName}.  Opening balance ${openingBalance}.  Purchases this month ${purchasesThisMonth}.  Payments this month ${paymentsThisMonth}.  Closing balance ${closingBalance}.  The overdue amount of ${overdueAmount} is due now.");
	}

	@Override
	public void close() { }
}
