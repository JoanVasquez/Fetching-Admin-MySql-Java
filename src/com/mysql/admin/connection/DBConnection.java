package com.mysql.admin.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection dbConnection; // VAR OF THIS CLASS
	private Connection connection; // VAR OF CONNECTION CLASS - TO CREATE THE DATABASE CONNECTION

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // LOAD THE MYSQL DRIVER
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "joanvasquez",
					"Password2288_"); // CREATE THE DATABASE CONNECTION
			System.out.println("Connection established...");
		} catch (SQLException ex) {
			System.err.println("Connection to the DataBase failed " + ex.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Loading driver failed: " + e.getMessage());
		}
	}

	// SINGLETON METHOD - RETURN AN INSTANCE OF THIS CLASS
	public synchronized static DBConnection openConnection() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	// RETURN AN INSTANCE OF connection
	public Connection getConnection() {
		return connection;
	}

	// CLOSE THIS CONNECTION
	public void closeConnection() {

		if ((connection != null) && (dbConnection != null)) {
			try {
				connection.close();
				dbConnection = null;
			} catch (SQLException e) {
				System.err.println("Failed to close the connection " + e.getMessage());
			}
		} else {
			System.out.println("Not opened connection");
		}

	}

}
