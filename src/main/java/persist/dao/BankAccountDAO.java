package persist.dao;

import core.models.BankAccount;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class BankAccountDAO {

    public Connection getConnection(){

        return null;
    }

    public abstract void createBankAccount(BankAccount bankAccount);

    public abstract void deleteBankAccount(String iban);

    public abstract ArrayList<BankAccount> getBankAccounts();

    public abstract BankAccount getBankAccountById(int bankAccountId);
}
