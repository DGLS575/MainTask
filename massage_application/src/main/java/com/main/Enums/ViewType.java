package com.main.Enums;

public enum ViewType {
	NONE,
	INFO,
	CRITICAL_ERROR,
	LOGIN,
	REGISTER,
	VISITS,
	NEW_VISIT,
	EDIT_VISIT,
	COMPLETE_VISIT; //Allow the user to enter income from the visit

	/*
	 * Returns the name of the specified view type
	 */
	public static String getName(ViewType viewType) {
		switch (viewType) {
			case INFO:
				return "info";
			case LOGIN:
				return "login";
			case REGISTER:
				return "register";
			case VISITS:
				return "visits";
			case NEW_VISIT:
				return "new_visit";
			case EDIT_VISIT:
				return "edit_visit";
			case COMPLETE_VISIT:
				return "complete_visit";
			default:
				return "none";
		}
	}
	
	/*
	 * Returns whether the title should be visible for the specified view type
	 */
	public static boolean getTitleVisible(ViewType viewType) {
		switch (viewType) {
			case LOGIN:
			case REGISTER:
				return true;
			default:
				return false;
		}
	}
}
