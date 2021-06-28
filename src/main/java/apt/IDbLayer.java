package apt;

import java.sql.SQLException;
import java.util.List;

public interface IDbLayer {
	void close();
	List<Customer> getCustomers() throws SQLException;
	List<Customer> getCustomersWhere(String whereCondition) throws SQLException;
	List<Mailout> getMailouts() throws SQLException;
	List<Mailout> getMailoutsWhere(String whereCondition) throws SQLException;
	double getCourseCost(String code);
	double getDiscount(String code);
	Product getProductByBarcode(String barcode) throws SQLException;
}
