package ui.controller;

import core.facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label tempLabel;

    @FXML
    private void initialize() {
        tempLabel.setText("Hello "+UserFacade.getUserFacade().getUser().getNickname());
    }

}
