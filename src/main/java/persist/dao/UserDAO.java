package persist.dao;

import core.models.*;

import java.sql.SQLException;
import java.util.*;

/**
 *
 */
public abstract class UserDAO{

    public abstract Collection getFriends(int userid);

    /**
     * @param phone
     * @param password
     */
    public abstract User emailLogIn(String phone, String password) throws Exception ;

    /**
     * @param email
     * @param password
     */
    public abstract User phoneLogIn(String email, String password) throws Exception ;

    /**
     * @param credential
     * @return
     */
    public abstract User findUser(String credential) throws SQLException;

    /**
     * @param email

     */
    public abstract User findUserByEmail(String email) throws SQLException;

    /**
     * @param phone

     */
    public abstract User findUserByPhone(String phone) ;


    /**
     * @param object

     */
    public abstract User createNormalUser(NormalUser object) ;


    public abstract User createStoreOwner(StoreOwner object) ;


    /**
     * @param object
     * @return
     */
    public abstract boolean delete(User object);

    /**
     * @param object
     * @return
     */
    public abstract boolean update(User object);

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
    public abstract User findById(int id) ;

    /**
     *
     */
    public abstract  void error() ;

    /**
     * @return
     */
    public abstract Collection<User> findAll();


    public abstract User findUserById(int userId) throws SQLException;

}