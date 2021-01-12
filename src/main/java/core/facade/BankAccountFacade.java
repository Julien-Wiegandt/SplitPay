package core.facade;

import core.models.BankAccount;
import persist.DAOFactory;
import persist.dao.BankAccountDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.Collection;

public class BankAccountFacade {

    private static final DAOFactory daoFactory = new MySqlDAOFactory();
    private static BankAccountFacade bankAccountFacade = null;
    private final BankAccountDAO bankAccountDAO;

    private BankAccountFacade() {
        this.bankAccountDAO = daoFactory.createBankAccountDAO();
    }

    public static BankAccountFacade getInstance() {

        if (bankAccountFacade == null) {
            bankAccountFacade = new BankAccountFacade();
        }
        return bankAccountFacade;
    }

    public void createBankAccount(String label, String bic, String iban, String ownerFirstName, String ownerLastName) {
        this.bankAccountDAO.createBankAccount(new BankAccount(null, label, bic, iban, ownerFirstName, ownerLastName));
    }

    public void deleteBankAccount(String iban) {
        this.bankAccountDAO.deleteBankAccount(iban);
    }

    public Collection<BankAccount> getBankAccounts() {
        return this.bankAccountDAO.getBankAccounts();

    }

    public BankAccount getBankAccountById(int bankAccountId) {
        return bankAccountDAO.getBankAccountById(bankAccountId);
    }
}
