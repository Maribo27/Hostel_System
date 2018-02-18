package by.tc.hostel_system.dao;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.tc.hostel_system.controller.constant.ControlConst.BASE_NAME;
public enum EntityMessageLocale {
	HOSTELS("locale.message.error.not.found.hostels"),
	REQUESTS("locale.message.error.not.found.requests"),
	USERS("locale.message.error.not.found.users"),
	USER("locale.message.error.not.found.user"),
	USER_EXIST("locale.message.error.user.exist");

	private static final String LANG_EN = "en";

	EntityMessageLocale(String res) {
		this.res = res;
	}

	private String res;

	/**
	 * Returns message on concrete language.
	 *
	 * @param lang current language
	 *
	 * @return message
	 */
	public String getMessage(String lang) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
		return resourceBundle.getString(res);
	}

	/**
	 * Returns message on default (English) language.
	 *
	 * @return message
	 */
	public String getMessage() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(LANG_EN));
		return resourceBundle.getString(res);
	}
}
