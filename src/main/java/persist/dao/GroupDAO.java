package persist.dao;

import core.models.Group;
import core.models.NormalUser;
import core.models.User;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class GroupDAO {


    public Connection getConnection(){
        return null;
    }
    public abstract void addGroup(String label);
    public abstract void deleteGroup(Group group);
    public abstract void addFriendToGroup(Group group, User friend);
    public abstract void  deleteFriendFromGroup(Group group, User friend);


    public abstract ArrayList<Group> getAllGroup();
    public abstract ArrayList<NormalUser> getAllFriendFromGroup(String id);
}
