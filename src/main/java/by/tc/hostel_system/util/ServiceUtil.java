package by.tc.hostel_system.util;

import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.builder.RequestBuilder;

import java.sql.Date;

import static by.tc.hostel_system.util.ControllerUtil.getEndDate;

public class ServiceUtil {
	/**
	 * Creates {@link Request} from database result set.
	 *
	 * @param userId    user id
	 * @param hostelId  hostel id
	 * @param type      request type ("payment", "booking")
	 * @param rooms     number of rooms to book
	 * @param days      number of days
	 * @param discount  user discount
	 * @param date      date of booking start
	 * @param cost      cost of request
	 *
	 * @return new request
	 */
	public static Request createRequest(int userId, int hostelId, String type, int rooms, int days, int discount, Date date, int cost) {
		Date endDate = getEndDate(days, date);
		int price = rooms * days * cost - rooms * days * cost * discount / 100;
		RequestBuilder builder = new RequestBuilder();
		builder.addUserId(userId);
		builder.addHostelId(hostelId);
		builder.addType(type);
		builder.addRoom(rooms);
		builder.addDays(days);
		builder.addCost(price);
		builder.addDate(date);
		builder.addEndDate(endDate);
		return builder.buildRequest();
	}
}
