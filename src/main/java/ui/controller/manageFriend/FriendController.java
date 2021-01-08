package ui.controller.manageFriend;

import core.facade.FriendFacade;
import core.models.NormalUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.SplitPay;
import ui.path.AuthPath;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<NormalUser> myFriends;

    @FXML
    private Button addFriend;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;



    @FXML
    void initialize() {


        ObservableList<NormalUser> friends = FXCollections.observableArrayList (FriendFacade.getFriendFacade().getFriends());
        myFriends.setItems(friends);

    }

    /**
     * This method redirects to the addFriendView
     * @throws IOException
     */
    public void goToAddFriendView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.addFriendView));
        SplitPay.window.setScene(new Scene(root));
    }

    public void deleteFriend() throws IOException {
        ObservableList<NormalUser> friends =  myFriends.getSelectionModel().getSelectedItems();
        for (NormalUser user : friends) {
            FriendFacade.getFriendFacade().deleteFriend(user);
        }
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.friendView));
        SplitPay.window.setScene(new Scene(root));
    }



}
