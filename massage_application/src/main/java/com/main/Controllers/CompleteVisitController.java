package com.main.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.main.Enums.ViewType;
import com.main.Models.Model;
import com.main.Models.Visit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class CompleteVisitController implements Initializable {

	private Visit visit;

	@FXML
	public Button btn_back;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_back.setOnAction(actionEvent -> backToVisits());
	}
	
	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	private void backToVisits() {		
		Model.getInstance().getViewFactory().showView(ViewType.VISITS);
	}
}
