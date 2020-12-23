package ui.controller;

import core.facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.SplitPay;
import ui.path.UserNavigationPath;
import ui.path.StoreOwnerNavigationPath;
import javafx.scene.control.Label;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;

public class HeaderBarController {

    @FXML
    private Label nickName;

    /**
     * This method redirects to the myTransactionsView
     * @param actionEvent
     * @throws IOException
     */
    public void goToHomeView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(UserNavigationPath.homeView));
        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    private void initialize() {
        String display;
        if(UserFacade.getUserFacade().isStoreOwner()) {
            display = "Store owner : ";
        } else {
            display = "User : ";
        }
        nickName.setText(display + UserFacade.getUserFacade().getLoggedUser().getNickname());
    }
}
