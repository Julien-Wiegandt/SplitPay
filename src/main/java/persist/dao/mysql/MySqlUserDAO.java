package persist.dao.mysql;

import persist.dao.UserDAO;
import persist.exception.login.PasswordLoginDAOException;
import core.models.NormalUser;
import core.models.StoreOwner;
import core.models.User;
import util.RegexPattern;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Collection;

/**
 *
 */
public class MySqlUserDAO extends UserDAO {

    /**
     * Default constructor
     */
    public MySqlUserDAO() {
        super();
    }

    /**
     * @return
     */
    private MySqlUserDAO MySqlUserDao() {
        // TODO implement here
        return null;
    }



    public User emailLogIn(String email, String password) throws Exception {
        User user = this.findUserByEmail(email);


        if (password.equals(user.getPassword())) {
            System.out.println("Login success !");
            return user;
        }else{
            throw new PasswordLoginDAOException("Login failed: Wrong Password input !");
        }
    }

    //prendre en compte héritage
    public User phoneLogIn(String phone, String password) throws Exception {
        User user = this.findUserByPhone(phone);

        if (password.equals(user.getPassword())) {
            System.out.println("Login success !");
            return user;

        }else{ throw new PasswordLoginDAOException("Login failed: Wrong Password input !");}
    }

    @Override
    public User findUser(String credential) throws SQLException {
        User user = null;
        if(RegexPattern.emailPattern.matcher(credential).find()){
            user = findUserByEmail(credential);
        }else{
            user = findUserByPhone(credential);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        User user = findNormalUserByEmail(email);
        if(user == null) {
            user = findStoreOwnerByEmail(email);
        }
        return user;

    }

    @Override
    public User findUserByPhone(String phone) {
        User user = findNormalUserByPhone(phone);
        if(user == null){
            user = findStoreOwnerByPhone(phone);
        }
        return user;
    }


    public User findNormalUserByEmail(String email) throws SQLException {
        Statement stmt = null;
        User user = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM NormalUser WHERE email='" + email + "'");

            while (rs.next()) {
                String dbId = rs.getString("normal_user_pk");
                String dbFirstName = rs.getString("firstName");
                String dbLastName = rs.getString("LastName");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                //String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                user = new NormalUser(dbFirstName, dbLastName, dbId, dbEmail, dbPhone, dbPassword, dbNickname, dbBalance, null);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    public User findStoreOwnerByEmail(String email) throws SQLException {
        Statement stmt = null;
        User user = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM StoreOwner WHERE email='" + email + "'");

            while (rs.next()) {
                String dbId = rs.getString("store_owner_pk");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                //String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbSiret = rs.getString("siret");
                String dbPassword = rs.getString("password");
                String dbCompanyName = rs.getString("companyName");
                String dbAddress = rs.getString("address");

                user = new StoreOwner(dbId, dbEmail, dbPhone, dbSiret, dbPassword, dbNickname, dbBalance, null, dbCompanyName, dbAddress);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }


    public User findNormalUserByPhone(String phone) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM NormalUser WHERE phone='" + phone + "'");

            while (rs.next()) {
                String dbId = rs.getString("normal_user_pk");
                String dbFirstName = rs.getString("firstName");
                String dbLastName= rs.getString("lastName");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                //String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                user = new NormalUser(dbFirstName, dbLastName, dbId, dbEmail, dbPhone, dbPassword, dbNickname, dbBalance, null);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }


    public User findStoreOwnerByPhone(String phone) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM StoreOwner WHERE phone='" + phone + "'");

            while (rs.next()) {
                String dbId = rs.getString("store_owner_pk");
                String dbSiret = rs.getString("siret");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                //String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                String dbCompanyName = rs.getString("companyName");
                String dbAddress = rs.getString("address");

                user = new StoreOwner(dbId, dbEmail, dbPhone, dbSiret, dbPassword, dbNickname, dbBalance, null, dbCompanyName, dbAddress);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }


    public User create(User user){
        if( user instanceof NormalUser){
            createNormalUser(((NormalUser)user));
        }else{
            createStoreOwner(((StoreOwner) user));
        }

        return user;
    }



    public User createNormalUser(NormalUser user) {
        Statement stmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("INSERT INTO NormalUser VALUES (NULL, '"+user.getFirstName()+"', '"+user.getLastName()+"','"+user.getEmail()+"', '"+user.getPhone()+"', '"+user.getPassword()+"', '"+user.getNickname()+"', '"+user.getBalance()+"')");
            Integer rs = stmt.executeUpdate("INSERT INTO NormalUser VALUES (NULL, '"+user.getFirstName()+"', '"+user.getLastName()+"','"+user.getEmail()+"', '"+user.getPhone()+"', '"+user.getPassword()+"', '"+user.getNickname()+"', '"+user.getBalance()+"')");
//            MySqlDaoFactory.connection.commit();
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    public User createStoreOwner(StoreOwner user) {
        Statement stmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Integer rs = stmt.executeUpdate("INSERT INTO StoreOwner VALUES (NULL, '"+user.getEmail()+"','"+user.getPhone()+"', '"+user.getNickname()+"', '"+user.getPassword()+"', '"+user.getBalance()+"', '"+user.getCompanyName()+"','"+user.getAddress()+"','"+user.getSiret()+")");
            System.out.println("good job");

            //            MySqlDaoFactory.connection.commit();
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean delete(User user) {
        Statement stmt = null;
        PreparedStatement preparedStmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(user instanceof NormalUser) {
                preparedStmt = ConnectionMySql.connection.prepareStatement("DELETE NormalUser WHERE id='" + user.getId() + "'");
            }else{
                preparedStmt = ConnectionMySql.connection.prepareStatement("DELETE StoreOwner WHERE id='" + user.getId() + "'");
            }
            preparedStmt.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean update(User user) {
        Statement stmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(user instanceof NormalUser) {
                Integer rs = stmt.executeUpdate("UPDATE NormalUser SET firstName='"+((NormalUser) user).getFirstName()+"','"+((NormalUser) user).getLastName()+"', email='" + user.getEmail() + "', phone='" + user.getPhone() + "'," +
                        " password='" + user.getPassword() + "', nickname='" + user.getNickname() + "', balance='" + user.getBalance() + "'" +
                        "WHERE id='" + user.getId() + "'");
            }else{
                Integer rs = stmt.executeUpdate("UPDATE StoreOwner SET email='" + user.getEmail() + "', phone='" + user.getPhone() + "'," +
                        " password='" + user.getPassword() + "', nickname='" + user.getNickname() + "','"+((StoreOwner)user).getCompanyName()+"','"+((StoreOwner)user).getAddress()+"', balance='" + user.getBalance() + "'" +
                        "WHERE id='" + user.getId() + "'");

            }
            ConnectionMySql.connection.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    public User findUserById(int id) throws SQLException {


        User user = findNormalUserById(id);
        if(user == null) {
            user = findStoreOwnerById(id);
        }
        return user;
    }

    public User findNormalUserById(int id) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM NormalUser WHERE id='" + id + "'");

            while (rs.next()) {
                String dbId = rs.getString("normal_user_pk");
                String dbFirstName = rs.getString("firstName");
                String dbLastName = rs.getString("LastName");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                //String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                user = new NormalUser(dbFirstName, dbLastName, dbId, dbEmail, dbPhone, dbPassword, dbNickname, dbBalance, null);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    public User findStoreOwnerById(int id) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM StoreOwner WHERE id='" + id + "'");

            while (rs.next()) {
                String dbId = rs.getString("store_owner_pk");
                String dbSiret = rs.getString("siret");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                //String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                String dbCompanyName = rs.getString("companyName");
                String dbAddress = rs.getString("address");

                user = new StoreOwner(dbId, dbEmail, dbPhone, dbSiret, dbPassword, dbNickname, dbBalance, null, dbCompanyName, dbAddress);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    //prendre en compte héritage
    @Override
    public void error() {

    }

    //prendre en compte héritage
    @Override
    public Collection<User> findAll() {
        return null;
    }

}