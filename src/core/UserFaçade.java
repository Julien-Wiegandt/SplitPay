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
    public void emailLogIn(String credential, String password) throws Exception {
        user = userDao.emailLogIn(credential,password);
    }

    /**
     * @param credential
     * @param password
     * @return
     */
    public void phoneLogIn(String credential, String password) throws Exception {
        user = userDao.phoneLogIn(credential,password);
    }

    public void storeOwnerPhoneSignUp(String credential, String companyName, String nickname, String siret, String password){
        //user = userDao.storeOwnerPhoneSignUp(credential, companyName, nickname, siret, password);
    }

    public void storeOwnerEmailSignUp(String credential, String companyName, String nickname, String siret, String password){
        //user = userDao.storeOwnerEmailSignUp(credential, companyName, nickname, siret, password);
    }

    public void normalUserEmailSignUp(String credential, String firstName, String lastName, String nickname, String password){
        //user = userDao.normalUserEmailSignUp(credential, firstName, lastName, nickname, password);
    }

    public void normalUserPhoneSignUp(String credential, String firstName, String lastName, String nickname, String password){
        //user = userDao.normalUserPhoneSignUp(credential, firstName, lastName, nickname, password);
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

    public User getUser() {
        return user;
    }
}
