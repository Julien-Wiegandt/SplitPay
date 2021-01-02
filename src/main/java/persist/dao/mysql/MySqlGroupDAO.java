package persist.dao.mysql;

import core.facade.UserFacade;
import core.models.Group;
import core.models.NormalUser;
import core.models.User;
import persist.dao.GroupDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class MySqlGroupDAO extends GroupDAO {

    public MySqlGroupDAO(){super();}

    @Override
    public void addGroup(String label) {
        Statement stmt = null;

        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("création..");

            Integer rs = stmt.executeUpdate("INSERT INTO FriendGroup VALUES (NULL, '"+label+"', '"+ UserFacade.getUserFacade().getLoggedUser().getId()+"')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteGroup(Group group) {
        Statement stmt = null;

        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            System.out.println("suppression...");

            stmt.executeUpdate("DELETE FROM FriendGroup WHERE friend_group_pk = '" + group.getId() + "'");
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addFriendToGroup(Group group, User friend) {
        Statement stmt = null;

        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("création..");

            Integer rs2 = stmt.executeUpdate("INSERT INTO Relation_FriendGroup_User VALUES ('" +group.getId()+"', '"+friend.getId()+"')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteFriendFromGroup(Group group, User friend) {

        Statement stmt = null;

        try {
            stmt = MySqlDAOFactory.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            System.out.println("suppression...");

            stmt.executeUpdate("DELETE FROM Relation_FriendGroup_User WHERE friend_group_fk = '" + group.getId()+ "' AND normal_user_fk = '"+friend.getId()+"' "  );
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ArrayList<Group> getAllGroup() {
        ArrayList<Group> ccs = new ArrayList<Group>();

        Statement stmt = null;
        Group group;

        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM FriendGroup WHERE normal_user_fk='" + UserFacade.getUserFacade().getLoggedUser().getId() + "'");

            while (rs.next()) {
                String dbId = rs.getString("friend_group_pk");
                String dbLabel = rs.getString("label");

                group = new Group(dbId, dbLabel);

                ccs.add(group);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ccs;
    }

    public ArrayList<NormalUser> getAllFriendFromGroup(String id){
        ArrayList<NormalUser> ccs = new ArrayList<NormalUser>();

        Statement stmt = null;
        NormalUser normalUser;

        try {
            stmt = ConnectionMySql.connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Relation_FriendGroup_User R, NormalUser U WHERE friend_group_fk ='" + id + "' AND R.normal_user_fk = U.normal_user_pk");

            while (rs.next()) {
                String dbId = rs.getString("normal_user_pk");
                String dbEmail = rs.getString("email");
                String dbFirstName = rs.getString("firstName");

                normalUser = new NormalUser(dbFirstName,null,dbId,dbEmail,null,null,null,null,null);

                ccs.add(normalUser);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return ccs;

    }
}
