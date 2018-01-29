package by.tc.task31.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Request implements Serializable {
    private static final long serialVersionUID = 8261672134275753823L;
    private String hostelInfo;
    private String type;
    private Status status;
    private Date date;
    private Date endDate;
    private int id;
    private int userId;
    private int hostelId;
    private int room;
    private int days;
    private int cost;

    public Request(){
    }

    public enum Status {
        PROCESSING, APPROVED, DENIED, DELETED
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                userId == request.userId &&
                hostelId == request.hostelId &&
                room == request.room &&
                days == request.days &&
                cost == request.cost &&
                Objects.equals(hostelInfo, request.hostelInfo) &&
                Objects.equals(type, request.type) &&
                status == request.status &&
                Objects.equals(date, request.date) &&
                Objects.equals(endDate, request.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostelInfo, type, status, date, endDate, id, userId, hostelId, room, days, cost);
    }

    @Override
    public String toString() {
        return "Request{" +
                "hostelInfo='" + hostelInfo + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", endDate=" + endDate +
                ", id=" + id +
                ", userId=" + userId +
                ", hostelId=" + hostelId +
                ", room=" + room +
                ", days=" + days +
                ", cost=" + cost +
                '}';
    }
}
