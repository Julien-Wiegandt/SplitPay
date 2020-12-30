package ui.controller.split;

import client.facade.SplitClientFacade;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.SplitPay;
import server.models.Split;
import ui.path.UserNavigationPath;

import java.io.IOException;
import java.util.HashMap;

public class SplitController {

    private SplitController instance;

    private SplitController(){ }

    public SplitController getInstance(){
        if(instance==null){
            instance = new SplitController();
        }

        return instance;
    }

    ///// mySplitView.fxml logic

    private HashMap<String, Split> splits = null;

    private SplitClientFacade facade = SplitClientFacade.getInstance();

    @FXML
    private ListView<Split> listView;

    @FXML
    private Label noSplitsLabel;

    public void getSplits() throws IOException {
        facade.getSplits();
    }

    public void setSplits(HashMap<String,Split> splits){
        this.splits=splits;
        ObservableList<Split> items = FXCollections.observableArrayList ();
        items.setAll(splits.values());
        listView.setItems(items);
        updateMySplitsView();
    }

    /**
     * Displays according to current data
     */
    public void updateMySplitsView(){
        if(splits.size()==0){
            listView.setManaged(false);
        } else {
            noSplitsLabel.setManaged(false);
        }
    }

    @FXML
    // TODO : Find another way to initialize to avoid getting splits for every view using this controller
    private void initialize() throws IOException {
        getSplits();
    }

    ///// splitSaloonView.fxml logic

    @FXML
    private Label displayedSplit = new Label();

    /// splitSection.fxml logic

    private Split joinedSplit;

    @FXML
    private TextField splitCode;

    @FXML
    private Label splitSectionFlashMessage;

    private Split getJoinedSplit(){
        return joinedSplit;
    }

    /**
     * This methods sends the splitCode to the server to attempt joining a split
     */
    public void joinSplit(){
        facade.joinSplit(splitCode.getText());
    }

    /**
     * Method used to store the joined split and redirecting the user to the saloon
     * @param split
     */
    public void splitJoined(final Split split){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setJoinedSplit(split);
                goToSaloonView();
                updateDisplayedSplit();
            }
        });

    }

    /**
     * Updates the displayed split in the saloon
     */
    public void updateDisplayedSplit(){

        System.out.println("getJoinedSplit : " +getJoinedSplit());
        displayedSplit.setText(getJoinedSplit().toString());
    }

    /**
     * Method used to store the joined split
     */
    private void setJoinedSplit(Split split){
        this.joinedSplit=split;
    }

    /**
     * Method used to redirect the user into the split saloon
     */
    private void goToSaloonView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(UserNavigationPath.splitSaloonView));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SplitPay.window.setScene(new Scene(root));
    }

    @FXML
    public void setSplitSectionFlashMessage(final String flashMessage){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                splitSectionFlashMessage.setText(flashMessage);
            }
        });
    }


}
