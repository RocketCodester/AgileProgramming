package apt;

public class SmtpService implements ISmtpService {
	@Override
	public void sendMail(String emailAddress, String subject, String body) {
		int maxLengthMinusOne = 69;
		String showText = "..sending mail to " + emailAddress + ": " + subject + " (" + body;
		if (showText.length() > maxLengthMinusOne) {
			showText = showText.substring(0, maxLengthMinusOne - 3) + "...";
		}
		showText += ")";
		System.out.println(showText);
	}
}
