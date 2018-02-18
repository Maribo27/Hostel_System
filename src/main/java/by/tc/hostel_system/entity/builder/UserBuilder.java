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

    /**
     * Adds personal info to {@link User}.
     *
     * @param username user username
     * @param email user email
     * @param name user name
     * @param surname user surname
     * @param lastName user last name
     */
    public void addPersonalInfo(String username, String email, String name, String surname, String lastName) {
        User.PersonalInfo personalInfo = new User().new PersonalInfo();
        personalInfo.setUsername(username);
        personalInfo.setEmail(email);
        personalInfo.setName(name);
        personalInfo.setSurname(surname);
        personalInfo.setLastName(lastName);
        this.personalInfo = personalInfo;
    }

    /**
     * Adds information about blocking to {@link User}.
     *
     * @param blockReason reason of blocking
     * @param blockDate date of blocking
     */
    public void addBlockInfo(String blockReason, Date blockDate) {
        User.BlockInfo blockInfo = new User().new BlockInfo();
        blockInfo.setDate(blockDate);
        blockInfo.setReason(blockReason);
        this.blockInfo = blockInfo;
    }

    /**
     * Adds status to {@link User}.
     *
     * @param status user status
     */
    public void addStatus(String status) {
        this.status = User.Status.valueOf(status.toUpperCase());
    }

    /**
     * Adds account number to {@link User}.
     *
     * @param account account number
     */
    public void addAccount(String account) {
        this.account = account;
    }

    /**
     * Adds id to {@link User}.
     *
     * @param id user id
     */
    public void addId(int id) {
        this.id = id;
    }

    /**
     * Adds discount to {@link User}.
     *
     * @param discount user discount
     */
    public void addDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Adds balance to {@link User}.
     *
     * @param balance user balance
     */
    public void addBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Adds number of requests to {@link User}.
     *
     * @param requests number of user requests
     */
    public void addRequests(int requests) {
        this.requests = requests;
    }

    /**
     * Builds {@link User}.
     *
     * @return new user
     */
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
