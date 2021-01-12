package persist.dao;

import core.models.Transaction;

import java.util.Collection;
import java.util.Date;

/**
 * Transaction DAO which provides all possible operations on the persistent data concerning Transaction.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-05
 */
public interface TransactionDAO {

    /**
     * This method allows to retrieve all the transactions of a user.
     * @param userId id of the user whose transactions are wanted.
     * @return user's Transactions.
     */
    Collection<Transaction> findAllTransactions(int userId);

    /**
     * This method creates a CreditCardToUserTransaction.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    void createCreditCardToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    /**
     * This method creates a SplitTransaction.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     * @param participants
     */
    void createSplitTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk, String participants);

    /**
     * This method creates a StoreOwnerToBankAccount.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    void createStoreOwnerToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    /**
     * This method creates a UserToBankAccount.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    void createUserToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk);

    /**
     * This method creates a UserToUserTransaction.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    void createUserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk);
}
