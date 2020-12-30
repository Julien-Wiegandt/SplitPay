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
        //launch(args);
        UserFacade user = UserFacade.getUserFacade();
        try {
            user.emailLogIn("test@test.com","splitpay");
        } catch (Exception e) {
            e.printStackTrace();
        }

        GroupFacade ccs =  GroupFacade.getInstance();

        ccs.addGroup("Gucci");
        ArrayList<Group> cars = new ArrayList<Group>();

        cars = ccs.getAllGroup();

        for(Group c: cars){
            System.out.println("group before: " + c.toString());
        }

        ccs.deleteGroup("3", null);

        cars = ccs.getAllGroup();

        for(Group c: cars){
            System.out.println(" group after: " + c.toString());
        }

        System.out.println("On ajoute des users dans le groupe:");
        ccs.addFriendToGroup("4","SplitTeam", new User("3",null,null,null,null,null,null));
        ccs.addFriendToGroup("4","SplitTeam", new User("4",null,null,null,null,null,null));


        System.out.println("les membres du groupes:");
        ArrayList<NormalUser> users = ccs.getAllFriendFromGroup("4");

        for(User u: users){
            System.out.println("user before: "+u.toString());
        }

        System.out.println("On supprime du groupes:");
        ccs.deleteFriendFromGroup("4",null, new User("3",null,null,null,null,null,null));


        System.out.println("Les membres du groupe:");
        users = ccs.getAllFriendFromGroup("4");

        for(User u: users){
            System.out.println("user after: "+u.toString());
        }
    }

}
