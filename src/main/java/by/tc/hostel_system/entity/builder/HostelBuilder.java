package by.tc.hostel_system.entity.builder;

import by.tc.hostel_system.entity.Hostel;

import java.io.Serializable;

public class HostelBuilder implements Serializable {
    private static final long serialVersionUID = -7481188849152613348L;
    private String name;
    private Hostel.Booking booking;
    private Hostel.FullAddress fullAddress;
    private String email;
    private int id;
    private int room;
    private int cost;

    public HostelBuilder() {
        fullAddress = new Hostel().new FullAddress();
    }

    /**
     * Adds name to {@link Hostel}.
     *
     * @param name hostel name
     */
    public void addName(String name) {
        this.name = name;
    }

    /**
     * Adds type of booking to {@link Hostel}.
     *
     * @param booking type of booking ("booking", "payment")
     */
    public void addBooking(Hostel.Booking booking) {
        this.booking = booking;
    }

    /**
     * Adds type of booking to {@link Hostel}.
     *
     * @param booking string type of booking ("booking", "payment")
     */
    public void addBooking(String booking) {
        this.booking = Hostel.Booking.valueOf(booking.toUpperCase());
    }

    /**
     * Adds fullAddress to {@link Hostel}.
     *
     * @param city hostel city
     * @param country hostel country
     * @param address hostel address
     */
    public void addAddress(String city, String country, String address) {
        Hostel.FullAddress hostelFullAddress = new Hostel().new FullAddress();
        hostelFullAddress.setCity(city);
        hostelFullAddress.setCountry(country);
        hostelFullAddress.setAddress(address);
        this.fullAddress = hostelFullAddress;
    }

    /**
     * Adds email to {@link Hostel}.
     *
     * @param email hostel email
     */
    public void addEmail(String email) {
        this.email = email;
    }

    /**
     * Adds id to {@link Hostel}.
     *
     * @param id hostel id
     */
    public void addId(int id) {
        this.id = id;
    }

    /**
     * Adds count of rooms to {@link Hostel}.
     *
     * @param room count of rooms
     */
    public void addRoom(int room) {
        this.room = room;
    }

    /**
     * Adds room cost to {@link Hostel}.
     *
     * @param cost room cost
     */
    public void addCost(int cost) {
        this.cost = cost;
    }

    /**
     * Builds {@link Hostel}.
     *
     * @return new hostel
     */
    public Hostel buildHostel() {
        Hostel hostel = new Hostel();
        hostel.setName(name);
        hostel.setBooking(booking);
        hostel.setFullAddress(fullAddress);
        hostel.setEmail(email);
        hostel.setId(id);
        hostel.setRoom(room);
        hostel.setCost(cost);
        return hostel;
    }
}