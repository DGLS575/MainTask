package com.main.Utilities;

import java.util.logging.Logger;

import com.main.Enums.InfoType;
import com.main.Models.Model;

public class InfoUtility {
	private static final Logger logger = Logger.getLogger(InfoUtility.class.getName());

	/*
	 * Logs an info message and displays it to the user
	 */
	public static void info(String message) {
		logger.info(message);
		Model.getInstance().getViewFactory().showInfo(InfoType.INFO, message);
	}

	/*
	 * Logs an error message and displays it to the user
	 */
	public static void error(String message) {
		logger.severe(message);
		Model.getInstance().getViewFactory().showInfo(InfoType.ERROR, message);
	}

	/*
	 * Logs an exception and displays it to the user
	 */
	public static void error(Exception exception) {
		StringBuilder sb = new StringBuilder();
		sb.append(exception.getMessage());
		sb.append("\n");
		for (StackTraceElement element : exception.getStackTrace()) {
			sb.append(element.toString());
			sb.append("\n");
		}
		
		error(sb.toString());
	}
}
