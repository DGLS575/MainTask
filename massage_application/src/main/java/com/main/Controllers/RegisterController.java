package com.main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.main.Enums.ViewType;
import com.main.Models.DatabaseDriver;
import com.main.Models.Model;
import com.main.Utilities.AlertUtility;
import com.main.Utilities.UserUtility;

public class RegisterController implements Initializable {
	
	private static final Logger logger = Logger.getLogger(DatabaseDriver.class.getName());


	@FXML
	public TextField txt_username_field;

	@FXML
	public PasswordField passwd;

	@FXML
	public PasswordField repeat_passwd;

	@FXML
	public Button btn_register;

	@FXML
	public Button btn_back;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_register.setOnAction(actionEvent -> onRegister());
		btn_back.setOnAction(actionEvent -> backToLogin());

		logger.info("RegisterController initialized");
	}

	public void onRegister() {
		if (txt_username_field.getText().isEmpty() || passwd.getText().isEmpty() || repeat_passwd.getText().isEmpty()) {
			AlertUtility.displayError("Please fill in all fields!");
			return;
		}

		//Check if passwords match
		if (Model.getInstance().userExists(txt_username_field.getText())) {
			AlertUtility.displayError("User already exists!");
			clearFields();
			return;
		}

		if (!UserUtility.doPasswordsMatch(passwd.getText(), repeat_passwd.getText())) {
			AlertUtility.displayError("Passwords do not match!");
			return;
		}

		Model.getInstance().createUser(txt_username_field.getText(), passwd.getText());

		AlertUtility.displayInformation("User created successfully!");

		backToLogin();
	}

	public void backToLogin() {
		Model.getInstance().getViewFactory().showView(ViewType.LOGIN);
	}

	/*
	 * Clear form fields
	 */
	public void clearFields() {
		txt_username_field.clear();
		passwd.clear();
		repeat_passwd.clear();
	}
}
