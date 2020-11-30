package core;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 */
public class UserFaçade implements Observable {

    /**
     * Default constructor
     */
    public UserFaçade() {
    }

    /**
     *
     */
    //private UserDaoImpl dao;

    /**
     * @param email
     * @param password
     * @return
     */
    private UserFaçade emailLogin(String email, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param phone
     * @param password
     * @return
     */
    private UserFaçade phoneLogin(String phone, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param credential
     * @param password
     * @return
     */
    public void login(String credential, String password) {
        // TODO implement here
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }

    public void signUp(String email, String phone, String firstName, String lastName, String nickname, String password) {

    }
}
