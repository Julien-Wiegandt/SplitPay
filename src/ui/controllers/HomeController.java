package ui.controllers;

import core.UserFaçade;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.awt.*;

public class HomeController {

    @FXML
    private Label tempLabel;

    @FXML
    private void initialize() {
        tempLabel.setText("Hello "+UserFaçade.getUserFaçade().getUser().getNickname());
    }

}
