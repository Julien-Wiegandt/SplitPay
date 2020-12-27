package main;

import core.facade.CreditCardFacade;
import core.facade.UserFacade;
import core.models.CreditCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        UserFacade user = UserFacade.getUserFacade();
        try {
            user.emailLogIn("test@test.com","splitpay");
        } catch (Exception e) {
            e.printStackTrace();
        }

        CreditCardFacade ccs =  CreditCardFacade.getInstance();

        //ccs.createCreditCard("712", "Ayoub", new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-31"), "708");
        ArrayList<CreditCard> cars = new ArrayList<CreditCard>();

        cars = ccs.getCards();

        for(CreditCard c: cars){
            System.out.println("new man : " + c.toString());
        }

        ccs.deleteCreditCard("712", "Ayoub", new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-31"), "708");

        cars = ccs.getCards();

        for(CreditCard c: cars){
            System.out.println("new man : " + c.toString());
        }
    }

}
