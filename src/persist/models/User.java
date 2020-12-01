package persist.models;

import java.util.*;

/**
 * 
 */
public class User {

    /**
     * Default constructor
     */
    public User(String id, String email, String phone, String password, String nickname, Float balance, String validationCode) {
        this.id=id;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.nickname=nickname;
        this.balance=balance;
        this.validationCode=validationCode;
    }

    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Float balance;

    /**
     * 
     */
    private String validationCode;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", validationCode='" + validationCode + '\'' +
                '}';
    }
}