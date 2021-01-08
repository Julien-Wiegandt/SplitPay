package ui.controller.manage;

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

public class DeleteAccountController {
    @FXML
    private TextField code;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    private static User tempUser;


    @FXML
    void initialize() {

    }

    public void deleteAccount(ActionEvent actionEvent) throws IOException {
        if (code.getText().equals(tempUser.getValidationCode())) {
            UserFacade.deleteAccount();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.logInView));
            SplitPay.window.setScene(new Scene(root));
        }
    }


    public User getTempUser() {
        return tempUser;
    }


    public static void setTempUser(User tempUser) {
        DeleteAccountController.tempUser = tempUser;
    }
}
