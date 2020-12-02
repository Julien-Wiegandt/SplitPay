package persist;

import persist.models.User;

import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public abstract class UserDaoImpl implements DAO<User> {

    /**
     * Default constructor
     */
    public UserDaoImpl() {
    }

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
    public abstract User findUser(String credential) ;

    /**
     * @param email

     */
    public abstract User findUserByEmail(String email) throws SQLException;

    /**
     * @param phone 

     */
    public abstract User findUserByPhone(String phone) ;

    /**
     * @param id 

     */
    public boolean generateValidationCode(int id) {
        // TODO implement here
        return false;
    }

    /**
     * @param id 

     */
    public abstract User setValidationCode(int id);

    /**
     * @param credential 
     * @param password 

     */
    public abstract void login(String credential, String password) ;

    /**
     * @param object

     */
    public abstract User create(User object) ;

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

}