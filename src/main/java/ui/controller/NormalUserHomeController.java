package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NormalUserHomeController {

    @FXML
    private Label balance;

    @FXML
    private void initialize() {
        balance.setText("User "+UserFacade.getUserFacade().getLoggedNormalUser().getBalance());
    }

}
