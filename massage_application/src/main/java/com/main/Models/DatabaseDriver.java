package com.main.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.main.Utilities.InfoUtility;

public class DatabaseDriver {
	//Database connection instance
	private Connection connection;

	private static final Logger logger = Logger.getLogger(DatabaseDriver.class.getName());

	/*
	 * Manage connection to database
	 */
	public DatabaseDriver() {
		try {
			logger.info("Connecting to database...");
			this.connection = DriverManager.getConnection("jdbc:sqlite:massage.db");
			logger.info("Connected to database");
		} catch (SQLException ex) {
			InfoUtility.error("Error connecting to database: " + ex.getMessage());
		}
	}

	/*
	 * Get connection to database
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/*
	 * Close connection to database
	 */
	public void closeConnection() {
		try {
			//Close the connection if not already closed
			connection.close();
			logger.info("Database connection closed");
		} catch (SQLException e) {
			InfoUtility.error("Error closing database connection: " + e.getMessage());
		}
	}
}
