package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.text.ParseException;


public class SplitPay extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws Exception{
        window = stage;

        //Main app
        Image logo = new Image(getClass().getResourceAsStream("../SplitPayV2XL.png"));
        window.getIcons().add(logo);
        window.setTitle("main");
        window.centerOnScreen();

        //LogIn
        Parent logInRoot = FXMLLoader.load(getClass().getResource("../view/authPath/loginView.fxml"));
        Scene logInView = new Scene(logInRoot);

        window.setScene(logInView);
        window.show();
    }

    public static void main(String[] args) throws ParseException {
        launch(args);
    }

}
