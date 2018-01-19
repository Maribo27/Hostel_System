package by.tc.task31.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class User implements Serializable {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String lastname;
    private String status;
    private String blockReason;

    private Timestamp blockDate;
    private Timestamp unlockDate;

    private int id;
    private int discount;
    private int balance;

    public User(){
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public Timestamp getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(Timestamp blockDate) {
        this.blockDate = blockDate;
    }

    public Timestamp getUnlockDate() {
        return unlockDate;
    }

    public void setUnlockDate(Timestamp unlockDate) {
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
}
