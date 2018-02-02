package by.tc.hostel_system.util;

import by.tc.hostel_system.entity.Request;

import java.sql.Date;

import static by.tc.hostel_system.util.ControllerUtil.getEndDate;

public class ServiceUtil {
	public static Request createRequest(int userId, int hostelId, String type, int rooms, int days, int discount, Date date, int cost) {
		Date endDate = getEndDate(days, date);
		int price = rooms * days * cost - rooms * days * cost * discount / 100;
		Request userRequest = new Request();
		userRequest.setUserId(userId);
		userRequest.setHostelId(hostelId);
		userRequest.setType(type);
		userRequest.setRoom(rooms);
		userRequest.setDays(days);
		userRequest.setCost(price);
		userRequest.setDate(date);
		userRequest.setEndDate(endDate);
		return userRequest;
	}
}
