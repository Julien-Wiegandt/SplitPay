package persist;

import persist.dao.BankAccountDAO;
import persist.dao.CreditCardDAO;
import persist.dao.GroupDAO;
import persist.dao.TransactionDAO;
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
    public abstract TransactionDAO createTransactionDAO();



    public abstract CreditCardDAO createCreditCardDao();

    public abstract BankAccountDAO createBankAccountDAO();
    public abstract GroupDAO createGroupDAO();

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
