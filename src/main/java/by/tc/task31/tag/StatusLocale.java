package by.tc.task31.tag;

import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class StatusLocale {
	private static final String CANNOT_FIND_CLASS_FIELD = "Cannot find class field";
	private static final String CANNOT_ACCESS_CLASS_FIELD = "Cannot access class field";
	private static Map<User.Status, String> userStatus = new HashMap<>();
	private static Map<Request.Status, String> requestStatus = new HashMap<>();
	private static Map<Hostel.Booking, String> hostelBooking = new HashMap<>();

	private final static Logger logger = Logger.getLogger(StatusLocale.class);

	public static final String USER_ADMIN = "locale.info.users.admin";
	public static final String USER_USER = "locale.info.users.user";
	public static final String USER_BANNED = "locale.info.users.banned";

	public static final String REQUEST_PROCESSING = "locale.info.requests.process";
	public static final String REQUEST_APPROVED = "locale.info.requests.approved";
	public static final String REQUEST_DENIED = "locale.info.requests.denied";
	public static final String REQUEST_DELETED = "locale.info.requests.deleted";

	public static final String HOSTEL_BOOKING = "locale.info.hostel.booking";
	public static final String HOSTEL_PAYMENT = "locale.info.hostel.payment";

	public static String getUserStatus(User.Status status) {
		return userStatus.get(status);
	}

	public static String getRequestStatus(Request.Status status) {
		return requestStatus.get(status);
	}

	public static String getBookingType(Hostel.Booking booking) {
		return hostelBooking.get(booking);
	}

	static {
		try {

			for (User.Status status : User.Status.values()){
				String fieldName = "USER_" + status.name();
				Field field = StatusLocale.class.getField(fieldName);
				String value = (String) field.get(null);
				userStatus.put(status, value);
			}

			for (Request.Status status : Request.Status.values()){
				String fieldName = "REQUEST_" + status.name();
				Field field = StatusLocale.class.getField(fieldName);
				String value = (String) field.get(null);
				requestStatus.put(status, value);
			}

			for (Hostel.Booking booking : Hostel.Booking.values()){
				String fieldName = "HOSTEL_" + booking.name();
				Field field = StatusLocale.class.getField(fieldName);
				String value = (String) field.get(null);
				hostelBooking.put(booking, value);
			}
		} catch (NoSuchFieldException e) {
			logger.error(CANNOT_FIND_CLASS_FIELD, e);
			throw new EntityTagException(CANNOT_FIND_CLASS_FIELD, e);
		} catch (IllegalAccessException e) {
			logger.error(CANNOT_ACCESS_CLASS_FIELD, e);
			throw new EntityTagException(CANNOT_ACCESS_CLASS_FIELD, e);
		}
	}

	private StatusLocale() {}
}
