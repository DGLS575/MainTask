module com.main {
	requires transitive javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires transitive java.sql;
	requires java.logging;

	requires transitive javafx.controls;
	requires transitive javafx.fxml;

	opens com.main to javafx.fxml;

	exports com.main;
	exports com.main.Controllers;
	exports com.main.Models;
	exports com.main.Views;
	exports com.main.DAO;
	exports com.main.Enums;
}
