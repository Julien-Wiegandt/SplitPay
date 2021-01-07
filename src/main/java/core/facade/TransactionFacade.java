package core.facade;

import core.models.Transaction;
import persist.DAOFactory;
import persist.dao.TransactionDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.Collection;
import java.util.Date;

/**
 * Facade providing all possible actions concerning the Transaction type.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-03
 */
public class TransactionFacade {

    /**
     * Private instance of the singleton transactionFacade
     */
    private static TransactionFacade transactionFacade;

    /**
     * @todo Should we delete it?
     */
    private static DAOFactory daoFactory = new MySqlDAOFactory();

    /**
     * TransactionDAO providing all possible actions concerning the persistent Transaction's data.
     */
    private TransactionDAO transactionDAO;

    /**
     * This method is the getter of the TransactionFacade singleton instance.
     * @return the transactionFacade singleton instance.
     */
    public static TransactionFacade getTransactionFacade() {
        if(transactionFacade==null){
            transactionFacade=new TransactionFacade();
        }
        return transactionFacade;
    }

    /**
     * The TransactionFacade constructor called by the getTransactionFacade method if the singleton instance is null.
     */
    private TransactionFacade() {
        transactionDAO=daoFactory.createTransactionDAO();
    }

    /**
     * This method returns all current user's persistent Transaction's data.
     * @return all current user's transactions.
     */
    public Collection<Transaction> getTransactions(){
        return transactionDAO.findAllTransactions(Integer.parseInt(UserFacade.getUserFacade().getUser().getId()));
    }

    /**
     * This method create a CreditCardToUserTransaction in the persistent Transaction's data.
     * @param amount The transaction's amount.
     * @param dateCreated The transaction's creation date.
     * @param sender_fk The transaction's sender.
     * @param receiver_fk The transaction's receiver.
     */
    public void createCreditCardToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createCreditCardToUserTransaction(amount, dateCreated, sender_fk, receiver_fk);
    }

    /**
     * This method create a SplitTransaction in the persistent Transaction's data.
     * @param amount The transaction's amount.
     * @param dateCreated The transaction's creation date.
     * @param sender_fk The transaction's sender.
     * @param receiver_fk The transaction's receiver.
     * @param participants The split transaction's participants.
     */
    public void createSplitTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk, String participants){
        transactionDAO.createSplitTransaction(amount, dateCreated, sender_fk, receiver_fk, participants);
    }

    /**
     * This method create a StoreOwnerToBankAccount in the persistent Transaction's data.
     * @param amount The transaction's amount.
     * @param dateCreated The transaction's creation date.
     * @param sender_fk The transaction's sender.
     * @param receiver_fk The transaction's receiver.
     */
    public void createStoreOwnerToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createStoreOwnerToBankAccount(amount, dateCreated, sender_fk, receiver_fk);
    }

    /**
     * This method create a UserToBankAccount in the persistent Transaction's data.
     * @param amount The transaction's amount.
     * @param dateCreated The transaction's creation date.
     * @param sender_fk The transaction's sender.
     * @param receiver_fk The transaction's receiver.
     */
    public void createUserToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createUserToBankAccount(amount, dateCreated, sender_fk, receiver_fk);
    }

    /**
     * This method create a UserToUserTransaction in the persistent Transaction's data.
     * @param amount The transaction's amount.
     * @param dateCreated The transaction's creation date.
     * @param sender_fk The transaction's sender.
     * @param receiver_fk The transaction's receiver.
     */
    public void createUserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createUserToUserTransaction(amount, dateCreated, sender_fk, receiver_fk);
    }

}
