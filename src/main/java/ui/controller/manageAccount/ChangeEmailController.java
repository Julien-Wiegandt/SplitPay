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
import util.RegexPattern;

import java.io.IOException;

public class ChangeEmailController {




    @FXML
    private TextField email1;
    @FXML
    private TextField email2;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;



    private String code;


    @FXML
    void initialize() {



    }

    /**
     * This method changes the user's email
     * @throws IOException
     */
    public void changeEmail() throws IOException {
        if (RegexPattern.emailPattern.matcher(email1.getText()).find() && RegexPattern.emailPattern.matcher(email2.getText()).find() ) {
            User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();
            tempUser.setEmail(email1.getText());
            VerificationController.setTempUser(tempUser);
            /*
            try {
                Mail.sendEmail(email1.getText(),code);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            */

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.verificationView));
            SplitPay.window.setScene(new Scene(root));



        }
        else {
            email1.setStyle("-fx-text-box-border: red");
            email2.setStyle("-fx-text-box-border: red");
        }


    }


}
