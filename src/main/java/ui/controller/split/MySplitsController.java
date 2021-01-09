package ui.controller.split;

import client.facade.SplitClientFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import server.models.split.Split;

import java.io.IOException;
import java.util.HashMap;

public class MySplitsController {

    private final SplitClientFacade facade = SplitClientFacade.getInstance();
    private HashMap<String, Split> splits = null;
    @FXML
    private ListView<Split> listView;

    @FXML
    private Label noSplitsLabel;

    public void getSplits() throws IOException {
        facade.getSplits();
    }

    public void setSplits(HashMap<String, Split> splits) {
        this.splits = splits;
        ObservableList<Split> items = FXCollections.observableArrayList();
        items.setAll(splits.values());
        listView.setItems(items);
        updateMySplitsView();
    }

    /**
     * Displays according to current data
     */
    public void updateMySplitsView() {
        if (splits.size() == 0) {
            listView.setManaged(false);
        } else {
            noSplitsLabel.setManaged(false);
        }
    }

    @FXML
    private void initialize() throws IOException {
        SplitClientFacade.getInstance().setMySplitsController(this);
        getSplits();
    }

}
