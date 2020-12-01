package persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public abstract class DaoFactory {

    /**
     * 
     */
    protected static Connection connection;

    /**
     * @return
     */
    public abstract UserDaoImpl createUserDao();

    /**
     * @return
     */
    public abstract Connection getConnection();

    /**
     * @return
     */
    private DaoFactory DaoFactory() {
        return null;
    }

}