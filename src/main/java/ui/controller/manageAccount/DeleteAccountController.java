package ui.controller.manageAccount;

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
import util.SplitUtilities;

import java.io.IOException;

public class DeleteAccountController {
    @FXML
    private TextField code;

    private static User tempUser;

    private String realCode;

    /**
     * This method redirects to the selectMethodView
     * @throws IOException
     */
    public void goToManageAccountView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    void initialize() {
        realCode = SplitUtilities.generateCode();
        System.out.println(realCode);
    }

    public void deleteAccount() throws IOException {
        if (code.getText().equals(realCode)) {
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
