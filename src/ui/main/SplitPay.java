package ui.main;

import core.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SplitPay extends Application {
    public static Stage window;
    public static User user;


    @Override
    public void start(Stage stage) throws Exception{
        window = stage;

        //Main app
        Image logo = new Image(getClass().getResourceAsStream("../ressources/SplitPayV2XL.png"));
        window.getIcons().add(logo);
        window.setTitle("SplitPay");
        window.centerOnScreen();

        //LogIn
        Parent logInRoot = FXMLLoader.load(getClass().getResource("../views/logInView.fxml"));
        Scene logInView = new Scene(logInRoot, 320.0, 500.0);

        window.setScene(logInView);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
