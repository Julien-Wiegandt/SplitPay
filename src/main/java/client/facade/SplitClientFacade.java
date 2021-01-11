package client.facade;

import client.communication.ObservableClient;
import core.facade.UserFacade;
import core.models.Bill;
import core.models.StoreOwner;
import server.communication.SplitOriginatorMessage;
import server.models.split.*;
import ui.controller.split.*;
import util.ClientServerProtocol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

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
// TODO : rename to setFreeSplitSaloonController
    public void setSplitSaloonController(FreeSplitSaloonController freeSplitSaloonController){
        this.freeSplitSaloonController = freeSplitSaloonController;
    }

    public void setSplitSectionController(SplitSectionController splitSectionController){
        this.splitSectionController=splitSectionController;
    }

    public void setItemSplitSaloonController(ItemSplitSaloonController itemSplitSaloonController){
        this.itemSplitSaloonController=itemSplitSaloonController;
    }

    public void setSplitCreationController(SplitCreationController splitCreationController){
        this.splitCreationController=splitCreationController;
    }

    public void setSplitGenerationController(GenerateSplitViewController generateSplitController){
        this.generateSplitController=generateSplitController;
    }


    /* References to controllers receiving server data ***************** */

    private MySplitsController mySplitsController;
    private FreeSplitSaloonController freeSplitSaloonController;
    private ItemSplitSaloonController itemSplitSaloonController;
    private SplitSectionController splitSectionController;

    private SplitCreationController splitCreationController;
    private GenerateSplitViewController generateSplitController;

    /* Data temporally stored for controllers */

    private Split joinedSplit;

    /* Controller data methods */

    private void setJoinedSplit(Split joinedSplit){
        this.joinedSplit = joinedSplit;
    }

    public Split getJoinedSplit(){
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
                // TODO : Participant already in split, moves in the saloon without adding ?
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
                switch (getJoinedSplit().getSplitMode()){
                    case FREESPLIT:
                        freeSplitSaloonController.updateSplit((FreeSplit) getJoinedSplit());
                        break;
                    case ITEMSPLIT:
                        itemSplitSaloonController.updateSplit((ItemSplit) getJoinedSplit());
                        break;
                }
                break;
            case ClientServerProtocol.QUIT_SPLIT_SUCCESS:
                System.out.println("Split quit successfully");
                try {
                    communicationService.closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (getJoinedSplit().getSplitMode()){
                    case FREESPLIT:
                        freeSplitSaloonController.splitQuit();
                        break;
                    case ITEMSPLIT:
                        itemSplitSaloonController.splitQuit();
                        break;
                }
                break;
            case ClientServerProtocol.SPLIT_PAID_RESPONSE:
                switch (getJoinedSplit().getSplitMode()){
                    case FREESPLIT:
                        freeSplitSaloonController.splitPaid();
                        break;
                    case ITEMSPLIT:
                        itemSplitSaloonController.splitPaid();
                        break;
                }
                System.out.println("Split paid success");
                break;
            case ClientServerProtocol.SPLIT_CREATED_RESPONSE:
                try {
                    communicationService.closeConnection();
                    String splitCode = msgReceived.getArgument("splitCode");
                    String splitModeString = msgReceived.getArgument("splitMode");
                    SplitMode splitMode
                            = SplitMode.valueOf(splitModeString);

                    switch (splitMode){
                        case FREESPLIT:
                            splitCreationController.splitCreatedSuccess(splitCode);
                            break;
                        case ITEMSPLIT:
                            generateSplitController.splitCreatedSuccess(splitCode);
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

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

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.JOIN_SPLIT_ATTEMPT,arguments,null,null,null);

        sendToServer(message);
        System.out.println("joinSplit message sent to server");

    }

    public void pickItem(int itemId, String splitCode){
        String id = UserFacade.getUserFacade().getLoggedUser().getId();
        String nickName = UserFacade.getUserFacade().getLoggedUser().getNickname();

        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",id);
        arguments.put("nickName",nickName);
        arguments.put("splitCode",splitCode);
        arguments.put("itemId",Integer.toString(itemId));

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.PICK_ITEM_ATTEMPT,arguments,null,null,null);

        sendToServer(message);
    }

    public void removeItem(int itemId, String splitCode){
        String id = UserFacade.getUserFacade().getLoggedUser().getId();
        String nickName = UserFacade.getUserFacade().getLoggedUser().getNickname();

        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",id);
        arguments.put("nickName",nickName);
        arguments.put("splitCode",splitCode);
        arguments.put("itemId",Integer.toString(itemId));

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.REMOVE_ITEM_ATTEMPT,arguments,null,null,null);

        sendToServer(message);
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

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.CHANGE_AMOUNT_REQUEST,arguments,null,null,null);
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

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.GET_SPLIT_REQUEST,arguments,null,null,null);

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

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.CHANGE_READY_STATUS,arguments,null,null,null);

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

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.QUIT_SPLIT_REQUEST,arguments,null,null,null);

        sendToServer(message);
    }

    /**
     * Method asking the server to pay the split
     * @param splitCode
     */
    public void paySplit(String splitCode){
        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("splitCode",splitCode);

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.SPLIT_PAYMENT_REQUEST,arguments,null,null,null);

    }

    /**
     * Method called by the client asking the server to create a free split
     */
    public void createFreeSplit(String splitLabel, double goalAmount, StoreOwner receiver) throws IOException {
        communicationService.openConnection();

        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("label",splitLabel);
        arguments.put("goalAmount",String.valueOf(goalAmount));
        arguments.put("ownerId",UserFacade.getUserFacade().getLoggedUser().getId());
        arguments.put("ownerNickname",UserFacade.getUserFacade().getLoggedUser().getNickname());
        arguments.put("splitMode", SplitMode.FREESPLIT.toString());

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.SPLIT_CREATION_REQUEST,arguments,null,receiver,null);

        System.out.println("Message createFreeSplit: "+message);

        sendToServer(message);
    }

    /**
     * Method called by the client asking the server to create a free split
     */
    public void createItemSplit(String splitLabel,  Bill bill) throws IOException {
        StoreOwner receiver=UserFacade.getUserFacade().getLoggedStoreOwner();
        /* Creating request arguments */
        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("label",splitLabel);
        arguments.put("ownerNickname",UserFacade.getUserFacade().getLoggedUser().getNickname());
        arguments.put("ownerId",UserFacade.getUserFacade().getLoggedUser().getId());
        arguments.put("splitMode", SplitMode.ITEMSPLIT.toString());
        SplitOriginatorMessage message = new SplitOriginatorMessage(null,ClientServerProtocol.SPLIT_CREATION_REQUEST,arguments,null,receiver,bill);

        System.out.println("Message createFreeSplit: "+message);

        System.out.println("splitlabel :"+splitLabel);
        System.out.println("bill items :"+bill.getItems());
        communicationService.openConnection();


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