package by.tc.hostel_system.dao;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.tc.hostel_system.controller.constant.ControlConst.BASE_NAME;
import static by.tc.hostel_system.controller.constant.ControlConst.DEFAULT_LANG;
import static by.tc.hostel_system.controller.constant.ControlConst.LANG;

public final class EntityMessageLocale {
	public enum Entity {
		HOSTELS("locale.message.error.not.found.hostels"),
		REQUESTS("locale.message.error.not.found.requests"),
		USERS("locale.message.error.not.found.users"),
		USER("locale.message.error.not.found.user"),
		USER_EXIST("locale.message.error.user.exist");

		private static final String LANG_EN = "en";

		Entity(String res) {
			this.res = res;
		}

		private String res;

		public String getMessage(String lang) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
			return resourceBundle.getString(res);
		}

		public String getMessage() {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(LANG_EN));
			return resourceBundle.getString(res);
		}
	}
}
