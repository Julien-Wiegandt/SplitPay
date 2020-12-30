package persist;

import persist.dao.BankAccountDAO;
import persist.dao.CreditCardDAO;
import persist.dao.GroupDAO;
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



    public abstract CreditCardDAO createCreditCardDao();

    public abstract BankAccountDAO createBankAccountDAO();
    public abstract GroupDAO createGroupDAO();

    /**
     * @return
     */
    private DAOFactory DaoFactory() {
        return null;
    }
}
