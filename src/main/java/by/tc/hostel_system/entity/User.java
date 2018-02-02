package by.tc.hostel_system.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 2299823395903644174L;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String lastname;
    private Status status;
    private String account;
    private String blockReason;

    private Date blockDate;
    private Date unlockDate;

    private int id;
    private int discount;
    private int balance;

    public User(){
        this.status = Status.USER;
        this.discount = 0;
        this.balance = 0;
    }

    public enum Status {
        BANNED, USER, ADMIN
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public Date getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(Date blockDate) {
        this.blockDate = blockDate;
    }

    public Date getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(Date unlockDate) {
        this.unlockDate = unlockDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                discount == user.discount &&
                balance == user.balance &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(lastname, user.lastname) &&
                status == user.status &&
                Objects.equals(account, user.account) &&
                Objects.equals(blockReason, user.blockReason) &&
                Objects.equals(blockDate, user.blockDate) &&
                Objects.equals(unlockDate, user.unlockDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, name, surname, lastname, status, account, blockReason, blockDate, unlockDate, id, discount, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", status=" + status +
                ", account=" + account +
                ", blockReason='" + blockReason + '\'' +
                ", blockDate=" + blockDate +
                ", unlockDate=" + unlockDate +
                ", id=" + id +
                ", discount=" + discount +
                ", balance=" + balance +
                '}';
    }
}
