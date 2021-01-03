package ui.controller.split;

import client.facade.SplitClientFacade;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.SplitPay;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class SplitSectionController {

    private SplitClientFacade facade = SplitClientFacade.getInstance();;

    @FXML
    private TextField splitCode;

    @FXML
    private Label splitSectionFlashMessage;

    /**
     * This methods sends the splitCode to the server to attempt joining a split
     */
    public void joinSplit(){
        facade.joinSplit(splitCode.getText());
    }

    /**
     * Method used to store the joined split and redirecting the user to the saloon
     */
    public void splitJoined(){
        Platform.runLater(() -> goToSaloonView());

    }

    /**
     * Method used to redirect the user into the split saloon
     */
    private void goToSaloonView() {
        Parent root = null;
        try {
            // TODO : Handle resource path problem
            root = FXMLLoader.load(getClass().getResource("../"+UserNavigationPath.splitSaloonView));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    public void setFlashMessage(String flashMessage){
        System.out.println("ok");
        Platform.runLater(() -> splitSectionFlashMessage.setText(flashMessage));
    }

    @FXML
    private void initialize() {
        SplitClientFacade.getInstance().setSplitSectionController(this);
    }

}
