package apt;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
	List<Customer> getCustomersToReceiveMailout() throws SQLException;
	List<Customer> getAllCustomers() throws SQLException;
	void close();
}