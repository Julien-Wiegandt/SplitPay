package persist.dao;

import core.models.Bill;
import core.models.Transaction;

import java.sql.SQLException;
import java.util.Collection;

public interface BillDAO {

    /**
     * This method allows to retrieve all the transactions of a user.
     * @param userId id of the user whose transactions are wanted.
     * @return user's Transactions.
     */
    public Collection<Bill> findAllUserBills(int userId) throws SQLException;
}
