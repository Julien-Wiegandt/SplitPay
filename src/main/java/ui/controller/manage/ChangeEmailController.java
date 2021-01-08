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
import util.RegexPattern;
import util.SplitUtilities;

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
     * @param actionEvent
     * @throws IOException
     */
    public void changeEmail(ActionEvent actionEvent) throws IOException {
        if (RegexPattern.emailPattern.matcher(email1.getText()).find() && RegexPattern.emailPattern.matcher(email2.getText()).find() ) {
            code = SplitUtilities.generateCode();
            User tempUser = UserFacade.getUserFacade().getLoggedNormalUser();
            tempUser.setValidationCode(code);
            tempUser.setEmail(email1.getText());
            VerificationController.setTempUser(tempUser);
            /*
            try {
                Mail.sendEmail(email1.getText(),code);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            */
            System.out.println(code);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.verificationView));
            SplitPay.window.setScene(new Scene(root));



        }
        else {
            email1.setStyle("-fx-text-box-border: red");
            email2.setStyle("-fx-text-box-border: red");
        }


    }


}
