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

    private String realCode;

    @FXML
    void initialize(){
        realCode = SplitUtilities.generateCode();
        System.out.println(realCode);
    }
    /**
     * This method verify if the validation code is correct and update the user
     * @throws IOException
     */
    public void verifyCode() throws IOException {
        System.out.println(code.getText());
        System.out.println(realCode);
        if (code.getText().equals(realCode)) {
            UserFacade.getUserFacade().updateUser(tempUser);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.manageAccountView));
            SplitPay.window.setScene(new Scene(root));

        }
    }

    public User getTempUser() {
        return tempUser;
    }


    public static void setTempUser(User tempUser) {
        VerificationController.tempUser = tempUser;
    }
}