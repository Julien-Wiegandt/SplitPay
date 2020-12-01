package core;

import persist.DaoFactory;
import persist.models.User;

import java.util.*;

/**
 * 
 */
public class UserFaçade {

    /**
     * Default constructor
     */
    public UserFaçade() {
    }

    /**
     * 
     */
    private static UserFaçade UserFaçade;

    /**
     * 
     */
    private DaoFactory daoFactory;

    /**
     * 
     */
    private User user;

    /**
     * @param credential 
     * @param password 
     * @return
     */
    public User login(String credential, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param credential 
     * @param password 
     * @return
     */
    public User signup(String credential, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public static UserFaçade getUserFaçade() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    private UserFaçade UserFaçade() {
        // TODO implement here
        return null;
    }

}