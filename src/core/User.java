package core;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.input.MouseEvent;

import java.util.*;

/**
 *
 */
public class User implements Observable {

    /**
     * Default constructor
     */
    public User(String email, String phone, String nickname, String password) {
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
        this.balance = 0.0;
    }

    public Double getBalance() {
        return this.balance;
    }

    /**
     *
     */
    public String email;

    /**
     *
     */
    public String phone;

    /**
     *
     */
    public String nickname;

    /**
     *
     */
    public String password;

    /**
     *
     */
    public Double balance;

    /**
     *
     */
    //private UserDaoImpl dao;

    /**
     * @param email
     * @param password
     * @return
     */
    public User emailLogin(String email, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param phone
     * @param password
     * @return
     */
    public User phoneLogin(String phone, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param credential
     * @param password
     * @return
     */
    public static User login(String credential, String password) {
        // TODO implement here
        return new User("imad@gmail.com", null, "imadBG34", "123");
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
