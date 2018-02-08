package by.tc.hostel_system.controller.constant;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ControlConst {
    public static final String DEFAULT_LANG = "ru";

    public static final String COMMAND = "command";
    public static final String USER = "user";
    public static final String HOSTELS = "hostels";
    public static final String REQUESTS = "requests";
    public static final String LANG = "lang";
    public static final String NEXT_COMMAND = "next-command";
    public static final String REQUEST = "request";
    public static final String PAGE = "page";
    public static final String NUMBER = "number";

    public static final String BASE_NAME = "locale.locale";

    public enum Message {
        PASSWORD_INCORRECT("locale.message.error.password.incorrect"),
        PASSWORD_CHANGED("locale.message.info.password.changed"),
        DATA_CHANGED("locale.message.info.data.changed");

        Message(String res) {
            this.res = res;
        }

        private String res;

        public String getMessage(String lang) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, Locale.forLanguageTag(lang));
            return resourceBundle.getString(res);
        }
    }

    private ControlConst(){}
}