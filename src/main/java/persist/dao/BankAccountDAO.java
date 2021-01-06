package persist.dao;

import core.models.BankAccount;
import core.models.CreditCard;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

public abstract class BankAccountDAO {

    public Connection getConnection(){

        return null;
    }

    public abstract void createBankAccount(BankAccount bankAccount);

    public abstract void deleteBankAccount(String iban);

    public abstract BankAccount getBankAccountById(int bankAccountId);

    public abstract Collection<BankAccount> getBankAccounts();
}
