package by.tc.task31.entity;

import java.io.Serializable;
import java.util.Objects;

public class Hostel implements Serializable {
    private String name;
    private String city;
    private String country;
    private String address;
    private String booking;
    private String email;
    private int id;
    private int room;
    private int cost;

    public Hostel(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
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
        Hostel hostel = (Hostel) o;
        return id == hostel.id &&
                room == hostel.room &&
                cost == hostel.cost &&
                Objects.equals(name, hostel.name) &&
                Objects.equals(city, hostel.city) &&
                Objects.equals(country, hostel.country) &&
                Objects.equals(address, hostel.address) &&
                Objects.equals(booking, hostel.booking) &&
                Objects.equals(email, hostel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, country, address, booking, email, id, room, cost);
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", booking='" + booking + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", room=" + room +
                ", cost=" + cost +
                '}';
    }
}
