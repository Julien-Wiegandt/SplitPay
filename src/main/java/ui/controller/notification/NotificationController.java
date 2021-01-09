package ui.controller.notification;

import core.facade.NotificationFacade;
import core.models.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class NotificationController {

    @FXML
    private ListView<Notification> myNotifications;

    @FXML
    void initialize() {

        ObservableList<Notification> notifications = FXCollections.observableArrayList(NotificationFacade.getNotificationFacade().getNotifications());
        myNotifications.setItems(notifications);


    }


}
