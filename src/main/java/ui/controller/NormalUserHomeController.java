package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NormalUserHomeController {

    @FXML
    private Label tempLabel;

    @FXML
    private void initialize() {
        tempLabel.setText("Hello normal user "+UserFacade.getUserFacade().getLoggedNormalUser().getNickname());
    }

}
