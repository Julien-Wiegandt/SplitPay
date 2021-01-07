package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.NormalUser;
import core.models.User;
import persist.dao.FriendDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public ArrayList<NormalUser> getFriends(String id) {
        Statement stmt = null;
        ArrayList<NormalUser> friends=new ArrayList<NormalUser>();
        System.out.println("GETFRIENDSSSSSSSSSSSSSSSSSSSSSSS");

        try {
            stmt = ConnectionMySql.connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From Friends JOIN NormalUser On NormalUser.normal_user_pk =  Friends.added_normal_user_fk  WHERE adder_normal_user_fk ="+"'"+ id +"'");
            System.out.println("Select * From Friends JOIN NormalUser On NormalUser.normal_user_pk =  Friends.added_normal_user_fk  WHERE adder_normal_user_fk ="+"'"+ id +"'");
            while(rs.next()) {
                NormalUser user = new NormalUser();
                user.setId(rs.getString("normal_user_pk"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setNickname(rs.getString("nickname"));
                System.out.println(user);
                friends.add(user);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return friends;
    }

    public void addFriend(User user, User friend) {
        Statement stmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("INSERT INTO Friends VALUES ('"+friend.getId()+"', '"+user.getId()+"')");
            Integer rs = stmt.executeUpdate("INSERT INTO Friends VALUES ('"+friend.getId()+"', '"+user.getId()+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteFriend(User user, User friend){
        Statement stmt = null;
        PreparedStatement preparedStmt = null;
        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            preparedStmt = ConnectionMySql.connection.prepareStatement("DELETE FROM Friends WHERE adder_normal_user_fk='" + user.getId() + "' AND added_normal_user_fk='"+ friend.getId() + "'");
            System.out.println("DELETE FROM Friends WHERE adder_normal_user_fk='" + user.getId() + "' and added_normal_user_fk="+ friend.getId() + "'");
            preparedStmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    };


};

