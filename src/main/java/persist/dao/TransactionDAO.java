package persist.dao;

import core.models.Transaction;

import java.util.Collection;

public interface TransactionDAO {
    public Collection<Transaction> findAllTransactions(int userId);
}
