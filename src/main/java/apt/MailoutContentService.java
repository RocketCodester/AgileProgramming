package apt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MailoutContentService implements IMailoutContentService {
	private IDbLayer db;
	
	public MailoutContentService(IDbLayer db) {
		this.db = db;
	}
	
	@Override
	public List<String> getMailoutNames() throws SQLException {
		List<Mailout> mailouts = db.getMailouts();
		List<String> names = new ArrayList<String>();
		for (Mailout mailout: mailouts) {
			names.add(mailout.getName());
		}
		return names;
	}

	@Override
	public Mailout getMailout(String mailoutName) throws SQLException {
		List<Mailout> mailouts = db.getMailoutsWhere("mailoutName='" + mailoutName + "'");
		if (mailouts.size() == 1) return mailouts.get(0); 
		else return null;
	}

	@Override
	public Mailout getStatement() throws SQLException {
		return getMailout("Statement");
	}
	
	@Override
	public Mailout getOverdue() throws SQLException {
		return getMailout("Overdue");
	}

	@Override
	public void close() {
		db.close();
	}
}
