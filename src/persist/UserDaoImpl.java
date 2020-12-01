package persist;

import persist.models.User;

import java.util.*;

/**
 * 
 */
public class UserDaoImpl implements DAO<User> {

    /**
     * Default constructor
     */
    public UserDaoImpl() {
    }

    /**
     * @return
     */
    public UserDaoImpl getConnection() {
        // TODO implement here
        return null;
    }

    /**
     * @param phone 
     * @param password 
     * @return
     */
    public User emailLogin(String phone, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param email 
     * @param password 
     * @return
     */
    public User phoneLogin(String email, String password) {
        // TODO implement here
        return null;
    }

    /**
     * @param credential 
     * @return
     */
    public User findUser(String credential) {
        // TODO implement here
        return null;
    }

    /**
     * @param email 
     * @return
     */
    public User findUserByEmail(String email) {
        // TODO implement here
        return null;
    }

    /**
     * @param phone 
     * @return
     */
    public User findUserByPhone(String phone) {
        // TODO implement here
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public boolean generateValidationCode(int id) {
        // TODO implement here
        return false;
    }

    /**
     * @param id 
     * @return
     */
    public User setValidationCode(int id) {
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
//        return null;
    }

    /**
     * @param object
     * @return
     */
    public User create(User object) {
        // TODO implement here
        return null;
    }

    /**
     * @param object
     * @return
     */
    public boolean delete(User object) {
        // TODO implement here
        return false;
    }

    /**
     * @param object
     * @return
     */
    public boolean update(User object) {
        // TODO implement here
        return false;
    }

//    /**
//     * @param object
//     * @return
//     */
//    @Override
//    public Object create(Object object) {
//        return null;
//    }
//
//    /**
//     * @param object
//     * @return
//     */
//    @Override
//    public boolean delete(Object object) {
//        return false;
//    }
//
//    /**
//     * @param object
//     * @return
//     */
//    @Override
//    public boolean update(Object object) {
//        return false;
//    }

    /**
     * @param id
     * @return
     */
    public User findById(int id) {
        // TODO implement here
        return null;
    }

    /**
     *
     */
    public void error() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Collection<User> findAll() {
        // TODO implement here
        return null;
    }

}