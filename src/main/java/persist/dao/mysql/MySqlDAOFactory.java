package persist.dao.mysql;


import persist.DAOFactory;
import persist.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class MySqlDAOFactory extends DAOFactory {
    public MySqlDAOFactory(){ new ConnectionMySql();}

    /**
     * @return
     */
    public MySqlUserDAO createUserDao() {
        return new MySqlUserDAO();
    }


    public MySqlTransactionDAO createTransactionDAO() { return new MySqlTransactionDAO(); }



    public CreditCardDAO createCreditCardDao() {
        return new MySqlCreditCardDAO();

    }

    /**
     * @return
     */
    public MySqlFriendDAO createFriendDao() {
        return new MySqlFriendDAO();
    }

    /**
     * @return
     */
    public MySqlNotificationDAO createNotificationDao() {
        return new MySqlNotificationDAO();
    }

    @Override
    public BillDAO createBillDao() {
        return new MySqlBillDAO();
    }


    public GroupDAO createGroupDAO(){return new MySqlGroupDAO();}

    public MySqlBankAccountDAO createBankAccountDAO() { return new MySqlBankAccountDAO(); }
}