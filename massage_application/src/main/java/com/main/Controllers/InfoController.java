package com.main.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.main.Enums.InfoType;
import com.main.Models.Model;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoController implements Initializable {

	@FXML
	public Button btn_back;

	@FXML
	public Label lbl_info;

	@FXML
	public Label lbl_title;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_back.setOnAction(actionEvent -> backToPreviousView());
	}

	/*
	 * Sets the info text and type along with the corresponding text color
	 */
	public void setInfo(InfoType infoType, String info) {
		switch (infoType) {
			case INFO:
				lbl_title.setText("Info");
				lbl_title.setStyle("-fx-text-fill: black;");
				lbl_info.setStyle("-fx-text-fill: black;");
				btn_back.setVisible(true);
				break;
			case ERROR:
				lbl_title.setText("Error");
				lbl_title.setStyle("-fx-text-fill: red;");
				lbl_info.setStyle("-fx-text-fill: red;");
				btn_back.setVisible(false);
				break;
			default:
				break;
		}

		lbl_info.setText(info);
	}

	/*
	 * Navigates back to the previous view
	 */
	private void backToPreviousView() {
		Model.getInstance().getViewFactory().showPreviousView();
	}
}
