package testsupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import apt.DbLayer;

public class DbSupport extends DbLayer {
	public DbSupport() throws ClassNotFoundException {
		super();
	}

	public void setCourseCost(String courseCode, double courseCost) throws SQLException {
		setLookup("Course", "code", courseCode, "cost", courseCost);
	}
	
	public void setDiscount(String promotionCode, double percentage) throws SQLException {
		setLookup("Discount", "code", promotionCode, "percentage", percentage);
	}
	
	public void setProductPrice(String barcode, double price) throws SQLException {
		Connection connection = getConnection();
		String sql = "UPDATE Product SET price=? WHERE barcode=?";
		int numberOfUpdates;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDouble(1, price);
			statement.setString(2, barcode);
			numberOfUpdates = statement.executeUpdate();
		}
		if (numberOfUpdates == 0) {
			sql = "INSERT INTO Product (price,barcode,productName) VALUES (?,?,'')";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setDouble(1, price);
				statement.setString(2, barcode);
				statement.executeUpdate();
			}
		}
	}
	
	public void deleteProduct(String barcode) throws SQLException {
		Connection connection = getConnection();
		String sql = "DELETE FROM Product WHERE barcode=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, barcode);
			statement.executeUpdate();
		}
	}
}
