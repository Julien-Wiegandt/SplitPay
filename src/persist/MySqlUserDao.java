package persist;

import persist.exception.EmailLoginDAOException;
import persist.exception.PasswordLoginDAOException;
import persist.exception.PhoneLoginDAOException;
import persist.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Collection;

/**
 * 
 */
public class MySqlUserDao extends UserDaoImpl {

    /**
     * Default constructor
     */
    public MySqlUserDao() {
        super();
    }

    /**
     * @return
     */
    private MySqlUserDao MySqlUserDao() {
        // TODO implement here
        return null;
    }

    @Override
    public void login(String credential, String password) {

    }

    public User emailLogIn(String email, String password) throws Exception {
        User user = this.findUserByEmail(email);

        if (email.equalsIgnoreCase(user.getEmail())){
            if (password.equals(user.getPassword())) {
                System.out.println("Login success !");
                return user;

            }else{ throw new PasswordLoginDAOException("Login failed: Wrong Password input !");}

        }else{ throw new EmailLoginDAOException("Login failed: Wrong Email input !"); }


    }

    public User phoneLogIn(String phone, String password) throws Exception {
        User user = this.findUserByPhone(phone);

        if (phone.equalsIgnoreCase(user.getEmail())){
            if (password.equals(user.getPassword())) {
                System.out.println("Login success !");
                return user;

            }else{ throw new PasswordLoginDAOException("Login failed: Wrong Password input !");}

        }else{ throw new PhoneLoginDAOException("Login failed: Wrong phone input !"); }

    }

    @Override
    public User findUser(String credential) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        Statement stmt = null;
        User user = null;
        try {
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE email='" + email + "'");

            while (rs.next()) {
                String dbId = rs.getString("id");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                user = new User(dbId, dbEmail, dbPhone, dbPassword, dbNickname, dbBalance, dbValidationCode);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByPhone(String phone) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE phone='" + phone + "'");

            while (rs.next()) {
                String dbId = rs.getString("id");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                user = new User(dbId, dbEmail, dbPhone, dbPassword, dbNickname, dbBalance, dbValidationCode);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User setValidationCode(int id) {
        return null;
    }


    @Override
    public User create(User user) {
        Statement stmt = null;
        try {
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Integer rs = stmt.executeUpdate("INSERT INTO User VALUES ('"+user.getEmail()+"', '"+user.getPhone()+"', '"+user.getPassword()+"', '"+user.getNickname()+"', '"+user.getBalance()+"')");
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(User user) {
        Statement stmt = null;
        try {
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            PreparedStatement preparedStmt = MySqlDaoFactory.connection.prepareStatement("DELETE User WHERE id='"+user.getId()+"'");
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
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Integer rs = stmt.executeUpdate("UPDATE User SET email='"+user.getEmail()+"', phone='"+user.getPhone()+"'," +
                    " password='"+user.getPassword()+"', nickname='"+user.getNickname() +"', balance='"+user.getBalance()+"'" +
                    "WHERE id='"+user.getId()+"'");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(int id) {
        Statement stmt = null;
        User user = null;
        try {
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE phone='" + id + "'");

            while (rs.next()) {
                String dbId = rs.getString("id");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                user = new User(dbId, dbEmail, dbPhone, dbPassword, dbNickname, dbBalance, dbValidationCode);
                System.out.println(user);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void error() {

    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

}