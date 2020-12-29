package main;

import core.facade.FriendFacade;
import core.facade.NotificationFacade;
import core.facade.UserFacade;
import core.models.NormalUser;
import core.models.Notification;
import core.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class SplitPay extends Application {
    public static Stage window;
    private UserFacade user = UserFacade.getUserFacade();


    @Override
    public void start(Stage stage) throws Exception{
        window = stage;

        //Main app
        Image logo = new Image(getClass().getResourceAsStream("../SplitPayV2XL.png"));
        window.getIcons().add(logo);
        window.setTitle("main");
        window.centerOnScreen();

        //LogIn
        Parent logInRoot = FXMLLoader.load(getClass().getResource("../view/authPath/homeView.fxml"));
        Scene logInView = new Scene(logInRoot);

        window.setScene(logInView);
        window.show();
    }

    public static void main(String[] args) {

        UserFacade user = UserFacade.getUserFacade();
        try {
            user.emailLogIn("test@test.com","splitpay");
        } catch (Exception e) {
            e.printStackTrace();
        }
        FriendFacade friendFacade = FriendFacade.getFriendFacade();
        User  friend = UserFacade.getUserFacade().findUserByPhone("0611223344");
        friendFacade.deleteFriend(friend);
        System.out.println(friendFacade.getFriends().toString());

        launch(args);



    }
}
