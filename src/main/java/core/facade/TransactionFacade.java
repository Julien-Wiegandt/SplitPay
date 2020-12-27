package core.facade;

import core.auth.Session;
import core.models.Transaction;
import persist.DAOFactory;
import persist.dao.TransactionDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.Collection;

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
}
