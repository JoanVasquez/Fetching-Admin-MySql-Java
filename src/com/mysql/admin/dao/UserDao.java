package com.mysql.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.admin.connection.DBConnection;
import com.mysql.admin.model.User;

import java.sql.PreparedStatement;

public class UserDao {
	private DBConnection dbConnection;
	private PreparedStatement prepareStatement;
	private ResultSet resultSet;

	public UserDao() {
		dbConnection = DBConnection.openConnection();
	}

	public User fetchAdmin(String name, String password) throws SQLException {
		User user = null;
		prepareStatement = dbConnection.getConnection()
				.prepareStatement("SELECT user, authentication_string AS password FROM mysql.user WHERE user = ?");
		prepareStatement.setString(1, name);

		resultSet = prepareStatement.executeQuery();
		if (resultSet.next()) {
			String userName = resultSet.getString(1);
			String userPassword = resultSet.getString(2);
			prepareStatement = dbConnection.getConnection()
					.prepareStatement("SELECT UPPER(SHA1(UNHEX(SHA1(\"" + password + "\")))) AS password");
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				String fetchedPassword = resultSet.getString(1);
				if (userPassword.equals("*" + fetchedPassword)) {
					user = new User(userName, userPassword);
				}
			}

		}
		dbConnection.closeConnection();
		return user;
	}
}
//