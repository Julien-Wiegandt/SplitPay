package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.*;
import persist.dao.TransactionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MySqlTransactionDAO implements TransactionDAO {

    public MySqlTransactionDAO(){

    }


    public Collection<Transaction> findAllTransactions(int userId) {
        Statement stmt = null;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs;
            if(UserFacade.getUserFacade().isNormalUser()){
                rs = stmt.executeQuery("SELECT * FROM SplitTransaction WHERE sender_fk='"+ userId +"';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");
                    String participants = rs.getString("participants");

                    transactions.add(new SplitTransaction(amount, dateCreated, sender_fk, receiver_fk, participants));
                }

                rs = stmt.executeQuery("SELECT * FROM UserToUserTransaction WHERE sender_fk='"+ userId +"' OR receiver_fk='"+ userId +"';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new UserToUserTransaction(amount, dateCreated, sender_fk, receiver_fk));
                }

                rs = stmt.executeQuery("SELECT * FROM UserToBankAccount WHERE sender_fk='"+ userId +"';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new UserToBankAccount(amount, dateCreated, sender_fk, receiver_fk));
                }

                rs = stmt.executeQuery("SELECT * FROM BankAccountToUserTransaction WHERE receiver_fk='"+ userId +"';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new BankAccountToUserTransaction(amount, dateCreated, sender_fk, receiver_fk));
                }

            }else{ ;
                rs = stmt.executeQuery("SELECT * FROM SplitTransaction WHERE receiver_fk='" + userId + "';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");
                    String participants = rs.getString("participants");

                    transactions.add(new SplitTransaction(amount, dateCreated, sender_fk, receiver_fk, participants));
                }

                rs = stmt.executeQuery("SELECT * FROM BankAccountToUserTransaction WHERE receiver_fk='" + userId + "';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new BankAccountToUserTransaction(amount, dateCreated, sender_fk, receiver_fk));
                }

                rs = stmt.executeQuery("SELECT * FROM StoreOwnerToBankAccount WHERE sender_fk='" + userId + "';");
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new StoreOwnerToBankAccount(amount, dateCreated, sender_fk, receiver_fk));
                }
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return transactions;
    }
}
