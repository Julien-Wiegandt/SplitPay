package persist.dao;

import core.models.BankAccount;

public interface BankAccountDAO {

    public BankAccount getBankAccountById(int bankAccountId);

}
