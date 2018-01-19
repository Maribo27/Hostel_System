package by.tc.task31.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Request implements Serializable {
    private String hostelInfo;
    private String type;
    private String status;
    private Date date;
    private int id;
    private int userId;
    private int hostelId;
    private int room;
    private int days;
    private int cost;

    public Request(){
    }

    public String getHostelInfo() {
        return hostelInfo;
    }

    public void setHostelInfo(String hostelInfo) {
        this.hostelInfo = hostelInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
