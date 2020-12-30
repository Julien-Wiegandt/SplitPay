package persist.dao;

import core.models.Transaction;

import java.util.Collection;
import java.util.Date;

public interface TransactionDAO {
    public Collection<Transaction> findAllTransactions(int userId);

    public void createCreditCardToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    public void createSplitTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk, String participants);

    public void createStoreOwnerToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    public void createUserToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    public void createUserToStoreOwnerTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    public void createUserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk);
}
