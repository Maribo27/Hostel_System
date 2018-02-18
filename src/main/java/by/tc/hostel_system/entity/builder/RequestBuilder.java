package by.tc.hostel_system.entity.builder;

import by.tc.hostel_system.entity.Hostel;
import by.tc.hostel_system.entity.Request;

import java.io.Serializable;
import java.sql.Date;

public class RequestBuilder implements Serializable {
    private static final long serialVersionUID = 8261672134275753823L;
    private String hostelInfo;
    private Hostel.Booking type;
    private Request.Status status;
    private Date date;
    private Date endDate;
    private int id;
    private int userId;
    private int hostelId;
    private int room;
    private int days;
    private int cost;

    public RequestBuilder() {
    }

    /**
     * Adds hostel info to {@link Request}.
     *
     * @param hostelInfo hostel full info
     */
    public void addHostelInfo(String hostelInfo) {
        this.hostelInfo = hostelInfo;
    }

    /**
     * Adds request type to {@link Request}.
     *
     * @param type request type ("booking", "payment")
     */
    public void addType(String type) {
        this.type = Hostel.Booking.valueOf(type.toUpperCase());
    }

    /**
     * Adds request status to {@link Request}.
     *
     * @param status request status
     */
    public void addStatus(String status) {
        this.status = Request.Status.valueOf(status.toUpperCase());
    }

    /**
     * Adds booking date to {@link Request}.
     *
     * @param date date of booking start
     */
    public void addDate(Date date) {
        this.date = date;
    }

    /**
     * Adds end of booking date to {@link Request}.
     *
     * @param endDate date of booking end
     */
    public void addEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Adds request id to {@link Request}.
     *
     * @param id request id
     */
    public void addId(int id) {
        this.id = id;
    }

    /**
     * Adds user id to {@link Request}.
     *
     * @param userId user id
     */
    public void addUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Adds hostel id to {@link Request}.
     *
     * @param hostelId hostel id
     */
    public void addHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    /**
     * Adds number of rooms to {@link Request}.
     *
     * @param room number of rooms
     */
    public void addRoom(int room) {
        this.room = room;
    }

    /**
     * Adds count of days to {@link Request}.
     *
     * @param days number of days
     */
    public void addDays(int days) {
        this.days = days;
    }

    /**
     * Adds full cost to {@link Request}.
     *
     * @param cost full cost of hostel
     */
    public void addCost(int cost) {
        this.cost = cost;
    }

    /**
     * Builds {@link Request}.
     *
     * @return new request
     */
    public Request buildRequest() {
        Request request = new Request();
        request.setHostelInfo(hostelInfo);
        request.setType(type);
        request.setStatus(status);
        request.setDate(date);
        request.setEndDate(endDate);
        request.setId(id);
        request.setUserId(userId);
        request.setHostelId(hostelId);
        request.setRoom(room);
        request.setDays(days);
        request.setCost(cost);
        return request;
    }
}
