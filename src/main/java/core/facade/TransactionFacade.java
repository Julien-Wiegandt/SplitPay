package core.facade;

import core.auth.Session;
import core.models.Transaction;
import persist.DAOFactory;
import persist.dao.TransactionDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.Collection;
import java.util.Date;

/**
 *
 */
public class TransactionFacade {

    /**
     *
     */
    private static TransactionFacade transactionFacade;

    /**
     *
     */
    private static DAOFactory daoFactory = new MySqlDAOFactory();

    /**
     *
     */
    private TransactionDAO transactionDAO;

    /**
     * @return
     */
    public static TransactionFacade getTransactionFacade() {
        if(transactionFacade==null){
            transactionFacade=new TransactionFacade();
        }

        return transactionFacade;
    }

    /**
     * @return
     */
    private TransactionFacade() {
        transactionDAO=daoFactory.createTransactionDAO();
    }

    public Collection<Transaction> getTransactions(){
        return transactionDAO.findAllTransactions(Integer.parseInt(UserFacade.getUserFacade().getUser().getId()));
    }

    public void createBankAccountToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createBankAccountToUserTransaction(amount, dateCreated, sender_fk, receiver_fk);
    }

    public void createSplitTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk, String participants){
        transactionDAO.createSplitTransaction(amount, dateCreated, sender_fk, receiver_fk, participants);
    }

    public void createStoreOwnerToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createStoreOwnerToBankAccount(amount, dateCreated, sender_fk, receiver_fk);
    }

    public void createUserToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createUserToBankAccount(amount, dateCreated, sender_fk, receiver_fk);
    }

    public void createUserToStoreOwnerTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createUserToStoreOwnerTransaction(amount, dateCreated, sender_fk, receiver_fk);
    }

    public void createUserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk){
        transactionDAO.createUserToUserTransaction(amount, dateCreated, sender_fk, receiver_fk);
    }

}
