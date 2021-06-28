package apt;

public interface ISmtpService {
	void sendMail(String emailAddress, String subject, String body);
}