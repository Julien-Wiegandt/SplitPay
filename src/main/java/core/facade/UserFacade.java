package core.facade;

import core.auth.Session;
import core.models.NormalUser;
import core.models.StoreOwner;
import core.models.User;
import persist.DAOFactory;
import persist.dao.UserDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public class UserFacade {

    /**
     *
     */
    private static final DAOFactory daoFactory = new MySqlDAOFactory();
    /**
     *
     */
    private static UserFacade userFaçade;

    /**
     *
     */
    private final UserDAO userDao;
    /**
     *
     */
    private final Session session;
    /**
     *
     */
    private User user;

    /**
     * @return
     */
    private UserFacade() {
        userDao = daoFactory.createUserDao();
        session = new Session();
    }

    /**
     * @return
     */
    public static UserFacade getUserFacade() {
        if (userFaçade == null) {
            userFaçade = new UserFacade();
        }

        return userFaçade;
    }

    public static void deleteAccount() {
        User user = getUserFacade().getLoggedUser();
        getUserFacade().userDao.delete(user);

    }

    /**
     * @param credential
     * @param password
     * @return
     */
    public void emailLogIn(String credential, String password) throws Exception {
        user = userDao.emailLogIn(credential, password);
        session.setLoggedUser(user);
    }

    /**
     * @param credential
     * @param password
     * @return
     */
    public void phoneLogIn(String credential, String password) throws Exception {
        user = userDao.phoneLogIn(credential, password);
        session.setLoggedUser(user);
    }

    public void storeOwnerPhoneSignUp(String credential, String companyName, String nickname, String siret, String adress, String password) {
        user = userDao.createStoreOwner(new StoreOwner(null, null, credential, siret, password, nickname, 0f, companyName, adress));
    }

    public void storeOwnerEmailSignUp(String credential, String companyName, String nickname, String siret, String adress, String password) {
        user = userDao.createStoreOwner(new StoreOwner(null, credential, null, siret, password, nickname, 0f, companyName, adress));
    }

    public void normalUserEmailSignUp(String credential, String firstName, String lastName, String nickname, String password) {
        user = userDao.createNormalUser(new NormalUser(firstName, lastName, null, credential, null, password, nickname, 0f));
    }

    public void normalUserPhoneSignUp(String credential, String firstName, String lastName, String nickname, String password) {
        user = userDao.createNormalUser(new NormalUser(firstName, lastName, null, null, credential, password, nickname, 0f));
    }

    public NormalUser getLoggedNormalUser() {
        return session.getLoggedNormalUser();
    }

    public StoreOwner getLoggedStoreOwner() {
        return session.getLoggedStoreOwner();
    }

    public boolean isStoreOwner() {
        return session.isStoreOwner();
    }

    public boolean isNormalUser() {
        return session.isNormalUser();
    }

    /**
     * pre : a user must be logged in
     * Returns the logged in user
     */
    public User getLoggedUser() {
        return session.getLoggedUser();
    }

    public void setLoggedUser(User user) {
        session.setLoggedUser(user);
    }

    public void logout() {
        session.logOut();
    }

    public User getUser() {
        return user;
    }

    public Boolean isEnoughtMoneyInBalance(Float money) {
        return money <= getUser().getBalance();
    }

    // TODO : refactor to updateNormalUserBalanceById
    public void updateUserBalanceById(int userId, Float amount) {
        try {
            User u = userDao.findUserById(userId);
            u.setBalance(u.getBalance() + amount);
            userDao.update(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateStoreOwnerBalanceById(int userId, Float amount) {
        try {
            User u = userDao.findStoreOwnerById(userId);
            u.setBalance(u.getBalance() + amount);
            userDao.update(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Returns the user having this phone number
     * else throws exception
     */
    public User findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    public User findUserByEmail(String email) throws SQLException {
        return userDao.findUserByEmail(email);
    }

    public void updateUser(User user) {
        this.user = user;
        userDao.update(user);
    }

    public NormalUser findNormalUserById(int id) throws SQLException {
        return userDao.findNormalUserById(id);
    }

    public StoreOwner findStoreOwnerById(int id) throws SQLException {
        return userDao.findStoreOwnerById(id);
    }


    public Collection getFriends(int parseInt) {
        return userDao.getFriends(parseInt);
    }

    public ArrayList<StoreOwner> getAllStoreOwners() throws SQLException {
        return userDao.findAllStoreOwners();
    }

}
