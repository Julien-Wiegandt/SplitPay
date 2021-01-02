package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.facade.UserFacade;
import core.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import main.SplitPay;
import ui.path.AuthPath;
import util.SplitUtilities;

public class ChangePasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;



    @FXML
    void initialize() {
        assert password1 != null : "fx:id=\"password1\" was not injected: check your FXML file 'changePasswordView.fxml'.";
        assert password2 != null : "fx:id=\"password2\" was not injected: check your FXML file 'changePasswordView.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'changePasswordView.fxml'.";
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'changePasswordView.fxml'.";

    }
    /**
     * This method change the password
     * @param actionEvent
     * @throws IOException
     */
    public void changePassword(ActionEvent actionEvent) throws IOException {

        if (password1.getText().equals(password2.getText())) {
            String code = SplitUtilities.generateCode();
            User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();
            tempUser.setValidationCode(code);
            tempUser.setPassword(password1.getText());
            VerificationController.setTempUser(tempUser);
            System.out.println(code);

            Parent root = FXMLLoader.load(getClass().getResource(AuthPath.selectMethodView));
            SplitPay.window.setScene(new Scene(root));
        }
    }
}
