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
import server.models.split.FreeSplit;
import server.models.split.Participant;
import ui.path.NormalUserNavigationPath;
import ui.path.UserNavigationPath;
import util.RegexPattern;

import java.io.IOException;

public class FreeSplitSaloonController {

    private final SplitClientFacade facade = SplitClientFacade.getInstance();
    @FXML
    public Label currentAmount;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Button payButton;
    private FreeSplit joinedSplit;
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

    private FreeSplit getJoinedSplit() {
        return joinedSplit;
    }

    private void setJoinedSplit(FreeSplit joinedSplit) {
        this.joinedSplit = joinedSplit;
    }

    @FXML
    private void initialize() throws IOException {
        SplitClientFacade.getInstance().setSplitSaloonController(this);
        setJoinedSplit((FreeSplit) SplitClientFacade.getInstance().getJoinedSplit());
        updateDisplayedSplit();
    }

    /**
     * Method called when the split's state changes
     *
     * @param split
     */
    public void updateSplit(FreeSplit split) {
        setJoinedSplit(split);
        updateDisplayedSplit();
    }

    /**
     * Displays information about the joined split's current state
     */
    public void updateDisplayedSplit() {

        Platform.runLater(() -> {
            splitLabel.setText(getJoinedSplit().getLabel());
            splitMode.setText(getJoinedSplit().getSplitMode().toString());
            splitCode.setText(getJoinedSplit().getSplitCode());
            goalAmount.setText(Double.toString(getJoinedSplit().getGoalAmount()));
            currentAmount.setText(Double.toString(getJoinedSplit().getCurrentAmount()));
            progressBar.setProgress(getJoinedSplit().getCurrentAmount() / getJoinedSplit().getGoalAmount());
            ObservableList<Participant> items = FXCollections.observableArrayList();
            items.setAll(getJoinedSplit().getParticipants().values());
            participants.setItems(items);

            // Pay button disabled if split not ready for payment
            payButton.setDisable(!getJoinedSplit().isReadyForPayment());

            // The pay button is only accessible for the split owner
            if (!isParticipantAdmin()) {
                payButton.setVisible(false);
            }
        });

    }

    private boolean isParticipantAdmin() {
        return getJoinedSplit().getSplitAdmin() == Integer.parseInt(UserFacade.getUserFacade().getLoggedUser().getId());
    }

    /**
     * Method called when the participant successfully quit the split
     */
    public void splitQuit() {
        Platform.runLater(() -> goToHomeView());

    }

    /**
     * Method to redirect the user to the payment success view
     */
    public void splitPaid() {
        Platform.runLater(() -> goToPaymentSuccessView());
    }

    /* Methods handling UI action ************ */

    /**
     * Handles participant input, checks the input, sends to the server the change amount request
     */
    public void moneyInputHandler() {
        if (RegexPattern.decimalPattern.matcher(moneyInput.getText()).find()) {
            double newAmount = Double.parseDouble(moneyInput.getText());
            facade.changeAmount(newAmount, getJoinedSplit().getSplitCode());
            flashMessage.setText("");
        } else {
            flashMessage.setText("Invalid amount");
        }
    }

    /**
     * Handles participant's ready status change
     */
    public void isReadyHandler() {
        facade.switchReadyStatus(getJoinedSplit().getSplitCode());
    }

    /**
     * Handles owner pay request
     */
    public void payButtonHandler() {
        try {
            facade.paySplit(getJoinedSplit().getSplitCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles participant quit button
     */
    public void quitHandler() {
        facade.quitSplit(getJoinedSplit().getSplitCode());
    }

    /* *************************************** */

    /**
     * Method to move the user to the home screen
     */
    private void goToHomeView() {
        Parent root = null;
        try {
            // TODO : resource correctly
            root = FXMLLoader.load(getClass().getClassLoader().getResource(UserNavigationPath.homeView));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SplitPay.window.setScene(new Scene(root));
    }

    /**
     * Redirects the user to the payment success view
     */
    private void goToPaymentSuccessView() {
        Parent root = null;
        try {
            // TODO : Handle resource path problem
            root = FXMLLoader.load(getClass().getClassLoader().getResource(NormalUserNavigationPath.paymentSuccessView));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SplitPay.window.setScene(new Scene(root));
    }

}
