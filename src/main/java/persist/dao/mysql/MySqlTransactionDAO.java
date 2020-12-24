package persist.dao.mysql;

import core.models.StoreOwner;
import core.models.Transaction;
import core.models.User;
import persist.dao.TransactionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class MySqlTransactionDAO implements TransactionDAO {

    public MySqlTransactionDAO(){

    }

    public Collection<Transaction> findAllTransactions(int userId) {
        return null;
    }
}
