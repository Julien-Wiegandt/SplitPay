package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.BankAccount;


import persist.dao.BankAccountDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


public class MySqlBankAccountDAO extends BankAccountDAO {

    public MySqlBankAccountDAO(){super(); }

    public void createBankAccount(BankAccount bankAccount){
        Statement stmt = null;


        try {
            stmt = MySqlDAOFactory.connection.createStatement();
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
            stmt = MySqlDAOFactory.connection.createStatement();
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

    @Override
    public ArrayList<BankAccount> getBankAccounts() {
        ArrayList<BankAccount> ccs = new ArrayList<BankAccount>();

        Statement stmt = null;
        BankAccount bankAccount= null;

        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            String id = UserFacade.getUserFacade().getLoggedUser().getId();
            ResultSet rs;
            if(UserFacade.getUserFacade().isStoreOwner()){
                rs = stmt.executeQuery("SELECT * FROM BankAccount B, Relation_StoreOwner_BankAccount R, StoreOwner U WHERE U.store_owner_pk='" +id+ "' AND R.store_owner_fk='"+id+"' AND R.bank_account_fk=B.bank_account_pk ");


            }else{
                rs = stmt.executeQuery("SELECT * FROM BankAccount B, Relation_NormalUser_BankAccount R, NormalUser U WHERE U.normal_user_pk='" +id+ "' AND R.normal_user_fk='"+id+"' AND R.bank_account_fk=B.bank_account_pk ");

            }

            while (rs.next()) {
                String dbId = rs.getString("bank_account_pk");
                String dbLabel = rs.getString("label");
                String dbBic = rs.getString("bic");
                String dbIban = rs.getString("iban");

                String dbFirstName = rs.getString("ownerFirstName");
                String dbLastName = rs.getString("ownerLastName");

                bankAccount = new BankAccount(dbId, dbLabel, dbBic, dbIban, dbFirstName, dbLastName);

                ccs.add(bankAccount);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ccs;
    }


    private String findBankAccountIdByIban(String iban) {
        Statement stmt = null;
        BankAccount bankAccount= null;
        try {
            stmt = MySqlDAOFactory.connection.createStatement();
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



}
