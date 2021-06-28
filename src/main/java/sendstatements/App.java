package sendstatements;

import java.sql.SQLException;
import apt.CustomerService;
import apt.DbLayer;
import apt.ICustomerService;
import apt.IDbLayer;
import apt.IMailoutContentService;
import apt.ISmtpService;
import apt.MailoutContentService;
import apt.SendStatementsController;
import apt.SmtpService;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SendStatementsController controller = getController();
		controller.sendStatements();
		System.out.println("All done");
	}

	private static SendStatementsController getController() throws ClassNotFoundException {
		IDbLayer db = new DbLayer();
		ICustomerService customerService = new CustomerService(db);
		ISmtpService smtpService = new SmtpService();
		IMailoutContentService contentService = new MailoutContentService(db);
		return new SendStatementsController(customerService, smtpService, contentService);
	}
}
