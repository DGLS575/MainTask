package com.main.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.main.Enums.ViewType;
import com.main.Models.Model;
import com.main.Utilities.InfoUtility;

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
	public TableView tbl_visits;

	@FXML
	public TableColumn col_id;

	@FXML
	public TableColumn col_first_name;

	@FXML
	public TableColumn col_last_name;

	@FXML
	public TableColumn col_email;

	@FXML
	public TableColumn col_date;

	@FXML
	public TableColumn col_time;

	@FXML
	public TableColumn col_completed;

	@FXML
	public TableColumn col_income;

	@FXML
	public TableColumn col_notes;

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

		System.out.println("Filtering by date");
	}
}
