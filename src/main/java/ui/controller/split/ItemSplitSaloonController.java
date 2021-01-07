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
import server.models.split.*;
import ui.path.UserNavigationPath;

import java.io.IOException;

public class ItemSplitSaloonController {

    private ItemSplit joinedSplit;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    private void setJoinedSplit(ItemSplit joinedSplit){
        this.joinedSplit=joinedSplit;
    }

    private ItemSplit getJoinedSplit(){
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
    private Label currentAmount;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button payButton;

    @FXML
    private ListView splitItems;

    @FXML
    private ListView participantsCart;


    @FXML
    private void initialize() throws IOException {
        SplitClientFacade.getInstance().setItemSplitSaloonController(this);
        setJoinedSplit((ItemSplit) SplitClientFacade.getInstance().getJoinedSplit());
        updateDisplayedSplit();
    }

    /**
     * Method called when the split's state changes
     * @param split
     */
    public void updateSplit(ItemSplit split){
        setJoinedSplit(split);
        updateDisplayedSplit();
    }

    /**
     * Displays information about the joined split's current state
     */
    public void updateDisplayedSplit(){

        Platform.runLater(() -> {
            splitLabel.setText(getJoinedSplit().getLabel());
            splitMode.setText(getJoinedSplit().getSplitMode().toString());
            splitCode.setText(getJoinedSplit().getSplitCode());
            goalAmount.setText(Double.toString(getJoinedSplit().getGoalAmount()));
            currentAmount.setText(Double.toString(getJoinedSplit().getCurrentAmount()));
            progressBar.setProgress(getJoinedSplit().getCurrentAmount()/getJoinedSplit().getGoalAmount());
            ObservableList<Participant> items = FXCollections.observableArrayList ();
            items.setAll(getJoinedSplit().getParticipants().values());
            participants.setItems(items);

            ObservableList<Item> itemsElement = FXCollections.observableArrayList ();
            itemsElement.setAll(getJoinedSplit().getItems());
            splitItems.setItems(itemsElement);

            ObservableList<String> cartElement = FXCollections.observableArrayList ();

            cartElement.add(getJoinedSplit().getParticipantsCart().toString());
            System.out.println(getJoinedSplit().getParticipantsCart().toString());
            participantsCart.setItems(cartElement);

            // Pay button disabled if split not ready for payment
            if(getJoinedSplit().isReadyForPayment()){
                payButton.setDisable(false);
            } else {
                payButton.setDisable(true);
            }

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
     * Sends to the server a request to pick the selected item and put it in the user's cart
     */
    public void pickItem(){
        int itemId = splitItems.getSelectionModel().getSelectedIndex();
        int userId = Integer.parseInt(UserFacade.getUserFacade().getLoggedUser().getId());
        Item itemclicked = (Item) splitItems.getSelectionModel().getSelectedItem();
        if(itemclicked.isPicked()){
            if(getJoinedSplit().getParticipantCart(userId).contains(itemclicked)){
                facade.removeItem(itemId,getJoinedSplit().getSplitCode());
            }
        } else {
            facade.pickItem(itemId,getJoinedSplit().getSplitCode());
        }
    }

    /**
     * Handles participant's ready status change
     */
    public void isReadyHandler(){
        facade.switchReadyStatus(getJoinedSplit().getSplitCode());
    }

    /**
     * Handles owner pay request
     */
    public void payButtonHandler(){
        facade.paySplit(getJoinedSplit().getSplitCode());
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