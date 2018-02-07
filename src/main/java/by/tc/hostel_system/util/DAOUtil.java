package by.tc.hostel_system.util;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.Request;
import by.tc.hostel_system.entity.User;
import by.tc.hostel_system.entity.builder.HostelBuilder;
import by.tc.hostel_system.entity.builder.RequestBuilder;
import by.tc.hostel_system.entity.builder.UserBuilder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOUtil {
	private static final String MD_5 = "MD5";

	public static User createUserFromDB(ResultSet resultSet) throws SQLException {
	    UserBuilder builder = new UserBuilder();
	    while (resultSet.next()){
	        int column = 1;
	        builder.addId(resultSet.getInt(column++));
	        String username = resultSet.getString(column++);
	        String email = resultSet.getString(column++);
	        String surname = resultSet.getString(column++);
	        String name = resultSet.getString(column++);
	        String lastname = resultSet.getString(column++);
	        builder.addPersonalInfo(username, email, "", name, surname, lastname);
	        builder.addDiscount(resultSet.getInt(column++));
	        builder.addBalance(resultSet.getInt(column++));
		    builder.addAccount(resultSet.getString(column++));
	        builder.addStatus(resultSet.getString(column++));
	        Date blockDate = resultSet.getDate(column++);
	        String blockReason = resultSet.getString(column);
	        builder.addBlockInfo(blockReason, blockDate);
	    }
	    return builder.buildUser();
	}

	public static String createPassword(String password) throws NoSuchAlgorithmException {
	    StringBuilder code = new StringBuilder();
	    MessageDigest messageDigest;
	    messageDigest = MessageDigest.getInstance(MD_5);
	    byte bytes[] = password.getBytes();
	    byte digest[] = messageDigest.digest(bytes);
	    for (byte aDigest : digest) {
	        code.append(Integer.toHexString(0x0100 + (aDigest & 0x00FF)).substring(1));
	    }

	    password = code.toString();
	    return password;
	}

	public static Map<Integer, String> translateToMap(ResultSet resultSet) throws SQLException {
		Map<Integer, String> cities = new HashMap<>();
	    while (resultSet.next()){
	        int column = 1;
	        Integer number = resultSet.getInt(column++);
	        String reason = resultSet.getString(column);
	        cities.put(number, reason);
	    }
	    return cities;
	}

	public static List<Hostel> createHostels(ResultSet resultSet, Hostel.Booking type, int room) throws SQLException {
	    List<Hostel> hostels = new ArrayList<>();
	    while (resultSet.next()){
	        int column = 1;
	        HostelBuilder builder = new HostelBuilder();

	        builder.addId(resultSet.getInt(column++));
	        builder.addName(resultSet.getString(column++));

		    String country = resultSet.getString(column++);
		    String city = resultSet.getString(column++);
		    String address = resultSet.getString(column++);
		    builder.addAddress(city, country, address);
		    Hostel.Booking currentType = Hostel.Booking.valueOf(resultSet.getString(column++).toUpperCase());
		    final boolean rightType = room == 0 || type == Hostel.Booking.PAYMENT || currentType == type;
		    if (rightType) {
		    	builder.addBooking(currentType);
	        } else {
		    	continue;
		    }
	        builder.addCost(resultSet.getInt(column++));
	        builder.addEmail(resultSet.getString(column++));
		    final int roomNumber = resultSet.getInt(column);
		    if (roomNumber < room){
			    continue;
		    }
		    builder.addRoom(roomNumber);
		    hostels.add(builder.buildHostel());
	    }
	    return hostels;
	}

	public static List<Request> createRequests(ResultSet resultSet) throws SQLException {
	    List<Request> requests = new ArrayList<>();
	    while (resultSet.next()){
	        int column = 1;
	        RequestBuilder builder = new RequestBuilder();
	        builder.addId(resultSet.getInt(column++));
	        builder.addUserId(resultSet.getInt(column++));
	        builder.addHostelId(resultSet.getInt(column++));
	        builder.addHostelInfo(resultSet.getString(column++));
	        builder.addType(resultSet.getString(column++));
	        builder.addRoom(resultSet.getInt(column++));
	        builder.addDays(resultSet.getInt(column++));
	        builder.addCost(resultSet.getInt(column++));
		    String status = resultSet.getString(column++);
		    builder.addStatus(status);
		    String deleted = Request.Status.DELETED.name();
		    if (status.equalsIgnoreCase(deleted)){
		    	continue;
		    }
		    builder.addDate(resultSet.getDate(column));
	        requests.add(builder.buildRequest());
	    }
	    return requests;
	}

	public static List<User> createUsers(ResultSet resultSet) throws SQLException {
	    List<User> users = new ArrayList<>();
		while (resultSet.next()){
		    UserBuilder builder = new UserBuilder();
	        int column = 1;
	        int tempId = resultSet.getInt(column++);
	        if (tempId == 1) {
	        	continue;
	        }
		    builder.addId(tempId);
		    String username = resultSet.getString(column++);
		    String email = resultSet.getString(column++);
		    String surname = resultSet.getString(column++);
		    String name = resultSet.getString(column++);
		    String lastname = resultSet.getString(column++);
		    builder.addPersonalInfo(username, email, "", name, surname, lastname);
		    builder.addDiscount(resultSet.getInt(column++));
		    builder.addBalance(resultSet.getInt(column++));
		    builder.addStatus(resultSet.getString(column++));
		    Date blockDate = resultSet.getDate(column++);
		    String blockReason = resultSet.getString(column++);
		    builder.addBlockInfo(blockReason, blockDate);
		    builder.addRequests(resultSet.getInt(column));
		    users.add(builder.buildUser());
	    }
	    return users;
	}
}
