package main;



import core.facade.GroupFacade;
import core.facade.UserFacade;

import core.models.Group;
import core.models.NormalUser;
import core.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.ParseException;

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
        Parent logInRoot = FXMLLoader.load(getClass().getResource("../view/authPath/logInView.fxml"));
        Scene logInView = new Scene(logInRoot, 320.0, 500.0);

        window.setScene(logInView);
        window.show();
    }

    public static void main(String[] args) throws ParseException {
        launch(args);

    }



}
