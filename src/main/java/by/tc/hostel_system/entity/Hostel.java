package by.tc.hostel_system.entity;

import java.io.Serializable;
import java.util.Objects;

public class Hostel implements Serializable {
    private static final long serialVersionUID = -7481188849152613348L;
    private String name;
    private Booking booking;
    private FullAddress fullAddress;
    private String email;
    private int id;
    private int room;
    private int cost;

    public Hostel(){
    }

    public enum Booking {
        BOOKING, PAYMENT
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = Booking.valueOf(booking.toUpperCase());
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public FullAddress getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(FullAddress fullAddress) {
        this.fullAddress = fullAddress;
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
                booking == hostel.booking &&
                Objects.equals(fullAddress, hostel.fullAddress) &&
                Objects.equals(email, hostel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, booking, fullAddress, email, id, room, cost);
    }

    @Override
    public String toString() {
        return "Hostel : " +
                "id=" + id +
                ", name=\"" + name + '\"' +
                ", fullAddress=" + fullAddress +
                ", booking=" + booking +
                ", email=" + email +
                ", rooms=" + room +
                ", cost=" + cost;
    }

    public class FullAddress {
        private String city;
        private String country;
        private String address;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FullAddress fullAddress1 = (FullAddress) o;
            return Objects.equals(city, fullAddress1.city) &&
                    Objects.equals(country, fullAddress1.country) &&
                    Objects.equals(address, fullAddress1.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city, country, address);
        }

        @Override
        public String toString() {
            return "FullAddress : " +
                    "country=" + country +
                    ", city=" + city +
                    ", street=" + address;
        }
    }
}
