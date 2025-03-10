package com.main.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.main.Enums.ViewType;
import com.main.Models.DatabaseDriver;
import com.main.Models.Model;
import com.main.Utilities.AlertUtility;

public class LoginController implements Initializable {
	
	private static final Logger logger = Logger.getLogger(DatabaseDriver.class.getName());


	@FXML
	public TextField txt_username_field;

	@FXML
	public PasswordField passwd;

	@FXML
	public Hyperlink hyp_register_link;

	@FXML
	public Button btn_login;

	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		hyp_register_link.setOnAction(actionEvent -> onRegister());
		btn_login.setOnAction(actionEvent -> onLogin());

		logger.info("LoginController initialized");
	}

	/*
	 * Handle login action
	 */
	public void onLogin() {
		if (txt_username_field.getText().isEmpty() || passwd.getText().isEmpty()) {
			AlertUtility.displayError("Please fill in all fields!");
			return;
		}

		//Check credentials
		Model.getInstance().checkCredentials(txt_username_field.getText(), passwd.getText());

		//If login is successful, open dashboard
		if (Model.getInstance().getLoginSuccessFlag()) {
			VisitsController controller = Model.getInstance().getViewFactory().showView(ViewType.VISITS).getController();
			controller.showTodaysVisits();
		} else {
			txt_username_field.setText(null);
			passwd.setText(null);
			AlertUtility.displayError("Invalid credentials");
		}
	}

	/*
	 * Handle register action
	 */
	public void onRegister() {
		Model.getInstance().getViewFactory().showView(ViewType.REGISTER);
	}	
}
