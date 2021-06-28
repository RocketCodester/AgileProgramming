package apt;

import java.sql.SQLException;
import java.util.List;

public interface IMailoutContentService {
	List<String> getMailoutNames() throws SQLException;
	Mailout getMailout(String mailoutName) throws SQLException;
	Mailout getStatement() throws SQLException;
	Mailout getOverdue() throws SQLException;
	void close();
}