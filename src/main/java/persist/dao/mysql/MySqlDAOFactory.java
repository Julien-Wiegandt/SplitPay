package persist.dao.mysql;


import persist.DAOFactory;
import persist.dao.BankAccountDAO;
import persist.dao.CreditCardDAO;
import persist.dao.GroupDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import persist.dao.TransactionDAO;

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




    public GroupDAO createGroupDAO(){return new MySqlGroupDAO();}

    public MySqlBankAccountDAO createBankAccountDAO() { return new MySqlBankAccountDAO(); }
}