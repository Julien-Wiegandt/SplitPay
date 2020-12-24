package persist;

import persist.dao.TransactionDAO;
import persist.dao.UserDAO;

import java.sql.Connection;

public abstract class DAOFactory {

    /**
     * @return
     */
    public abstract UserDAO createUserDao();

    /**
     * @return
     */
    public abstract TransactionDAO createTransactionDAO();

}
