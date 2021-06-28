package sendnewsletter;

import java.sql.SQLException;
import apt.ISmtpService;
import apt.SendNewsletterController;
import apt.SmtpService;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		SendNewsletterController controller = getController();
		String mailoutName = getMailoutName(controller);
		if (!"".equals(mailoutName)) {
			controller.sendNewsletter(mailoutName);
		}
		controller.close();
		System.out.println("All done");
	}

	private static SendNewsletterController getController() throws ClassNotFoundException {
		ISmtpService smtpService = new SmtpService();
		return new SendNewsletterController(smtpService);
	}

	private static String getMailoutName(SendNewsletterController controller) throws SQLException, ClassNotFoundException {
		System.out.println("The following mailouts are available:");
		for (String mailout: controller.getMailouts()) {
			System.out.println("  " + mailout);
		}
		return System.console().readLine("Which mailout do you want to send? ");
	}
}
