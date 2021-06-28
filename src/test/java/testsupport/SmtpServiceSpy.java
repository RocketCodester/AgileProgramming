package testsupport;

import apt.ISmtpService;

public class SmtpServiceSpy implements ISmtpService {
	private int numberOfEmailsSent = 0;
	private String lastRecipient;
	private String lastSubject;
	private String lastBody;
	
	public int getNumberOfEmailsSent() {
		return numberOfEmailsSent;
	}
	
	public String getLastRecipient() {
		return lastRecipient;
	}

	public String getLastSubject() {
		return lastSubject;
	}

	public String getLastBody() {
		return lastBody;
	}
	
	@Override
	public void sendMail(String emailAddress, String subject, String body) {
		numberOfEmailsSent++;
		lastRecipient = emailAddress;
		lastSubject = subject;
		lastBody = body;
	}
}
