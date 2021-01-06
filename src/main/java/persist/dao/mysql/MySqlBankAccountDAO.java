package persist.dao.mysql;

import core.facade.BankAccountFacade;
import core.facade.UserFacade;
import core.models.BankAccount;

import persist.dao.BankAccountDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;


public class MySqlBankAccountDAO extends BankAccountDAO {

    public MySqlBankAccountDAO(){super(); }

    public void createBankAccount(BankAccount bankAccount){
        Statement stmt = null;


        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("création..");

            Integer rs1 = stmt.executeUpdate("INSERT INTO BankAccount VALUES (NULL, '"+ bankAccount.getLabel()+"', '"+bankAccount.getBic()+"','"+ bankAccount.getIban()+"', '"+bankAccount.getOwnerFirstName()+"', '"+ bankAccount.getOwnerLastName()+"')");
            String id_iban = findBankAccountIdByIban(bankAccount.getIban());
            if(UserFacade.getUserFacade().isStoreOwner()) {

                Integer rs2 = stmt.executeUpdate("INSERT INTO Relation_StoreOwner_BankAccount VALUES ('" + UserFacade.getUserFacade().getLoggedUser().getId() + "', '" + id_iban + "')");

            }else {
                Integer rs2 = stmt.executeUpdate("INSERT INTO Relation_NormalUser_BankAccount VALUES ('" + UserFacade.getUserFacade().getLoggedUser().getId() + "', '" + id_iban + "')");
            }
            /*
            BEGIN TRANSACTION
   DECLARE @DataID int;
   INSERT INTO DataTable (Column1 ...) VALUES (....);
   SELECT @DataID = scope_identity();
   INSERT INTO LinkTable VALUES (@ObjectID, @DataID);
COMMIT
             */
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteBankAccount(String iban) {
        Statement stmt = null;

        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            System.out.println("suppression...");

            stmt.executeUpdate("DELETE FROM BankAccount WHERE iban = '" + iban+ "'");
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private String findBankAccountIdByIban(String iban) {
        Statement stmt = null;
        BankAccount bankAccount= null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM BankAccount WHERE iban='" + iban + "'");

            while (rs.next()) {
                String dbId = rs.getString("bank_account_pk");
                String dbLabel = rs.getString("label");
                String dbBic = rs.getString("bic");
                String dbIban = rs.getString("iban");

                String dbFirstName = rs.getString("ownerFirstName");
                String dbLastName = rs.getString("ownerLastName");
                bankAccount = new BankAccount(dbId, dbLabel, dbBic, dbIban, dbFirstName, dbLastName);
                System.out.println(bankAccount);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return bankAccount.getId();
    }

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

    public Collection<BankAccount> getBankAccounts(){
        Statement stmt = null;
        ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(UserFacade.getUserFacade().isNormalUser()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Relation_NormalUser_BankAccount WHERE normal_user_fk=" + UserFacade.getUserFacade().getUser().getId() + ";");
                while (rs.next()) {
                    BankAccount bankAccount = BankAccountFacade.getInstance().getBankAccountById(rs.getInt("bank_account_fk"));
                    bankAccounts.add(bankAccount);
                }
            }else{
                ResultSet rs = stmt.executeQuery("SELECT * FROM Relation_StoreOwner_BankAccount WHERE store_owner_fk=" + UserFacade.getUserFacade().getUser().getId() + ";");
                while (rs.next()){
                    BankAccount bankAccount = BankAccountFacade.getInstance().getBankAccountById(rs.getInt("bank_account_fk"));
                    bankAccounts.add(bankAccount);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankAccounts;
    }
}
