package persist;

import persist.dao.UserDAO;
import persist.dao.mysql.MySqlFriendDAO;
import persist.dao.mysql.MySqlNotificationDAO;

import java.sql.Connection;

public abstract class DAOFactory {

    /**
     *
     */
    public static Connection connection;


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

    /**
     * @return
     */
    public abstract UserDAO createUserDao();



    public abstract MySqlFriendDAO createFriendDao();

    public abstract MySqlNotificationDAO createNotificationDao();
}
