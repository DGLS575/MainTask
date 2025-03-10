package com.main.Controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import com.main.Enums.ViewType;
import com.main.Models.Model;
import com.main.Models.Visit;
import com.main.Utilities.AlertUtility;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditVisitController implements Initializable {

	private Visit visit;

	@FXML
	public Button btn_back;

	@FXML
	public Button btn_update;

	@FXML
	public TextField tf_first_name;

	@FXML
	public TextField tf_last_name;

	@FXML
	public TextField tf_phone;

	@FXML
	public TextField tf_email;

	@FXML
	public DatePicker dp_date;

	@FXML
	public TextField tf_hour;

	@FXML
	public TextField tf_minute;

	@FXML
	public TextArea ta_note;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		btn_back.setOnAction(actionEvent -> backToVisits());
		btn_update.setOnAction(actionEvent -> updateVisit());
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
		populateFieldsWithVisitData();
	}

	private void backToVisits() {
		Model.getInstance().getViewFactory().showView(ViewType.VISITS);
	}

	private void updateVisit() {
		Visit newVisit = buildVisit();
		if (newVisit == null)
			return;

		Model.getInstance().updateVisit(newVisit);
		Model.getInstance().getViewFactory().showView(ViewType.VISITS);
		AlertUtility.displayInformation("Visit updated successfully!");
	}

	private void populateFieldsWithVisitData() {
		if (visit == null)
			return;

		tf_first_name.setText(visit.firstNameProperty().get());
		tf_last_name.setText(visit.lastNameProperty().get());
		tf_phone.setText(visit.phoneProperty().get());
		tf_email.setText(visit.emailProperty().get());
		ta_note.setText(visit.noteProperty().get());

		LocalDateTime date = visit.dateProperty().get();
		dp_date.setValue(date.toLocalDate());
		tf_hour.setText(String.valueOf(date.getHour()));
		tf_minute.setText(String.valueOf(date.getMinute()));
	}
	
	private Visit buildVisit() {
		if (tf_first_name.getText().isEmpty() || tf_last_name.getText().isEmpty() || tf_phone.getText().isEmpty()
				|| tf_email.getText().isEmpty() || dp_date.getValue() == null || tf_hour.getText().isEmpty()
				|| tf_minute.getText().isEmpty()) {
			AlertUtility.displayError("Please fill in all the fields.");
			return null;
		}

		// Date and time
		LocalDateTime date = null;
		try {
			date = LocalDateTime.of(dp_date.getValue().getYear(), dp_date.getValue().getMonthValue(),
					dp_date.getValue().getDayOfMonth(), Integer.parseInt(tf_hour.getText()),
					Integer.parseInt(tf_minute.getText()));
		} catch (Exception e) {
			AlertUtility.displayError("Please enter a valid date and time.");
			return null;
		}

		// Email
		if (!tf_email.getText().contains("@") || !tf_email.getText().contains(".")) {
			AlertUtility.displayError("Please enter a valid email address.");
			return null;
		}

		return new Visit(visit.idProperty().get(), tf_first_name.getText(), tf_last_name.getText(), tf_phone.getText(), tf_email.getText(),
				date, false, 0, ta_note.getText());
	}
}
