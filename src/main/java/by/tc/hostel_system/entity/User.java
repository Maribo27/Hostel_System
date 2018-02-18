package by.tc.hostel_system.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 2299823395903644174L;
    private PersonalInfo personalInfo;
    private Status status;
    private String account;
    private BlockInfo blockInfo;

    private int id;
    private int discount;
    private int balance;
    private int requests;

    public User(){
        this.personalInfo = new PersonalInfo();
        this.blockInfo = new BlockInfo();
        this.status = Status.USER;
        this.discount = 0;
        this.balance = 0;
    }

    public class PersonalInfo {
        private String username;
        private String email;
        private String name;
        private String surname;
        private String lastName;

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

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonalInfo that = (PersonalInfo) o;
            return Objects.equals(username, that.username) &&
                    Objects.equals(email, that.email) &&
                    Objects.equals(name, that.name) &&
                    Objects.equals(surname, that.surname) &&
                    Objects.equals(lastName, that.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, email, name, surname, lastName);
        }

        @Override
        public String toString() {
            return "PersonalInfo : " +
                    "username=\"" + username + '\"' +
                    ", email=\"" + email + '\"' +
                    ", name=\"" + name + '\"' +
                    ", surname=\"" + surname + '\"' +
                    ", lastName=\"" + lastName + '\"';
        }
    }

    public class BlockInfo {
        private String reason;
        private Date date;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BlockInfo blockInfo = (BlockInfo) o;
            return Objects.equals(reason, blockInfo.reason) &&
                    Objects.equals(date, blockInfo.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(reason, date);
        }

        @Override
        public String toString() {
            return "BlockInfo : " +
                    "reason=\"" + reason + '\"' +
                    ", date=" + date;
        }
    }

    public enum Status {
        BANNED, USER, ADMIN
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

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public BlockInfo getBlockInfo() {
        return blockInfo;
    }

    public void setBlockInfo(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                discount == user.discount &&
                balance == user.balance &&
                requests == user.requests &&
                Objects.equals(personalInfo, user.personalInfo) &&
                status == user.status &&
                Objects.equals(account, user.account) &&
                Objects.equals(blockInfo, user.blockInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(personalInfo, status, account, blockInfo, id, discount, balance, requests);
    }

    @Override
    public String toString() {
        return "User : " +
                "id=" + id +
                ", personalInfo=" + personalInfo +
                ", discount=" + discount +
                ", balance=" + balance +
                ", requests=" + requests +
                ", account='" + account + '\'' +
                ", status=" + status +
                ", blockInfo=" + blockInfo;
    }

    
}
