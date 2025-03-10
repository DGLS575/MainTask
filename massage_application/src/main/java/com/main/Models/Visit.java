package com.main.Models;

import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Visit {
	private IntegerProperty id;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty phone;
	private StringProperty email;
	private SimpleObjectProperty<LocalDateTime> date;
	private BooleanProperty completed;
	private DoubleProperty income;
	private StringProperty note;

	/*
	 * Author constructor
	 */
	public Visit(int id, String firstName, String lastName, String phone, String email, LocalDateTime date, boolean completed, double income, String note) {
		this.id = new SimpleIntegerProperty(id);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.phone = new SimpleStringProperty(phone);
		this.email = new SimpleStringProperty(email);
		this.date = new SimpleObjectProperty<LocalDateTime>(date);
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

	public StringProperty phoneProperty() {
		return phone;
	}

	public StringProperty emailProperty() {
		return email;
	}

	public SimpleObjectProperty<LocalDateTime> dateProperty() {
		return date;
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

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public void setDate(LocalDateTime date) {
		this.date.set(date);
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
		return String.format("Visit [FirstName=%s, LastName=%s, Phone=%s, Email=%s, Date=%s, Completed=%s, Income=%s, Note=%s]", firstName.get(), lastName.get(), phone.get(), email.get(), date, completed.get(), income.get(), note.get());
	}
}
