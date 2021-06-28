package apt;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements ICustomerService {
	private IDbLayer db;
	
	public CustomerService(IDbLayer db) {
		this.db = db;
	}
	
	@Override
	public List<Customer> getCustomersToReceiveMailout() throws SQLException {
		return db.getCustomersWhere("optInNewsletter");
	}
	
	@Override
	public List<Customer> getAllCustomers() throws SQLException {
		return db.getCustomers();
	}
	
	@Override
	public void close() {
		db.close();
	}
}
