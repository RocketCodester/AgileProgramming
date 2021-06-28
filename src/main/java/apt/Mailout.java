package apt;

public class Mailout {
	private String name;
	private String subject;
	private String bodyFormat;
	
	public Mailout(String name, String subject, String bodyFormat) {
		this.name = name;
		this.subject = subject;
		this.bodyFormat = bodyFormat;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getBodyFormat() {
		return bodyFormat;
	}
}
