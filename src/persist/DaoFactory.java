package persist;

import java.sql.Connection;
import java.util.*;

/**
 * 
 */
public abstract class DaoFactory {

    /**
     * 
     */
    private Connection connection;

    /**
     * 
     */
    private static DaoFactory DaoFactory;

    /**
     * 
     */
    private UserDaoImpl dao;

    /**
     * @return
     */
    public abstract UserDaoImpl createUserDao();

    /**
     * @return
     */
    public abstract DaoFactory getConnection();

    /**
     * @return
     */
    private DaoFactory DaoFactory() {
        return null;
    }

    /**
     * @return
     */
    public UserDaoImpl getDao(){ return this.dao;}

}