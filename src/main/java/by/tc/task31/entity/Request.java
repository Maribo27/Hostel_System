package by.tc.task31.entity;

import java.io.Serializable;

public class Request implements Serializable {
    private int number;
    private String username;
    private String type;
    private int cost;
    private int placesNumber;
    private String status;
    private String hostelName;

    public Request(){
    }

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (number != request.number) return false;
        if (cost != request.cost) return false;
        if (placesNumber != request.placesNumber) return false;
        if (username != null ? !username.equals(request.username) : request.username != null) return false;
        if (type != null ? !type.equals(request.type) : request.type != null) return false;
        if (status != null ? !status.equals(request.status) : request.status != null) return false;
        return hostelName != null ? hostelName.equals(request.hostelName) : request.hostelName == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + placesNumber;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (hostelName != null ? hostelName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "number=" + number +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", placesNumber=" + placesNumber +
                ", status='" + status + '\'' +
                ", hostelName='" + hostelName + '\'' +
                '}';
    }
}
