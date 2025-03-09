package com.main.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.main.Enums.ViewType;
import com.main.Models.Model;
import com.main.Models.Visit;
import com.main.Utilities.InfoUtility;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VisitsController implements Initializable {

	//Buttons
	@FXML
	public Button btn_new_visit;

	@FXML
	public Button btn_logout;

	@FXML
	public Button btn_clear_filter;

	//Date filtering
	@FXML
	public DatePicker dp_filter_from;

	@FXML
	public DatePicker dp_filter_to;

	//Table
	@FXML
	public TableView<Visit> tbl_visits;

	@FXML
	public TableColumn<Visit, Integer> col_id;

	@FXML
	public TableColumn<Visit, String> col_first_name;

	@FXML
	public TableColumn<Visit, String> col_last_name;

	@FXML
	public TableColumn<Visit, String> col_phone;

	@FXML
	public TableColumn<Visit, String> col_email;

	@FXML
	public TableColumn<Visit, String> col_date;

	@FXML
	public TableColumn<Visit, String> col_time;

	@FXML
	public TableColumn<Visit, Boolean> col_completed;

	@FXML
	public TableColumn<Visit, Double> col_income;

	@FXML
	public TableColumn<Visit, String> col_notes;

	//Context menu
	@FXML
	public MenuItem mi_complete;

	@FXML
	public MenuItem mi_edit;

	@FXML
	public MenuItem mi_delete;

	//Total income
	@FXML
	public Label lbl_total_income;

	//Username
	@FXML
	public Label lbl_username;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		//Set username
		lbl_username.setText(Model.getInstance().getCurrentUserName());

		//Set button actions
		btn_new_visit.setOnAction(e -> onNewVisit());
		btn_logout.setOnAction(e -> onLogout());

		//Date filter
		btn_clear_filter.setOnAction(e -> onClearFilter());
		dp_filter_from.setOnHidden(e -> onFilter());
		dp_filter_to.setOnHidden(e -> onFilter());

		//InitializeTable
		onFilter();

		//Set context menu actions
		mi_complete.setOnAction(e -> onCompleteVisit());
		mi_edit.setOnAction(e -> onEditVisit());
		mi_delete.setOnAction(e -> onDeleteVisit());

		//Disable context menu items if no visit item is selected
		tbl_visits.setOnContextMenuRequested(e -> {
			if (tbl_visits.getSelectionModel().getSelectedItem() == null) {
				mi_complete.setDisable(true);
				mi_edit.setDisable(true);
				mi_delete.setDisable(true);
			} else {
				mi_complete.setDisable(false);
				mi_edit.setDisable(false);
				mi_delete.setDisable(false);
			}
		});
	}

	/*
	 * Open new visit window
	 */
	public void onNewVisit() {
		Model.getInstance().getViewFactory().showView(ViewType.NEW_VISIT);
	}

	/*
	 * Logout and show the login window
	 */
	public void onLogout() {
		Model.getInstance().setLoginSuccessFlag(false);
		Model.getInstance().getViewFactory().showView(ViewType.LOGIN);
	}

	/*
	 * Clear the date filter
	 */
	public void onClearFilter() {
		dp_filter_from.setValue(null);
		dp_filter_to.setValue(null);
		onFilter();
	}

	/*
	 * Open the complete visit window
	 */
	public void onCompleteVisit() {

	}

	/*
	 * Open the edit visit window
	 */
	public void onEditVisit() {

	}

	/*
	 * Delete the selected visit
	 */
	public void onDeleteVisit() {
		//Delete the selected visit
		Visit selectedVisit = tbl_visits.getSelectionModel().getSelectedItem();
		if (selectedVisit == null)
			return;
		
		Model.getInstance().deleteVisit(selectedVisit.idProperty().get());
		onFilter();
	}

	/*
	 * Filter the visits by date
	 */
	public void onFilter() {
		LocalDate dateFrom = dp_filter_from.getValue() == null ? LocalDate.MIN : dp_filter_from.getValue();
		LocalDate dateTo = dp_filter_to.getValue() == null ? LocalDate.MAX : dp_filter_to.getValue();
		if (dateFrom.isAfter(dateTo)) {
			InfoUtility.info("The start date must be before the end date");
			return;
		}

		ObservableList<Visit> allVisits = Model.getInstance().getAllVisits();
		ArrayList<Visit> filteredVisits = new ArrayList<>();
		for (Visit visit : allVisits) {
			LocalDate visitDate = LocalDate.parse(visit.dateProperty().get());
			if (visitDate.isAfter(dateFrom) && visitDate.isBefore(dateTo))
				filteredVisits.add(visit);
		}
		allVisits.clear();

		//Clear Table
		tbl_visits.getItems().clear();
		updateTotalIncome();

		//Add filtered visits
		if (filteredVisits.isEmpty())
			return;
		tbl_visits.getItems().addAll(filteredVisits);
		updateTotalIncome();
	}

	private void updateTotalIncome() {
		double totalIncome = 0;
		for (Visit visit : tbl_visits.getItems())
			totalIncome += visit.incomeProperty().get();
		
		lbl_total_income.setText(String.valueOf(totalIncome) + " €");
	}
}
