package com.main.Models;

import java.time.LocalDate;

import com.main.DAO.VisitDAO;
import com.main.DAO.UserDAO;
import com.main.Views.ViewFactory;

import javafx.collections.ObservableList;

public class Model {
	private static Model model; //Singleton instance
	private final ViewFactory viewFactory;

	public final UserDAO userDAO;
	public final VisitDAO visitDAO;

	private boolean loginSuccessFlag;
	private User currentUser;

	private Model() {
		viewFactory = new ViewFactory();
		userDAO = new UserDAO(new DatabaseDriver().getConnection());
		visitDAO = new VisitDAO(new DatabaseDriver().getConnection());
		this.currentUser = null;
	}

	/*
	 * Returns the singleton instance of the Model class
	 * @return the singleton instance
	 */
	public static synchronized Model getInstance() {
		if (model == null)
			model = new Model();

		return model;
	}

	/*
	 * Get ViewFactory instance
	 * @return the ViewFactory instance
	 */
	public ViewFactory getViewFactory() {
		return viewFactory;
	}

	/*
	 * Create new user in database
	 * @param username the username
	 * @param password the password
	 */
	public void createUser(String username, String password) {
		userDAO.createUser(username, password, LocalDate.now());
	}

	public void checkCredentials(String username, String password) {
		User user = userDAO.findUserByCredentials(username, password);

		if (user != null) {
			this.loginSuccessFlag = true;
			this.currentUser = user;
		} else {
			this.loginSuccessFlag = false;
			this.currentUser = null;
		}
	}

	/*
	 * Get current user name
	 * 
	 * @return the current user name
	 */
	public String getCurrentUserName() {
		return currentUser == null ? null : currentUser.getUsernameProperty();
	}

	/*
	 * Get current user id
	 * 
	 * @return the current user id
	 */
	public int getCurrentUserId() {
		return currentUser == null ? null : currentUser.getId();
	}

	/*
	 * @return the loginSuccessFlag
	 */
	public boolean getLoginSuccessFlag() {
		return loginSuccessFlag;
	}

	/*
	 * @param loginSuccessFlag the loginSuccessFlag to set
	 */
	public void setLoginSuccessFlag(boolean loginSuccessFlag) {
		this.loginSuccessFlag = loginSuccessFlag;
	}

	/*
	 * Check if user exists
	 * @param username the username
	 * @return true if user exists, false otherwise
	 */
	public boolean userExists(String username) {
		return userDAO.userExists(username);
	}

	/*
	 * Check if there are users in the database
	 * @return true if there are users, false otherwise
	 */
	public boolean hasRegisteredUsers() {
		return userDAO.countUsers() > 0;
	}

	/* 
	 * Create author
	*/
	public void createAuthor(String firstName, String lastName, String email, String date, String time, boolean completed, double income, String note) {
		visitDAO.create(firstName, lastName, email, date, time, completed, income, note);
	}

	/*
	 * Get all authors
	 * @return the list of authors
	 */
	public ObservableList<Visit> getAllVisits() {
		return visitDAO.findAll();
	}
}
