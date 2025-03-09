package com.main.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.main.Models.Visit;
import com.main.Models.Model;
import com.main.Utilities.InfoUtility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitDAO {

	private Connection connection;
	private static final Logger logger = Logger.getLogger(VisitDAO.class.getName());

	public VisitDAO(Connection connection) {		
		this.connection = connection;
	}

	public Visit findById(int id) {
		return null;
	}

	public void create(String firstName, String lastName, String email, String date, String time, boolean completed, double income, String note) {
		String sql = "INSERT INTO authors (first_name, last_name, email, city, date, user_id) VALUES (?, ?, ?, ?, ?, ?)";
		int userId = Model.getInstance().getCurrentUserId();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, date);
			statement.setString(5, time);
			statement.setBoolean(6, completed);
			statement.setDouble(7, income);
			statement.setString(8, note);

			statement.setDate(5, Date.valueOf(LocalDate.now()));
			statement.setInt(6, userId);

			statement.executeUpdate();

			logger.info("Visit created successfully!");
		} catch (Exception ex) {
			InfoUtility.error("Error creating visit: " + ex.getMessage());
		}
	}

	
	public void update(Visit entity) {
		
	}

	
	public void delete(int id) {
		
	}

	
	public ObservableList<Visit> findAll() {
		ObservableList<Visit> visits = FXCollections.observableArrayList();
		String sql = "SELECT id, first_name, last_name, email, city, date FROM authors";

		try (PreparedStatement statement = this.connection.prepareStatement(sql)){
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String date = resultSet.getString("date");
				String time = resultSet.getString("time");
				boolean completed = resultSet.getBoolean("completed");
				double income = resultSet.getDouble("income");
				String note = resultSet.getString("note");

				Visit visit = new Visit(id, firstName, lastName, email, date, time, completed, income, note);
				visits.add(visit);
			}
		} catch (Exception ex) {
			InfoUtility.error("Error fetching visits: " + ex.getMessage());
		}
		
		return visits;
	}
}
