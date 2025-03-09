package com.main.Views;

import com.main.Controllers.InfoController;
import com.main.Controllers.LoginController;
import com.main.Enums.InfoType;
import com.main.Enums.ViewType;
import com.main.Utilities.InfoUtility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewFactory {

	private ViewType viewType;
	private BorderPane mainView;

	private AnchorPane currentView;
	private boolean titleVisible;
	private ViewType previousViewType;

	public ViewFactory() {
		this.viewType = null;
		this.mainView = getMainView();
		createStage(mainView);
	}

	/*
	 * Getter for the currently open view type
	 */
	public ViewType getViewType() {
		return viewType;
	}

	/*
	 * Sets the current view based on the specified view type
	 */
	public FXMLLoader showView(ViewType viewType) {
		if (viewType == null)
			return null;

		this.previousViewType = this.viewType;
		this.viewType = viewType;

		//Set the title visibility
		if (ViewType.getTitleVisible(viewType))
			showTitle();
		else
			hideTitle();

		//Dispose of the currentView if it exists
		if (currentView != null)
			mainView.getChildren().remove(currentView);

		String viewPath = "/Fxml/" + ViewType.getName(viewType) + ".fxml";

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
			AnchorPane pane = loader.load();
			currentView = pane;
			mainView.setCenter(currentView);
			return loader;
		} catch (Exception ex) {
			InfoUtility.error(ex);
		}
		return null;
	}

	/*
	 * Shows the previously open view
	 */
	public void showPreviousView() {
		showView(previousViewType);
	}

	/*
	 * Shows the title VBox at the top of the main view
	 */
	public void showTitle() {
		if (titleVisible)
			return;

		String viewPath = "/Fxml/title.fxml";
		try {
			VBox pane = new FXMLLoader(getClass().getResource(viewPath)).load();
			mainView.setTop(pane);
			titleVisible = true;
		} catch (Exception ex) {
			InfoUtility.error(ex);
		}
	}

	/*
	 * Hides the title VBox at the top of the main view
	 */
	public void hideTitle() {
		if (!titleVisible)
			return;

		mainView.setTop(null);
		titleVisible = false;
	}

	/*
	 * Shows an info view with the specified text
	 * @param infoType the type of info to display
	 * @param text the text to display
	 */
	public void showInfo(InfoType infoType, String text)
	{
		try {
			InfoController controller = showView(ViewType.INFO).getController();
			controller.setInfo(infoType, text);
		} catch (Exception ex) {
			InfoUtility.error(ex);
		}
	}

	/*
	 * Loads the login view and triggers the login with the specified credentials
	 * @param username the username
	 * @param password the password
	 */
	public void quickLogin(String username, String password) {
		try {
			LoginController controller = showView(ViewType.LOGIN).getController();
			controller.txt_username_field.setText(username);
			controller.passwd.setText(password);
			controller.onLogin();
		} catch (Exception ex) {
			InfoUtility.error(ex);
		}
	}

	/*
	 * Gets a new instance (or an existing instance) of the main view BorderPane and returns it
	 */
	private BorderPane getMainView() {
		if (mainView == null) {
			try {
				mainView = new FXMLLoader(getClass().getResource("/Fxml/main.fxml")).load();
			} catch (Exception ex) {
				InfoUtility.error(ex);
			}
		}
		return mainView;
	}

	/*
	 * Create and display new stage
	 * @param loader the FXML loader instance. Loads the FXML file and creates a scene
	 */
	private Stage createStage(Parent parent) {
		Scene scene = null;
		try {
			scene = new Scene(parent);
		} catch (Exception ex) {
			InfoUtility.error(ex);
		}

		Stage stage = new Stage();
		stage.setResizable(true);
		stage.setMinWidth(1100);
		stage.setMinHeight(800);
		stage.setScene(scene);
		stage.setTitle("Massage App");
		stage.show();
		return stage;
	}
}
