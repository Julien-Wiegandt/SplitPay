package ui.controller.manageFriend;

import core.facade.FriendFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;
import util.RegexPattern;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddFriendController {

    @FXML
    private TextField credentials;

    /**
     * This method redirects to the friendView
     * @throws IOException
     */
    public void goToFriendView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.friendView));
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * This method add a friend to the logged user
     * @throws IOException
     */
    public void addFriend() throws IOException {
        try {
            if(RegexPattern.emailPattern.matcher(credentials.getText()).find()) {
                FriendFacade.getFriendFacade().addFriendByEmail(credentials.getText());
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.friendView));
                SplitPay.window.setScene(new Scene(root));
            }
            else if (RegexPattern.phonePattern.matcher(credentials.getText()).find()){
                FriendFacade.getFriendFacade().addFriendByPhone(credentials.getText());
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.friendView));
                SplitPay.window.setScene(new Scene(root));
            }
            else{
                credentials.setStyle("-fx-text-box-border: red");
            }
        } catch (SQLException throwables) {
            credentials.setStyle("-fx-text-box-border: red");
        }
    }

}
