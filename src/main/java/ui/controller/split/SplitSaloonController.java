package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.UserFacade;
import core.models.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import server.models.Participant;
import server.models.Split;

import java.io.IOException;

public class SplitSaloonController {

    private Split joinedSplit;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    private void setJoinedSplit(Split joinedSplit){
        this.joinedSplit=joinedSplit;
    }

    private Split getJoinedSplit(){
        return joinedSplit;
    }

    @FXML
    private Label flashMessage;

    @FXML
    private Label splitLabel;

    @FXML
    private Label splitMode;

    @FXML
    private Label splitCode;

    @FXML
    private Label goalAmount;

    @FXML
    private ListView participants;

    @FXML
    private TextField moneyInput;

    @FXML
    private void initialize() throws IOException {
        SplitClientFacade.getInstance().setSplitSaloonController(this);
        setJoinedSplit(SplitClientFacade.getInstance().getJoinedSplit());
        updateDisplayedSplit();
    }

    /**
     * Method called when the split's state changes
     */
    public void updateSplit(Split split){
        setJoinedSplit(split);
        updateDisplayedSplit();
    }

    /**
     * Displays information about the joined split's current state
     */
    public void updateDisplayedSplit(){

        Platform.runLater(() -> {
            splitLabel.setText(getJoinedSplit().getLabel());
            splitMode.setText(getJoinedSplit().getSplitMode());
            splitCode.setText(getJoinedSplit().getSplitCode());
            goalAmount.setText(Double.toString(getJoinedSplit().getGoalAmount()));

            ObservableList<Participant> items = FXCollections.observableArrayList ();
            items.setAll(getJoinedSplit().getParticipants().values());
            participants.setItems(items);
        });



    }

    /**
     * Handles participant input, sends to the server the change amount request
     */
    public void moneyInputHandler(){
        Double newAmount = Double.parseDouble(moneyInput.getText());
        facade.changeAmount(newAmount,getJoinedSplit().getSplitCode());
    }

}
