package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeController {

    @FXML
    private Label balance;

    @FXML
    private VBox joinSplitSection;

    @FXML
    private void initialize() {
        balance.setText(UserFacade.getUserFacade().getLoggedUser().getBalance().toString());
        if(UserFacade.getUserFacade().isStoreOwner()){
            joinSplitSection.setVisible(false);
            joinSplitSection.setManaged(false);
        }
    }

}
