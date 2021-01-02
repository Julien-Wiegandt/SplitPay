package ui.controller.split;

import client.facade.SplitClientFacade;
import core.facade.UserFacade;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import main.SplitPay;
import server.models.split.Participant;
import server.models.split.FreeSplit;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class SplitSaloonController {

    private FreeSplit joinedSplit;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    private void setJoinedSplit(FreeSplit joinedSplit){
        this.joinedSplit=joinedSplit;
    }

    private FreeSplit getJoinedSplit(){
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
    public Label currentAmount;

    @FXML
    public ProgressBar progressBar;

    @FXML
    public Button payButton;

    @FXML
    private void initialize() throws IOException {
        SplitClientFacade.getInstance().setSplitSaloonController(this);
        setJoinedSplit(SplitClientFacade.getInstance().getJoinedSplit());
        updateDisplayedSplit();
    }

    /**
     * Method called when the split's state changes
     */
    public void updateSplit(FreeSplit split){
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
            currentAmount.setText(Double.toString(getJoinedSplit().getCurrentAmount()));
            progressBar.setProgress(getJoinedSplit().getCurrentAmount()/getJoinedSplit().getGoalAmount());
            ObservableList<Participant> items = FXCollections.observableArrayList ();
            items.setAll(getJoinedSplit().getParticipants().values());
            participants.setItems(items);

            // The pay button is only accessible for the split owner
            if(isParticipantOwner()){
                payButton.setVisible(false);
            }
        });

    }

    private boolean isParticipantOwner(){
        return getJoinedSplit().getOwnerId() == Integer.parseInt(UserFacade.getUserFacade().getLoggedUser().getId());
    }

    /**
     * Method called when the participant successfully quit the split
     */
    public void splitQuit(){
        Platform.runLater(() -> goToHomeView());

    }

    /* Methods handling UI action ************ */

    /**
     * Handles participant input, sends to the server the change amount request
     */
    public void moneyInputHandler(){
        double newAmount = Double.parseDouble(moneyInput.getText());
        facade.changeAmount(newAmount,getJoinedSplit().getSplitCode());
    }

    /**
     * Handles participant's ready status change
     */
    public void isReadyHandler(){
        facade.switchReadyStatus(getJoinedSplit().getSplitCode());
    }

    /**
     * Handles participant quit button
     */
    public void quitHandler(){
        facade.quitSplit(getJoinedSplit().getSplitCode());
    }

    /* *************************************** */

    /**
     * Method to move the user to the home screen
     */
    private void goToHomeView(){
        Parent root = null;
        try {
            // TODO : resource correctly
            root = FXMLLoader.load(getClass().getResource("../"+UserNavigationPath.homeView));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SplitPay.window.setScene(new Scene(root));
    }

}
