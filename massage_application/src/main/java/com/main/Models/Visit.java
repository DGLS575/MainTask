package com.main.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Visit {
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private StringProperty date;
	private StringProperty time;
	private BooleanProperty completed;
	private DoubleProperty income;
	private StringProperty note;

	/*
	 * Author constructor
	 * 
	 * @param id the author id
	 * @param firstName the author first name
	 * @param lastName the author last name
	 * @param email the author email
	 * @param city the author city
	 */
	public Visit(int id, String firstName, String lastName, String email, String date, String time, boolean completed, double income, String note) {
		this.id = new SimpleIntegerProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.email = new SimpleStringProperty(email);
		this.date = new SimpleStringProperty(date);
		this.time = new SimpleStringProperty(time);
		this.completed = new SimpleBooleanProperty(completed);
		this.income = new SimpleDoubleProperty(income);
		this.note = new SimpleStringProperty(note);
	}

	public IntegerProperty idProperty() {
		return id;
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public StringProperty emailProperty() {
		return email;
	}

	public StringProperty dateProperty() {
		return date;
	}

	public StringProperty timeProperty() {
		return time;
	}

	public BooleanProperty completedProperty() {
		return completed;
	}

	public DoubleProperty incomeProperty() {
		return income;
	}

	public StringProperty noteProperty() {
		return note;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public void setDate(String date) {
		this.date.set(date);
	}

	public void setTime(String time) {
		this.time.set(time);
	}

	public void setCompleted(boolean completed) {
		this.completed.set(completed);
	}

	public void setIncome(double income) {
		this.income.set(income);
	}

	public void setNote(String note) {
		this.note.set(note);
	}
	
	@Override
	public String toString() {
		return String.format("Visit [FirstName=%s, LastName=%s, Email=%s, Date=%s, Time=%s, Completed=%s, Income=%s, Note=%s]", firstName.get(), lastName.get(), email.get(), date.get(), time.get(), completed.get(), income.get(), note.get());
	}
}
