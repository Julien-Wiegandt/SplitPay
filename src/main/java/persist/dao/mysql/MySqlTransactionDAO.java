package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.*;
import persist.dao.TransactionDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Transaction MySql DAO which provides all possible operations on the persistent data concerning Transaction for a MySQL database.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2021-01-05
 */
public class MySqlTransactionDAO implements TransactionDAO {

    /**
     * MySqlTransactionDAO's constructor.
     */
    public MySqlTransactionDAO(){ }

    /**
     * This method makes SQL queries on persistent data to find all transactions of the user having userid.
     * @param userId id of the user whose transactions are wanted.
     * @return user's Transactions.
     */
    public Collection<Transaction> findAllTransactions(int userId) {
        PreparedStatement statement = null;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            ResultSet rs;
            if(UserFacade.getUserFacade().isNormalUser()){
                statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM SplitTransaction WHERE sender_fk=?;");
                statement.setInt(1, userId);
                rs = statement.executeQuery();
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");
                    String participants = rs.getString("participants");

                    transactions.add(new SplitTransaction(amount, dateCreated, sender_fk, receiver_fk, participants));
                }
                statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM UserToUserTransaction WHERE sender_fk=? OR receiver_fk=?;");
                statement.setInt(1, userId);
                statement.setInt(2, userId);
                rs = statement.executeQuery();
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new UserToUserTransaction(amount, dateCreated, sender_fk, receiver_fk));
                }
                statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM UserToBankAccount WHERE sender_fk=?;");
                statement.setInt(1, userId);
                rs = statement.executeQuery();
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new UserToBankAccount(amount, dateCreated, sender_fk, receiver_fk));
                }
                statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM CreditCardToUserTransaction WHERE receiver_fk=?;");
                statement.setInt(1, userId);
                rs = statement.executeQuery();
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new CreditCardToUserTransaction(amount, dateCreated, sender_fk, receiver_fk));
                }
                statement.close();
                rs.close();
            }else{
                statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM SplitTransaction WHERE receiver_fk=?;");
                statement.setInt(1, userId);
                rs = statement.executeQuery();
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");
                    String participants = rs.getString("participants");

                    transactions.add(new SplitTransaction(amount, dateCreated, sender_fk, receiver_fk, participants));
                }
                statement = ConnectionMySql.connection.prepareStatement("SELECT * FROM StoreOwnerToBankAccount WHERE sender_fk=?;");
                statement.setInt(1, userId);
                rs = statement.executeQuery();
                while(rs.next()){
                    Float amount = rs.getFloat("amount");
                    Date dateCreated = rs.getDate("dateCreated");
                    int sender_fk = rs.getInt("sender_fk");
                    int receiver_fk = rs.getInt("receiver_fk");

                    transactions.add(new StoreOwnerToBankAccount(amount, dateCreated, sender_fk, receiver_fk));
                }
                statement.close();
                rs.close();
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return transactions;
    }

    /**
     * This method creates a CreditCardToUserTransaction in persistent data via an sql query.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    public void createCreditCardToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(dateCreated.getTime());
            PreparedStatement statement = ConnectionMySql.connection.prepareStatement("INSERT INTO CreditCardToUserTransaction VALUES (?, ?, ?, ?);");
            statement.setFloat(1, amount);
            statement.setDate(2, sqlDate);
            statement.setInt(3, sender_fk);
            statement.setInt(4, receiver_fk);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method creates a SplitTransaction in persistent data via an sql query.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     * @param participants
     */
    public void createSplitTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk, String participants) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(dateCreated.getTime());
            PreparedStatement statement = ConnectionMySql.connection.prepareStatement("INSERT INTO SplitTransaction VALUES (?, ?, ?, ?, ?);");
            statement.setFloat(1, amount);
            statement.setString(2, participants);
            statement.setDate(3, sqlDate);
            statement.setInt(4, sender_fk);
            statement.setInt(5, receiver_fk);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method creates a StoreOwnerToBankAccount in persistent data via an sql query.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    public void createStoreOwnerToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(dateCreated.getTime());
            PreparedStatement statement = ConnectionMySql.connection.prepareStatement("INSERT INTO StoreOwnerToBankAccount VALUES (?, ?, ?, ?);");
            statement.setFloat(1, amount);
            statement.setDate(2, sqlDate);
            statement.setInt(3, sender_fk);
            statement.setInt(4, receiver_fk);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method creates a UserToBankAccount in persistent data via an sql query.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    public void createUserToBankAccount(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(dateCreated.getTime());
            PreparedStatement statement = ConnectionMySql.connection.prepareStatement("INSERT INTO UserToBankAccount VALUES (?, ?, ?, ?);");
            statement.setFloat(1, amount);
            statement.setDate(2, sqlDate);
            statement.setInt(3, sender_fk);
            statement.setInt(4, receiver_fk);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method creates a UserToUserTransaction in persistent data via an sql query.
     * @param amount
     * @param dateCreated
     * @param sender_fk
     * @param receiver_fk
     */
    public void createUserToUserTransaction(Float amount, Date dateCreated, int sender_fk, int receiver_fk) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(dateCreated.getTime());
            PreparedStatement statement = ConnectionMySql.connection.prepareStatement("INSERT INTO UserToUserTransaction VALUES (?, ?, ?, ?);");
            statement.setFloat(1, amount);
            statement.setDate(2, sqlDate);
            statement.setInt(3, sender_fk);
            statement.setInt(4, receiver_fk);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
