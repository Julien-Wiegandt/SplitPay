package persist;

import persist.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public User emailLogin(String email, String password){
        Statement stmt = null;
        try {
            stmt = MySqlDaoFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE email='"+email+"'");
            while (rs.next()) {
                String dbId = rs.getString("id");
                String dbPhone = rs.getString("phone");
                String dbNickname = rs.getString("nickname");
                Float dbBalance = rs.getFloat("balance");
                String dbValidationCode = rs.getString("validationCode");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                User user = new User(dbId,dbEmail,dbPhone,dbPassword,dbNickname,dbBalance,dbValidationCode);
                System.out.println(user);
                if(email.equalsIgnoreCase(dbEmail) && password.equalsIgnoreCase(dbPassword)){
                    System.out.println("Login success");
                } else {
                    System.out.println("Login failed");
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}