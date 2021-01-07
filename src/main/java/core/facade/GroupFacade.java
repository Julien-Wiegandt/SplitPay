package core.facade;

import core.models.*;
import persist.DAOFactory;
import persist.dao.GroupDAO;
import persist.dao.mysql.MySqlDAOFactory;

import java.util.ArrayList;
import java.util.Collection;

public class GroupFacade {

    private GroupDAO groupDAO;
    private static GroupFacade groupFacade = null;

    private static DAOFactory daoFactory = new MySqlDAOFactory();

    private GroupFacade(){
        this.groupDAO = daoFactory.createGroupDAO();
    }

    public static GroupFacade getInstance(){

        if(groupFacade == null){
            groupFacade = new GroupFacade();
        }

        return groupFacade;
    }


    public void addGroup(String label){

        this.groupDAO.addGroup(label);
    }

    public void deleteGroup(String id, String label){
        Group group = new Group(id, label);
        this.groupDAO.deleteGroup(group);
    }

    public void addFriendToGroup(String id, String label, User user){
        Group group = new Group(id, label);
        this.groupDAO.addFriendToGroup(group, user);
    }

    public void deleteFriendFromGroup(String id, String label, User user){

        Group group = new Group(id, label);
        System.out.println(user.getId());
        this.groupDAO.deleteFriendFromGroup(group, user);
    }

    public ArrayList<Group> getAllGroup(){
        return this.groupDAO.getAllGroup();
    }

    public ArrayList<NormalUser> getAllFriendFromGroup(String id){

        return this.groupDAO.getAllFriendFromGroup(id);
    }
}
