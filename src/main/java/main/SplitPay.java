package main;
import core.models.Bill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.path.AuthPath;

import java.text.ParseException;


public class SplitPay extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws Exception{
        Bill bill = new Bill("Splitlabel","coffee:2.00/vanilla ice-cream:3.99/pizza margarita:5.90/hot chocolate muffin:3.99/truffle pizza:18.99/vegetarian pizza:13.99/");
        System.out.println(bill.toString());
        window = stage;

        //Main app
        Image logo = new Image(getClass().getResourceAsStream("../SplitPayV2XL.png"));
        window.getIcons().add(logo);
        window.setTitle("SplitPay");
        window.setResizable(false);
        window.centerOnScreen();

        //LogIn
        Parent logInRoot = FXMLLoader.load(getClass().getClassLoader().getResource(AuthPath.logInView));
        Scene logInView = new Scene(logInRoot);

        window.setScene(logInView);
        window.show();
    }

    public static void main(String[] args) throws ParseException {
        launch(args);
    }

}
