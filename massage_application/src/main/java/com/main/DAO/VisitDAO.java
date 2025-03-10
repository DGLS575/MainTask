package com.main.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
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

	public void create(String firstName, String lastName, String phone, String email, LocalDateTime date, boolean completed, double income, String note) {
		String sql = "INSERT INTO visits (first_name, last_name, phone, email, date, completed, income, note, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int userId = Model.getInstance().getCurrentUserId();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, phone);
			statement.setString(4, email);
			statement.setString(5, date.toString());
			statement.setInt(6, completed ? 1 : 0);
			statement.setDouble(7, income);
			statement.setString(8, note);
			statement.setInt(9, userId);

			statement.executeUpdate();

			logger.info("Visit created successfully!");
		} catch (Exception ex) {
			InfoUtility.error("Error creating visit: " + ex.getMessage());
		}
	}

	
	public void update(Visit entity) {
		String sql = "UPDATE visits SET first_name = ?, last_name = ?, phone = ?, email = ?, date = ?, completed = ?, income = ?, note = ? WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, entity.firstNameProperty().get());
			statement.setString(2, entity.lastNameProperty().get());
			statement.setString(3, entity.phoneProperty().get());
			statement.setString(4, entity.emailProperty().get());
			statement.setString(5, entity.dateProperty().get().toString());
			statement.setInt(6, entity.completedProperty().get() ? 1 : 0);
			statement.setDouble(7, entity.incomeProperty().get());
			statement.setString(8, entity.noteProperty().get());
			statement.setInt(9, entity.idProperty().get());

			logger.info("Updating visit with id: " + entity.idProperty().get());
			int lines = statement.executeUpdate();
			logger.info("Visit updated successfully! With " + lines + " lines affected.");
		} catch (Exception ex) {
			InfoUtility.error("Error updating visit: " + ex.getMessage());
		}
	}

	
	public void delete(int id) {
		String sql = "DELETE FROM visits WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
			logger.info("Visit deleted successfully!");
		} catch (Exception ex) {
			InfoUtility.error("Error deleting visit: " + ex.getMessage());
		}
	}

	
	public ObservableList<Visit> findAll() {
		ObservableList<Visit> visits = FXCollections.observableArrayList();
		String sql = "SELECT id, first_name, last_name, phone, email, date, completed, income, note FROM visits";

		try (PreparedStatement statement = this.connection.prepareStatement(sql)){
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String phone = resultSet.getString("phone");
				String email = resultSet.getString("email");
				String date = resultSet.getString("date");
				boolean completed = resultSet.getInt("completed") == 1;
				double income = resultSet.getDouble("income");
				String note = resultSet.getString("note");
				
				LocalDateTime localDate;
				try {
					localDate = LocalDateTime.parse(date);
				} catch (Exception ex) {
					localDate = LocalDateTime.MIN.plusDays(1);
				}

				Visit visit = new Visit(id, firstName, lastName, phone, email, localDate, completed, income, note);
				visits.add(visit);
			}
		} catch (Exception ex) {
			InfoUtility.error("Error fetching visits: " + ex.getMessage());
		}
		
		return visits;
	}
}
