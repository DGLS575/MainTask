package com.main.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.main.Models.User;
import com.main.Utilities.UserUtility;

public class UserDAO {
	private Connection connection;
	private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

	/*
	 * Initialize connection to database
	 */
	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	/*
	 * Find user by credentials
	 * @param username the username
	 * @param password the password
	 */
	public User findUserByCredentials(String username, String password) {
		ResultSet resultSet = null;
		User user = null;
		String sql = "SELECT * FROM users WHERE username = ? AND passwd = ?";

		try (PreparedStatement statement = this.connection.prepareStatement(sql)){
			statement.setString(1, username);
			statement.setString(2, UserUtility.hashPassword(password));
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String storedPasswordHash = resultSet.getString("passwd");
				if (UserUtility.verifyPassword(password, storedPasswordHash)) {
					user = new User(resultSet.getInt("id"), resultSet.getString("username"));
				}
			}	
		} catch (SQLException e) {
			logger.severe("Database error while finding user by credentials");
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * Creates new user in database
	 * @param username the username
	 * @param password the password
	 * @param date the date
	 */
	public void createUser(String username, String password, LocalDate date) {
		String sql = "INSERT INTO users (username, passwd, date) VALUES (?, ?, ?)";

		try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
			statement.setString(1, username);
			statement.setString(2, UserUtility.hashPassword(password));
			statement.setDate(3, Date.valueOf(date));
			statement.executeUpdate();
		} catch (Exception e) {
			logger.severe("Error creating user: " + e.getMessage());
		}
	}

	/*
	 * Check if user exists
	 * @param username the username
	 * @return true if user exists, false otherwise
	 */
	public boolean userExists(String username) {
		String sql = "SELECT COUNT(*) AS user_count FROM users WHERE username = ?";
		try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("user_count") > 0;
			}
		} catch (SQLException e) {
			logger.severe("Database error while checking if user exists: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Count users in database
	 * 
	 * @return the number of users
	 */
	public int countUsers() {
		String sql = "SELECT COUNT(*) AS user_count FROM users";
		try (PreparedStatement statement = this.connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery()) {
			if (resultSet.next()) {
				return resultSet.getInt("user_count");
			}
		} catch (SQLException e) {
			logger.severe("Database error while counting users: " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
}
