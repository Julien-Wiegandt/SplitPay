package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StoreOwnerHomeController {

    @FXML
    private Label tempLabel;

    @FXML
    private void initialize() {
        tempLabel.setText("Hello store owner"+ UserFacade.getUserFacade().getLoggedStoreOwner().getNickname());
    }
}
