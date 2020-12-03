package persist;

import persist.models.NormalUser;
import persist.models.StoreOwner;
import persist.models.User;

import java.util.*;

/**
 * 
 */
public class PostGresUserDao extends UserDaoImpl {

    /**
     * Default constructor
     */
    public PostGresUserDao() {
        super();
    }

    @Override
    public User emailLogIn(String phone, String password) throws Exception {
        return null;
    }

    @Override
    public User phoneLogIn(String email, String password) throws Exception {
        return null;
    }

    @Override
    public User findUser(String credential) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByPhone(String phone) {
        return null;
    }

    @Override
    public User setValidationCode(int id) {
        return null;
    }

    @Override
    public User createNormalUser(NormalUser object) {
        return null;
    }

    @Override
    public User createStoreOwner(StoreOwner object) {
        return null;
    }


    public User createStoreOwner(User object) {
        return null;
    }


    @Override
    public User create(User object) {
        return null;
    }

    @Override
    public boolean delete(User object) {
        return false;
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void error() {

    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    /**
     * @return
     */
    private PostGresUserDao PostGresUserDao() {
        // TODO implement here
        return null;
    }

}