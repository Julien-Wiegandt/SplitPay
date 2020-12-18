package persist;

import persist.dao.UserDAO;

import java.sql.Connection;

public abstract class DAOFactory {

    /**
     *
     */
    public static Connection connection;

    /**
     * @return
     */
    public abstract UserDAO createUserDao();

    /**
     * @return
     */
    public abstract Connection getConnection();

    /**
     * @return
     */
    private DAOFactory DaoFactory() {
        return null;
    }
}
