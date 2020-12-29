package ui.controller;

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
import utilities.RegexPattern;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddFriendController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TextField credentials;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;



    @FXML
    void initialize() {


    }

    /**
     * This method add a friend to the logged user
     * @param actionEvent
     * @throws IOException
     */
    public void addFriend(ActionEvent actionEvent) throws IOException {
        if(RegexPattern.emailPattern.matcher(credentials.getText()).find()) {
            try {
                FriendFacade.getFriendFacade().addFriendByEmail(credentials.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else if (RegexPattern.phonePattern.matcher(credentials.getText()).find()){
            FriendFacade.getFriendFacade().addFriendByPhone(credentials.getText());

        }
        else {
            credentials.setStyle("-fx-text-box-border: red");
        }


        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.friendView));
        SplitPay.window.setScene(new Scene(root));
    }

}
