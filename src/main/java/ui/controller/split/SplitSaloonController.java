package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.UserFacade;
import core.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SplitSaloonController {

    @FXML
    private TextField joinedSplit;

    @FXML
    private Label flashMessage;

    @FXML
    // TODO : Find another way to initialize to avoid getting splits for every view using this controller
    private void initialize() throws IOException {
        SplitClientFacade.getInstance().setSplitSaloonController(this);
        joinedSplit.setText(SplitClientFacade.getInstance().getJoinedSplit().toString());
    }

}
