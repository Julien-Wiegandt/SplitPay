package core.facade;

import core.models.BankAccount;
import persist.DAOFactory;
import persist.dao.BankAccountDAO;
import persist.dao.TransactionDAO;
import persist.dao.mysql.MySqlDAOFactory;

public class BankAccountFacade {
    /**
     *
     */
    private static BankAccountFacade bankAccountFacade;

    /**
     *
     */
    private static DAOFactory daoFactory = new MySqlDAOFactory();

    /**
     *
     */
    private BankAccountDAO bankAccountDAO;

    /**
     * @return
     */
    public static BankAccountFacade getBankAccountFacade() {
        if(bankAccountFacade==null){
            bankAccountFacade=new BankAccountFacade();
        }

        return bankAccountFacade;
    }

    /**
     * @return
     */
    private BankAccountFacade() {
        bankAccountDAO=daoFactory.createBankAccountDAO();
    }

    public BankAccount getBankAccountById(int bankAccountId){
        return bankAccountDAO.getBankAccountById(bankAccountId);
    }
}
