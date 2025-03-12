package com.main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import com.main.Enums.ViewType;
//import com.main.Enums.ViewType;
import com.main.Models.Model;
//import com.main.Utilities.AlertUtility;
import com.main.Utilities.AlertUtility;

/**
 * JavaFX App
 */
public class App extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		//Model.getInstance().getViewFactory().quickLogin("admin", "123");

		if (Model.getInstance().hasRegisteredUsers()) {
			Model.getInstance().getViewFactory().showView(ViewType.LOGIN);
		} else {
			AlertUtility.displayError("No registered users found! Create a new user.");
			Model.getInstance().getViewFactory().showView(ViewType.REGISTER);
		}
	}

	public static void main(String[] args) {
		launch();
	}
}