package client.facade;

import client.ObservableClient;
import core.facade.UserFacade;
import server.SplitOriginatorMessage;
import server.facade.SplitServerFacade;
import ui.controller.SplitController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class SplitClientFacade implements Observer
{
    // Class variables ***************************************************

    /**
     * Indicates a close of the connection to server.
     */
    public static final String CONNECTION_CLOSED = "#OC:Connection closed.";

    /**
     * Indicates establishment of a connection to server.
     */
    public static final String CONNECTION_ESTABLISHED = "#OC:Connection established.";

    // CLASS VARIABLES

    /**
     * The string sent to the observers when a client requests a split cretion.
     */
    public static final String CREATION_REQUEST= "#OS:Creation Request";

    /**
     * The string sent to the client when a has requested his splits.
     */
    public static final String GET_SPLIT_REQUEST= "#OS:Get Split Request";

    /**
     * The string sent to the client when a client has successfully joined a split.
     */
    public static final String JOINED_SPLIT= "#OS:Joined Split";

    /**
     * The string sent to the client when a client has failed to join a split.
     */
    public static final String SPLIT_NOT_FOUND= "#OS:Split Not Found";

    /**
     * The string sent to the observers when a client tries to join a split.
     */
    public static final String JOIN_SPLIT_ATTEMPT= "#OS:Join Split Attempt";

    /**
     * The string sent to the observers when a client tries to join a split.
     */
    public static final String PARTICIPANT_ALREADY_IN_SPLIT= "#OS:Participant Already In Split";


    //Instance variables **********************************************

    /**
     * The interface type variable.  It allows the implementation of
     * the display method in the client.
     */
    Object clientUI;
    ObservableClient communicationService;
    private SplitController splitController;

    // Variable that holds de name chosen by the user when created

    String name;

    /**
     * Hook method called after the connection has been closed.
     * The default implementation does nothing. The method
     * may be overriden by subclasses to perform special processing
     * such as cleaning up and terminating, or attempting to
     * reconnect.
     */
    protected void connectionClosed() {
//        clientUI.display("Connection closed correctly");
        System.out.println("Connection closed correctly");
    }

    /**
     * Hook method called each time an exception is thrown by the
     * client's thread that is waiting for messages from the server.
     * The method may be overridden by subclasses.
     *
     * @param exception the exception raised.
     */
    protected void connectionException(Exception exception) {
//        clientUI.display("Lost connection with the server");
        System.out.println("Lost connection with the server");
        quit();
    }

    /**
     * Hook method called after a connection has been established.
     * The default implementation does nothing.
     * It may be overridden by subclasses to do anything they wish.
     */
    protected void connectionEstablished() {
//        clientUI.display("Connection established successfully");
        System.out.println("Connection established successfully");

    }



    //Constructors ****************************************************

    /**
     * Constructs an instance of the chat client.
     *
     * @paarm splitController
     */

    private static SplitClientFacade instance;

    public SplitClientFacade(SplitController splitController)
    {
        this.splitController=splitController;
        communicationService = new ObservableClient("localhost", 5555);
        communicationService.addObserver(this);
    }


    //Instance methods ************************************************

    /**
     * This method handles all data that comes in from the server.
     * TODO : handle disconnection error
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg) {
        SplitOriginatorMessage msgReceived = (SplitOriginatorMessage) msg;
        System.out.println("Received message");
        switch ((String) msgReceived.getMessage()){
            case GET_SPLIT_REQUEST:
                System.out.println(msgReceived.getSplits());
                try {
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                splitController.setSplits(msgReceived.getSplits());
                break;
            case JOINED_SPLIT:
                System.out.println("Success : Participant joined the split");
                System.out.println("Received split : " + msgReceived.getSplit());
                splitController.setSplitSectionFlashMessage("Success : redirecting...");
                splitController.splitJoined(msgReceived.getSplit());
                break;
            case PARTICIPANT_ALREADY_IN_SPLIT:
                System.out.println("Error : Participant already in Split");
                try {
                    splitController.setSplitSectionFlashMessage("Error : participant already in split");
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case SPLIT_NOT_FOUND:
                System.out.println("Error : Wrong code");
                try {
                    splitController.setSplitSectionFlashMessage("Error : wrong code split not found");
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "exception":
                System.out.println("exception");
        }

    }

    public void sendToServer(SplitOriginatorMessage message){
        try{
            communicationService.sendToServer(message);
        }
        catch(IOException e){
//            clientUI.display
//                    ("Could not send message to server.  Terminating client.");
            System.out.println("Could not send message to server.  Terminating client.");
            e.printStackTrace();

            quit();
        }
    }

    /**
     * This method terminates the client.
     */
    public void quit()
    {
        try
        {
            communicationService.closeConnection();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * TODO : implement
     */
    public void joinSplit(String splitCode){
        try {
            communicationService.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("splitcode entered :"+splitCode);

        String id = UserFacade.getUserFacade().getLoggedUser().getId();
        String nickName = UserFacade.getUserFacade().getLoggedUser().getNickname();

        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",id);
        arguments.put("nickName",nickName);
        arguments.put("splitCode",splitCode);

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,JOIN_SPLIT_ATTEMPT,arguments,null);

        sendToServer(message);
        System.out.println("joinSplit message sent to server");

    }

    /**
     *
     */
    public void getSplits() throws IOException {
        communicationService.openConnection();

        String id = UserFacade.getUserFacade().getLoggedUser().getId();

        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",id);

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,GET_SPLIT_REQUEST,arguments,null);

        sendToServer(message);
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        SplitOriginatorMessage msg = (SplitOriginatorMessage) arg;
        String message = (String) msg.getMessage();
        if(CONNECTION_CLOSED.contentEquals(message)){
            connectionClosed();
        }
        else if (CONNECTION_ESTABLISHED.contentEquals(message)){
            connectionEstablished();
        }
        else {
            handleMessageFromServer(msg);
        }
    }

}