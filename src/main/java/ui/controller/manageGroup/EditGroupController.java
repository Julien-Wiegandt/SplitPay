package ui.controller.manageGroup;

import core.facade.GroupFacade;
import core.facade.UserFacade;
import core.models.Group;
import core.models.NormalUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import main.SplitPay;
import ui.path.NormalUserNavigationPath;


import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class EditGroupController {

    @FXML
    private Label label;

    @FXML
    private ListView friends_in_group;

    @FXML private ListView all_my_friends;

    private String id_group;
    private String label_group;



    private NormalUser friend_in_group;
    private NormalUser friend_in_all;

    private Collection friends_group;
    private Collection my_friends;


    @FXML
    private void initialize() {
    }

    public void setGroup(Group selectedItem) {

        this.id_group = selectedItem.getId();
        this.label_group = selectedItem.getLabel();

        //On instancie la collection des amis du groupe
        this.friends_group = GroupFacade.getInstance().getAllFriendFromGroup(id_group);
        ObservableList<NormalUser> items = FXCollections.observableArrayList();

        //On instancie la listView des amis du groupe
        Iterator<NormalUser> iterator = this.friends_group.iterator();
        while (iterator.hasNext()) {
            items.add(iterator.next());
        }
        this.friends_in_group.setItems(items);

        // On instancie le label
        label.setText(selectedItem.getLabel());

        // On instancie la collection de TOUS les amis
        this.my_friends = UserFacade.getUserFacade().getFriends(Integer.parseInt(UserFacade.getUserFacade().getLoggedNormalUser().getId()));

        ObservableList<NormalUser> my_friends_items = FXCollections.observableArrayList();

        //On instancie la listView de Tous les amis
        Iterator<NormalUser> iterator2 = this.my_friends.iterator();
        while (iterator2.hasNext()) {
            my_friends_items.add(iterator2.next());
        }
        this.all_my_friends.setItems(my_friends_items);

    }

    public void deleteGroup() throws IOException {
        GroupFacade.getInstance().deleteGroup(id_group, label_group);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void addFriend()throws IOException{

        if((this.friend_in_all != null)){
            if((!isInCollection(friend_in_all,friends_group))) {
                GroupFacade.getInstance().addFriendToGroup(id_group, label_group, friend_in_all);
                friend_in_all = null;
            }
        }
        refresh();

    }

    private boolean isInCollection(NormalUser friend, Collection collection) {
        boolean present = false;
        for(Object user: collection){
            if(((NormalUser) user).getId() == friend.getId()){
                present = true;
            }
        }
        return present;
    }

    public void goToGroupView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.groupView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void selectFriendInAll(MouseEvent mouseEvent) {
        this.friend_in_all = (NormalUser)all_my_friends.getSelectionModel().getSelectedItem();

    }

    public void selectFriendInGroup(MouseEvent mouseEvent){
        this.friend_in_group = (NormalUser)friends_in_group.getSelectionModel().getSelectedItem();

    }

    public void deleteFriendFromGroup() throws IOException {

        if((this.friend_in_group != null) ) {
            if((!isInCollection(friend_in_group, my_friends))) {
                GroupFacade.getInstance().deleteFriendFromGroup(id_group, label_group, friend_in_group);
                friend_in_group = null;
            }
        }
        refresh();
    }

    private void refresh() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(NormalUserNavigationPath.editGroupView));
        Parent root = loader.load();
        EditGroupController editGroupController = loader.getController();
        editGroupController.setGroup(new Group(this.id_group, this.label_group));
        SplitPay.window.setScene(new Scene(root));
    }


/*
    private boolean isInGroup(NormalUser friend_in_all) {
        for(Object user: friends_group){

        }
    }

 */
}
