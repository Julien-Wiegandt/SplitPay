package client.facade;

import client.communication.ObservableClient;
import core.facade.UserFacade;
import server.communication.SplitOriginatorMessage;
import server.models.split.FreeSplit;
import ui.controller.split.MySplitsController;
import ui.controller.split.SplitSaloonController;
import ui.controller.split.SplitSectionController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import util.ClientServerProtocol;

/**
 * This class is the facade linking the app to the server and
 * receiving and processing data sent by the server
 *
 * @author Ayoub Moujane
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

    //Instance variables **********************************************

    ObservableClient communicationService;
    // TODO : change splitController

    /**
     * Hook method called after the connection has been closed.
     * The default implementation does nothing. The method
     * may be overriden by subclasses to perform special processing
     * such as cleaning up and terminating, or attempting to
     * reconnect.
     */
    protected void connectionClosed() {
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
        System.out.println("Lost connection with the server");
        quit();
    }

    /**
     * Hook method called after a connection has been established.
     * The default implementation does nothing.
     * It may be overridden by subclasses to do anything they wish.
     */
    protected void connectionEstablished() {
        System.out.println("Connection established successfully");

    }

    //Constructors ****************************************************

    /**
     * Constructs an instance of the chat client.
     *
     *                 // TODO : change splitController
     * @paarm splitController
     */

    private static SplitClientFacade instance;

    private SplitClientFacade()
    {

        communicationService = new ObservableClient("localhost", 5555);
        communicationService.addObserver(this);
    }

    public static SplitClientFacade getInstance(){
        if(instance==null){
            instance=new SplitClientFacade();
        }
        return instance;

    }

    // Dependencies on initialized views

    public void setMySplitsController(MySplitsController mySplitsController){
        this.mySplitsController=mySplitsController;
    }

    public void setSplitSaloonController(SplitSaloonController splitSaloonController){
        this.splitSaloonController=splitSaloonController;
    }

    public void setSplitSectionController(SplitSectionController splitSectionController){
        this.splitSectionController=splitSectionController;
    }

    /* References to controllers receiving server data ***************** */

    private MySplitsController mySplitsController;
    private SplitSaloonController splitSaloonController;
    private SplitSectionController splitSectionController;

    /* Data temporally stored for controllers */

    private FreeSplit joinedSplit;

    /* Controller data methods */

    private void setJoinedSplit(FreeSplit joinedSplit){
        this.joinedSplit = joinedSplit;
    }

    public FreeSplit getJoinedSplit(){
        return joinedSplit;
    }

    /* Instance methods ************************************************ */

    /**
     * This method handles all data that comes in from the server.
     * TODO : handle disconnection error
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg) {

        System.out.println(msg);
        SplitOriginatorMessage msgReceived = (SplitOriginatorMessage) msg;
        switch (msgReceived.getMessage()){
            case ClientServerProtocol.GET_SPLIT_REQUEST:
                System.out.println(msgReceived.getSplits());
                try {
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mySplitsController.setSplits(msgReceived.getSplits());
                break;
            case ClientServerProtocol.JOINED_SPLIT:
                System.out.println("Success : Participant joined the split");
                System.out.println("Received split : " + msgReceived.getSplit());
                setJoinedSplit(msgReceived.getSplit());
                splitSectionController.setFlashMessage("Success : redirecting...");
                splitSectionController.splitJoined();
                break;
            case ClientServerProtocol.PARTICIPANT_ALREADY_IN_SPLIT:
                System.out.println("Error : Participant already in Split");
                try {
                    splitSectionController.setFlashMessage("Error : participant already in split");
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ClientServerProtocol.SPLIT_NOT_FOUND:
                System.out.println("Error : Wrong code");
                try {
                    splitSectionController.setFlashMessage("Error : wrong code split not found");
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ClientServerProtocol.UPDATED_SPLIT_STATE:
                System.out.println(msgReceived.getSplits());
                setJoinedSplit(msgReceived.getSplit());
                splitSaloonController.updateSplit(getJoinedSplit());
                break;
            case ClientServerProtocol.QUIT_SPLIT_SUCCESS:
                System.out.println("Split quit successfully");
                try {
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                splitSaloonController.splitQuit();
        }

    }

    public void sendToServer(SplitOriginatorMessage message){
        try{
            communicationService.sendToServer(message);
        }
        catch(IOException e){
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

    /* Methods creating requests to the server ***************** */

    /**
     * Method asking the server to join a split with a splitcode
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

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.JOIN_SPLIT_ATTEMPT,arguments,null);

        sendToServer(message);
        System.out.println("joinSplit message sent to server");

    }

    /**
     * Method asking the server to change the currently logged user's
     * current amount in the split corresponding to the provided splitcode.
     * @param newAmount the new participant amount
     * @param splitCode the split code
     */
    public void changeAmount(double newAmount, String splitCode){

        String id = UserFacade.getUserFacade().getLoggedUser().getId();

        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",id);
        arguments.put("splitCode",splitCode);
        arguments.put("newAmount",Double.toString(newAmount));

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.CHANGE_AMOUNT_REQUEST,arguments,null);
        sendToServer(message);

    }

    /**
     * Method asking the server the splits of the currently logged user
     */
    public void getSplits() throws IOException {
        communicationService.openConnection();

        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",UserFacade.getUserFacade().getLoggedUser().getId());

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.GET_SPLIT_REQUEST,arguments,null);

        sendToServer(message);
    }

    /**
     * Method asking the server to change the logged user's current ready status
     */
    public void switchReadyStatus(String splitCode){

        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",UserFacade.getUserFacade().getLoggedUser().getId());
        arguments.put("splitCode",splitCode);

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.CHANGE_READY_STATUS,arguments,null);

        sendToServer(message);

    }

    /**
     * Method asking the server to remove a participant from a split
     */
    public void quitSplit(String splitCode){

        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",UserFacade.getUserFacade().getLoggedUser().getId());
        arguments.put("splitCode",splitCode);

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.QUIT_SPLIT_REQUEST,arguments,null);

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
        System.out.println("Client update : "+msg);
        String message = msg.getMessage();
        // TODO : handle null problem
        if(message != null){
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

}