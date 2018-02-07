package by.tc.hostel_system.entity.builder;

import by.tc.hostel_system.entity.Hostel;

import java.io.Serializable;

public class HostelBuilder implements Serializable {
    private static final long serialVersionUID = -7481188849152613348L;
    private String name;
    private Hostel.Booking booking;
    private Hostel.Address address;
    private String email;
    private int id;
    private int room;
    private int cost;

    public HostelBuilder() {
        address = new Hostel().new Address();
    }

    public void addName(String name) {
        this.name = name;
    }

    public void addBooking(Hostel.Booking booking) {
        this.booking = booking;
    }

    public void addBooking(String booking) {
        this.booking = Hostel.Booking.valueOf(booking.toUpperCase());
    }

    public void addAddress(String city, String country, String address) {
        Hostel.Address hostelAddress = new Hostel().new Address();
        hostelAddress.setCity(city);
        hostelAddress.setCountry(country);
        hostelAddress.setAddress(address);
        this.address = hostelAddress;
    }

    public void addEmail(String email) {
        this.email = email;
    }

    public void addId(int id) {
        this.id = id;
    }

    public void addRoom(int room) {
        this.room = room;
    }

    public void addCost(int cost) {
        this.cost = cost;
    }

    public Hostel buildHostel() {
        Hostel hostel = new Hostel();
        hostel.setName(name);
        hostel.setBooking(booking);
        hostel.setAddress(address);
        hostel.setEmail(email);
        hostel.setId(id);
        hostel.setRoom(room);
        hostel.setCost(cost);
        return hostel;
    }
}