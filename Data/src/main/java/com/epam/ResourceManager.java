package com.epam;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
	private static Locale locale = Locale.ENGLISH;
	private static ResourceBundle rb = ResourceBundle.getBundle("properties.SQL_Queries", locale);

	public static String getRegExp(String key) {
		return rb.getString(key);
	}
}
