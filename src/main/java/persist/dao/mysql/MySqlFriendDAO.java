package persist.dao.mysql;

import core.models.NormalUser;
import core.models.User;
import persist.dao.FriendDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySqlFriendDAO extends FriendDAO {







    //---------------------------------Constructors--------------------------------------

    public MySqlFriendDAO() {
        super();
    }

    private MySqlUserDAO MySqlFriendDao() {
        // TODO implement here
        return null;
    }

    //---------------------------------Methods--------------------------------------

    //public FriendDAO getConnection();

    //public ArrayList<User> getFriends();


    public void addFriend(User user, User friend) {
        Statement stmt = null;
        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("INSERT INTO Friends VALUES ('"+user.getId()+"', '"+friend.getId()+"')");
            Integer rs = stmt.executeUpdate("INSERT INTO Friends VALUES ('"+user.getId()+"', '"+friend.getId()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteFriend(User user, User friend){
        Statement stmt = null;
        PreparedStatement preparedStmt = null;
        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStmt = MySqlDAOFactory.connection.prepareStatement("DELETE FROM Friends WHERE added_normal_user_fk='" + user.getId() + "' AND adder_normal_user_fk='"+ friend.getId() + "'");
            System.out.println("DELETE FROM Friends WHERE added_normal_user_fk='" + user.getId() + "' and adder_normal_user_fk="+ friend.getId() + "'");
            preparedStmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    };


};

