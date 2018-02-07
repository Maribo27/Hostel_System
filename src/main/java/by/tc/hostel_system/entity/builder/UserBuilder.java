package by.tc.hostel_system.entity.builder;

import by.tc.hostel_system.entity.User;

import java.io.Serializable;
import java.sql.Date;

public class UserBuilder implements Serializable {
    private static final long serialVersionUID = 2299823395903644174L;
    private User.PersonalInfo personalInfo;
    private User.Status status;
    private String account;
    private User.BlockInfo blockInfo;

    private int id;
    private int discount;
    private int balance;
    private int requests;

    public UserBuilder(){
        personalInfo = new User().new PersonalInfo();
        blockInfo = new User().new BlockInfo();
        status = User.Status.USER;
        discount = 0;
        balance = 0;
    }

    public void addPersonalInfo(String username, String email, String password, String name, String surname, String lastname) {
        User.PersonalInfo personalInfo = new User().new PersonalInfo();
        personalInfo.setUsername(username);
        personalInfo.setEmail(email);
        personalInfo.setPassword(password);
        personalInfo.setName(name);
        personalInfo.setSurname(surname);
        personalInfo.setLastname(lastname);
        this.personalInfo = personalInfo;
    }

    public void addBlockInfo(String blockReason, Date blockDate) {
        User.BlockInfo blockInfo = new User().new BlockInfo();
        blockInfo.setBlockDate(blockDate);
        blockInfo.setBlockReason(blockReason);
        this.blockInfo = blockInfo;
    }

    public void addStatus(String status) {
        this.status = User.Status.valueOf(status.toUpperCase());
    }

    public void addAccount(String account) {
        this.account = account;
    }

    public void addId(int id) {
        this.id = id;
    }

    public void addDiscount(int discount) {
        this.discount = discount;
    }

    public void addBalance(int balance) {
        this.balance = balance;
    }

    public void addRequests(int requests) {
        this.requests = requests;
    }

    public User buildUser(){
        User user = new User();
        user.setId(id);
        user.setPersonalInfo(personalInfo);
        user.setStatus(status);
        user.setAccount(account);
        user.setBlockInfo(blockInfo);
        user.setDiscount(discount);
        user.setBalance(balance);
        user.setRequests(requests);
        return user;
    }
}
