package ui.controller;

import core.facade.UserFacade;
import core.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.AuthPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerificationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField code;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    private static User tempUser;

    @FXML
    void initialize(){


    }
    /**
     * This method verify if the validation code is correct and update the user
     * @param actionEvent
     * @throws IOException
     */
    public void verifyCode(ActionEvent actionEvent) throws IOException {
        System.out.println(code.getText());
        System.out.println(getTempUser().getValidationCode());
        if (code.getText().equals(tempUser.getValidationCode())) {
            UserFacade.getUserFacade().updateUser(tempUser);
            Parent root = FXMLLoader.load(getClass().getResource(AuthPath.manageAccountView));
            SplitPay.window.setScene(new Scene(root));
            //Parent root = FXMLLoader.load(getClass().getResource(AuthPath.changePasswordView));
            //SplitPay.window.setScene(new Scene(root, 320, 500));
        }
    }


    public User getTempUser() {
        return tempUser;
    }


    public static void setTempUser(User tempUser) {
        VerificationController.tempUser = tempUser;
    }
}