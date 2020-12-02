package core;

import persist.DaoFactory;
import persist.MySqlDaoFactory;
import persist.UserDaoImpl;
import persist.models.User;

import java.util.*;

/**
 * 
 */
public class UserFaçade {

    /**
     *
     */
    private static UserFaçade userFaçade;


    /**
     * 
     */
    private static DaoFactory daoFactory = new MySqlDaoFactory();

    /**
     * 
     */
    private User user;

    /**
     *
     */
    private UserDaoImpl userDao;

    /**
     * @param credential
     * @param password
     * @return
     */
    public User emailLogIn(String credential, String password) {
        //userDao.emailLogIn(credential,password);
        return null;
    }

    /**
     * @param credential
     * @param password
     * @return
     */
    public User phoneLogIn(String credential, String password) {
        //userDao.emailLogIn(credential,password);
        return null;
    }

    /**
     * @param credential 
     * @param password 
     * @return
     */
    public User signUp(String credential, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public static UserFaçade getUserFaçade() {
        // TODO implement here
        if(userFaçade==null){
            userFaçade=new UserFaçade();
        }

        return userFaçade;
    }

    /**
     * @return
     */
    private UserFaçade() {
        // TODO implement here
        userDao=daoFactory.createUserDao();
    }

}
