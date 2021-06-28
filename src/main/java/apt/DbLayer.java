package apt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbLayer implements IDbLayer {
	private Connection connection;
	
	public DbLayer() throws ClassNotFoundException {
		Class.forName(Settings.getInstance().getDbConnectionDriver());
	}
	
	@Override
	public void close() {
		if (connection != null) {
			try {
				if (!connection.getAutoCommit()) connection.rollback();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}
	
	@Override
	public List<Customer> getCustomers() throws SQLException {
		return getCustomersWhere(null);
	}

	@Override
	public List<Customer> getCustomersWhere(String whereCondition) throws SQLException {
		Connection connection = getConnection();
		String sql = "SELECT firstName, emailAddress, optInNewsletter, openingBalance, currentPurchases, currentPayments FROM Customer";
		if (whereCondition != null) sql += " WHERE " + whereCondition;
		try (Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(sql)) {
			List<Customer> customers = new ArrayList<>();
			while (results.next()) {
				customers.add(new Customer(results.getString(1), results.getString(2),
						results.getBoolean(3), results.getDouble(4), results.getDouble(5),
						results.getDouble(6)));
			}
			return customers;
		}
	}
	
	@Override
	public List<Mailout> getMailouts() throws SQLException {
		return getMailoutsWhere(null);
	}

	@Override
	public List<Mailout> getMailoutsWhere(String whereCondition) throws SQLException {
		Connection connection = getConnection();
		String sql = "SELECT mailoutName, subject, bodyFormat FROM Mailout";
		if (whereCondition != null) sql += " WHERE " + whereCondition;
		try (Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery(sql)) {
			List<Mailout> mailouts = new ArrayList<>();
			while (results.next()) {
				mailouts.add(new Mailout(results.getString(1), results.getString(2), 
						results.getString(3)));
			}
			return mailouts;
		}
	}
	
	@Override
	public double getCourseCost(String code) {
		try {
			Double cost = getLookupAsDouble("Course", "code", code, "cost");
			if (cost == null) return 0;
			else return cost;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public double getDiscount(String code) {
		try {
			Double percentage = getLookupAsDouble("Discount", "code", code, "percentage");
			if (percentage == null) return 0;
			else return percentage;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public Product getProductByBarcode(String barcode) throws SQLException {
		Connection connection = getConnection();
		String sql = "SELECT barcode, price, productName FROM Product WHERE barcode=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, barcode);
			try (ResultSet results = statement.executeQuery()) {
				if (results.next()) return new Product(results.getString(1),
						results.getDouble(2), results.getString(3));
				else return null;
			}
		}
	}
	
	protected Double getLookupAsDouble(String tableName, String keyField, 
			String keyValue, String lookupField) throws SQLException {
		Connection connection = getConnection();
		String sql = "SELECT " + lookupField + " FROM " + tableName + " WHERE " + keyField + "=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, keyValue);
			try (ResultSet results = statement.executeQuery()) {
				if (results.next()) return results.getDouble(1);
				else return null;
			}
		}
	}
	
	protected void setLookup(String tableName, String keyField, String keyValue, 
			String lookupField, double lookupValue) throws SQLException {
		Connection connection = getConnection();
		String sql = "UPDATE " + tableName + " SET " + lookupField + "=? WHERE " + keyField + "=?";
		int numberOfUpdates;
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDouble(1, lookupValue);
			statement.setString(2, keyValue);
			numberOfUpdates = statement.executeUpdate();
		}
		if (numberOfUpdates == 0) {
			sql = "INSERT INTO " + tableName + " (" + lookupField + "," + keyField + ") VALUES (?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setDouble(1, lookupValue);
				statement.setString(2, keyValue);
				statement.executeUpdate();
			}
		}
	}
	
	protected synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(
					Settings.getInstance().getDbConnectionUrl());
		}
		return connection;
	}
	
	protected void startTransaction() throws SQLException {
		Connection connection = getConnection();
		if (!connection.getAutoCommit()) {
        	throw new RuntimeException(
        			"Cannot start a transaction when one is already active.");
		}
		connection.setAutoCommit(false);
	}
	
	protected void commitTransaction() throws SQLException {
        if (connection == null || connection.getAutoCommit()) {
        	throw new RuntimeException(
        			"Cannot commit a transaction that has not been started.");
        }
        connection.commit();
        connection.setAutoCommit(true);
	}
	
	protected void rollbackTransaction() throws SQLException {
        if (connection == null || connection.getAutoCommit()) {
        	throw new RuntimeException(
        			"Cannot rollback a transaction that has not been started.");
        }
        connection.rollback();
        connection.setAutoCommit(true);
	}
}
