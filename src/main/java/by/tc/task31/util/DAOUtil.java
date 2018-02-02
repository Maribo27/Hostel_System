package by.tc.task31.util;

import by.tc.task31.entity.Hostel;
import by.tc.task31.entity.Request;
import by.tc.task31.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOUtil {
	private static final String MD_5 = "MD5";

	public static User createUserFromDB(ResultSet resultSet) throws SQLException {
	    User user = new User();
	    while (resultSet.next()){
	        int column = 1;
	        user.setId(resultSet.getInt(column++));
	        user.setUsername(resultSet.getString(column++));
	        user.setEmail(resultSet.getString(column++));
	        user.setPassword(resultSet.getString(column++));
	        user.setSurname(resultSet.getString(column++));
	        user.setName(resultSet.getString(column++));
	        user.setLastname(resultSet.getString(column++));
	        user.setDiscount(resultSet.getInt(column++));
	        user.setBalance(resultSet.getInt(column++));
		    user.setAccount(resultSet.getString(column++));
	        user.setStatus(resultSet.getString(column++));
	        user.setBlockDate(resultSet.getDate(column++));
	        user.setUnlockDate(resultSet.getDate(column++));
	        user.setBlockReason(resultSet.getString(column));
	    }
	    return user;
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

	public static List<Hostel> createHostels(ResultSet resultSet, int room) throws SQLException {
	    List<Hostel> hostels = new ArrayList<>();
	    while (resultSet.next()){
	        int column = 1;
	        Hostel hostel = new Hostel();

	        hostel.setId(resultSet.getInt(column++));
	        hostel.setName(resultSet.getString(column++));
	        hostel.setCountry(resultSet.getString(column++));
	        hostel.setCity(resultSet.getString(column++));
	        hostel.setAddress(resultSet.getString(column++));
	        if (room == 0) {
		        hostel.setBooking(resultSet.getString(column++));
	        }
	        hostel.setCost(resultSet.getInt(column++));
	        hostel.setEmail(resultSet.getString(column++));
		    hostel.setRoom(resultSet.getInt(column));
		    if (hostel.getRoom() < room){
			    continue;
		    }
	        hostels.add(hostel);
	    }
	    return hostels;
	}

	public static List<Request> createRequests(ResultSet resultSet) throws SQLException {
	    List<Request> requests = new ArrayList<>();
	    while (resultSet.next()){
	        int column = 1;
	        Request req = new Request();
	        req.setId(resultSet.getInt(column++));
	        req.setUserId(resultSet.getInt(column++));
	        req.setHostelId(resultSet.getInt(column++));
	        req.setHostelInfo(resultSet.getString(column++));
	        req.setType(resultSet.getString(column++));
	        req.setRoom(resultSet.getInt(column++));
	        req.setDays(resultSet.getInt(column++));
	        req.setCost(resultSet.getInt(column++));
		    String status = resultSet.getString(column++);
		    req.setStatus(status);
		    String deleted = Request.Status.DELETED.name();
		    if (status.equalsIgnoreCase(deleted)){
		    	continue;
		    }
		    req.setDate(resultSet.getDate(column));
	        requests.add(req);
	    }
	    return requests;
	}

	public static List<User> createUsers(ResultSet resultSet) throws SQLException {
	    List<User> users = new ArrayList<>();
	    while (resultSet.next()){
	        User user = new User();
	        int column = 1;
	        user.setId(resultSet.getInt(column++));
	        user.setUsername(resultSet.getString(column++));
	        user.setEmail(resultSet.getString(column++));
	        user.setSurname(resultSet.getString(column++));
	        user.setName(resultSet.getString(column++));
	        user.setLastname(resultSet.getString(column++));
	        user.setDiscount(resultSet.getInt(column++));
	        user.setBalance(resultSet.getInt(column++));
	        user.setStatus(resultSet.getString(column++));
	        user.setBlockDate(resultSet.getDate(column++));
	        user.setUnlockDate(resultSet.getDate(column++));
	        user.setBlockReason(resultSet.getString(column));
	        users.add(user);
	    }
	    return users;
	}
}
