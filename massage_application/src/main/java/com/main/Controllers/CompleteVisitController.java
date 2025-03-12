package com.main.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.main.Enums.ViewType;
import com.main.Models.Model;
import com.main.Models.Visit;
import com.main.Utilities.AlertUtility;
import com.main.Utilities.InfoUtility;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CompleteVisitController implements Initializable {

	private Visit visit;

	@FXML
	public Button btn_back;

	@FXML
	public Button btn_complete;

	@FXML
	public TextField tf_income;

	@FXML
	public TextArea ta_note;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_back.setOnAction(actionEvent -> backToVisits());
		btn_complete.setOnAction(actionEvent -> completeVisit());
	}

	/*
	 * Sets the visit to be completed
	 */
	public void setVisit(Visit visit) {
		this.visit = visit;
		populateFieldsWithVisitData();
	}

	/*
	 * Navigate back to the visits view
	 */
	private void backToVisits() {
		Model.getInstance().getViewFactory().showView(ViewType.VISITS);
	}

	/*
	 * Complete the visit
	 */
	private void completeVisit() {
		Visit newVisit = buildVisit();
		if (newVisit == null)
			return;

		Model.getInstance().updateVisit(newVisit);
		Model.getInstance().getViewFactory().showView(ViewType.VISITS);
		AlertUtility.displayInformation("Visit completed successfully!");
	}

	/*
	 * Populate the fields with the visit data
	 */
	private void populateFieldsWithVisitData() {
		if (visit == null)
			return;

		ta_note.setText(visit.noteProperty().get());
	}
	
	/*
	 * Build the visit object
	 */
	private Visit buildVisit() {
		if (visit == null)
		{
			InfoUtility.error("Visit is null.");
			return null;
		}

		//Income
		if (tf_income.getText().isEmpty()) {
			AlertUtility.displayError("Please fill out the income field.");
			return null;
		}

		double income = 0;
		try {
			income = Double.parseDouble(tf_income.getText());
		} catch (Exception ex) {
			AlertUtility.displayError("Please enter a valid income.");
			return null;
		}

		if (income <= 0) {
			AlertUtility.displayError("Income must be greater than zero.");
			return null;
		}

		//Visit
		visit.setIncome(income);
		visit.setCompleted(true);
		visit.setNote(ta_note.getText());
		return visit;
	}
}
