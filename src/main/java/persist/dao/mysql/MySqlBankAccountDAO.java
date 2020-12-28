package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.BankAccount;
import core.models.CreditCard;
import persist.dao.BankAccountDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySqlBankAccountDAO implements BankAccountDAO {

    public BankAccount getBankAccountById(int bankAccountId) {
        Statement stmt = null;
        BankAccount bankAccount = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM BankAccount WHERE bank_account_pk=" + bankAccountId + ";");

            while (rs.next()) {
                String id = rs.getString("bank_account_pk");
                String label = rs.getString("label");
                String bic = rs.getString("bic");
                String iban = rs.getString("iban");
                String ownerFirstName = rs.getString("ownerFirstName");
                String ownerLastName = rs.getString("ownerLastName");

                bankAccount = new BankAccount(id, label, bic, iban, ownerFirstName, ownerLastName);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return bankAccount;
    }
}
