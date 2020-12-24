package persist.dao.mysql;


import persist.DAOFactory;
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
}