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

    public void addHostelInfo(String hostelInfo) {
        this.hostelInfo = hostelInfo;
    }

    public void addType(String type) {
        this.type = Hostel.Booking.valueOf(type.toUpperCase());
    }

    public void addStatus(String status) {
        this.status = Request.Status.valueOf(status.toUpperCase());
    }

    public void addDate(Date date) {
        this.date = date;
    }

    public void addEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addId(int id) {
        this.id = id;
    }

    public void addUserId(int userId) {
        this.userId = userId;
    }

    public void addHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public void addRoom(int room) {
        this.room = room;
    }

    public void addDays(int days) {
        this.days = days;
    }

    public void addCost(int cost) {
        this.cost = cost;
    }

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
