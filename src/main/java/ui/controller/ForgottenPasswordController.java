package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import ui.path.AuthPath;
import main.SplitPay;

import java.io.IOException;

/**
 * Controller of the forgottenPasswordAmountView called when the a user has forgotten his password.
 *
 * @author Julien Wiegandt
 * @version 1.0
 * @since 2020-12-28
 */
public class ForgottenPasswordController {

    /**
     * The credential entered by the user (expected: e-mail adress or phone).
     */
    @FXML
    private TextField credential;

    /**
     * This method load the logInView.
     * It is called by a button.
     * @throws IOException
     * @todo Handle the possible exceptions.
     */
    public void goToLogInView() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(AuthPath.logInView));
        SplitPay.window.setScene(new Scene(root, 320, 500));
    }

    /**
     * This method sends a link by phone or e-mail (depends on the credential entered) to the current user to reset his password.
     * If the credential doesn't exist or
     * doesn't match any phone|email regex pattern,
     * then credential TextField border is highlighted in red.
     * @todo This method is TODO.
     */
    public void resetPassword() {
        allStyleSetDefault();
        //TODO
    }

    /**
     * This method is used to set all user's input error feedback styles to default.
     */
    private void allStyleSetDefault(){
        credential.setStyle("-fx-text-box-border: black");
    }
}

