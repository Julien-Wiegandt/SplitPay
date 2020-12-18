package core.facade;

import persist.DAOFactory;
import persist.dao.UserDAO;
import core.models.NormalUser;
import core.models.StoreOwner;
import core.models.User;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.*;

/**
 *
 */
public class UserFacade {

    /**
     *
     */
    private static UserFacade userFaçade;


    /**
     *
     */
    private static DAOFactory daoFactory = new MySqlDAOFactory();

    /**
     *
     */
    private User user;

    /**
     *
     */
    private UserDAO userDao;

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
        user = userDao.createStoreOwner(new StoreOwner(null, null, credential, siret, password, nickname, 0f, null, companyName, null));
    }

    public void storeOwnerEmailSignUp(String credential, String companyName, String nickname, String siret, String password){
        user = userDao.createStoreOwner(new StoreOwner(null, credential, null, siret, password, nickname, 0f, null, companyName, null));
    }

    public void normalUserEmailSignUp(String credential, String firstName, String lastName, String nickname, String password){
        user = userDao.createNormalUser(new NormalUser(firstName, lastName, null, credential, null, password, nickname, 0f, null));
    }

    public void normalUserPhoneSignUp(String credential, String firstName, String lastName, String nickname, String password){
        user = userDao.createNormalUser(new NormalUser(firstName, lastName, null, null, credential, password, nickname, 0f, null));
    }

    /**
     * @return
     */
    public static UserFacade getUserFacade() {
        // TODO implement here
        if(userFaçade==null){
            userFaçade=new UserFacade();
        }

        return userFaçade;
    }

    /**
     * @return
     */
    private UserFacade() {
        // TODO implement here
        userDao=daoFactory.createUserDao();
    }

    public User getUser() {
        return user;
    }
}
