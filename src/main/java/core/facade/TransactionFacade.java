package core.facade;

import persist.DAOFactory;
import persist.dao.TransactionDAO;
import persist.dao.mysql.MySqlDAOFactory;

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
}
