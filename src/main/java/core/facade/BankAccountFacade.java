package core.facade;

import core.models.BankAccount;
import core.models.CreditCard;
import persist.DAOFactory;
import persist.dao.BankAccountDAO;
import persist.dao.mysql.MySqlBankAccountDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.ArrayList;

public class BankAccountFacade {

    private BankAccountDAO bankAccountDAO;

    private static BankAccountFacade bankAccountFacade = null;
    private static DAOFactory daoFactory = new MySqlDAOFactory();

    private BankAccountFacade(){
        this.bankAccountDAO = daoFactory.createBankAccountDAO();
    }

    public static BankAccountFacade getInstance(){

        if(bankAccountFacade == null){
            bankAccountFacade = new BankAccountFacade();
        }
        return bankAccountFacade;
    }

    public void createBankAccount(String label, String bic,  String iban, String ownerFirstName, String ownerLastName){
        this.bankAccountDAO.createBankAccount(new BankAccount(null,label,bic, iban, ownerFirstName, ownerLastName));
    }

    public void deleteBankAccount(String iban){
        this.bankAccountDAO.deleteBankAccount(iban);
    }




    public ArrayList<BankAccount> getBankAccounts() {
        return this.bankAccountDAO.getBankAccounts();
    }
}
