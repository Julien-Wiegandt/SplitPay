package ui.controller;

import client.facade.SplitClientFacade;
import javafx.fxml.FXML;

import java.io.IOException;

public class MySplitController {

//    @FXML
//    private ListView listView;

    public void getSplits() throws IOException {
        SplitClientFacade.getInstance().getSplits();
    }

    @FXML
    private void initialize() throws IOException {
//        ObservableList<Split> items = FXCollections.observableArrayList ();
//
//        Iterator<Split> iterator = splits.iterator();
//        while (iterator.hasNext()) {
//            items.add(iterator.next());
//        }
//        listView.setItems(items);
    }

}
